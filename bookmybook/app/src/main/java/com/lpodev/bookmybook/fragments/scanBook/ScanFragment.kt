package com.lpodev.bookmybook.fragments.scanBook

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.common.util.concurrent.ListenableFuture
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.lpodev.bookmybook.databinding.FragmentScanBinding
import java.util.concurrent.Executors

private const val CAMERA_PERMISSION_REQUEST_CODE = 1

class ScanFragment : Fragment() {
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!
    private lateinit var barcodeScanner: BarcodeScanner
    private var isAlertShown = false
    private var isScanningEnabled = true
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        if (hasCameraPermission()) bindCameraUseCases()
        else requestPermission()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        isAlertShown = false
        isScanningEnabled = true
    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        // opening up dialog to ask for camera permission
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    private fun bindCameraUseCases() {
        cameraProviderFuture.addListener({
            val options = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_EAN_13)
                .enableAllPotentialBarcodes()
                .build()

            // getClient() creates a new instance of the MLKit barcode scanner with the specified options
            val scanner = BarcodeScanning.getClient(options)

            // setting up the analysis use case
            val analysisUseCase = ImageAnalysis.Builder().build()

            // define the actual functionality of our analysis use case
            analysisUseCase.setAnalyzer(
                // newSingleThreadExecutor() will let us perform analysis on a single worker thread
                Executors.newSingleThreadExecutor()
            ) { imageProxy ->
                processImageProxy(scanner, imageProxy)
            }
            val cameraProvider = cameraProviderFuture.get()

            // setting up the preview use case
            val previewUseCase = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            // configure to use the back camera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.bindToLifecycle(this, cameraSelector, previewUseCase, analysisUseCase)

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun processImageProxy(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy
    ) {

        imageProxy.image?.let { image ->
            val inputImage = InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees)
            barcodeScanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    // Process the detected barcodes
                    for (barcode in barcodes) {
                        val rawValue = barcode.rawValue
                        // Handle the barcode value as needed
                        if (rawValue != "") {
                            showBarcodeResult(rawValue)
                        }
                    }
                }.addOnCompleteListener {
                    imageProxy.image?.close()
                    imageProxy.close()
                }
        }
    }

    private fun showBarcodeResult(barcode: String?) {
        if (!isAlertShown) {
            isAlertShown = true // Set the flag to indicate that the alert is shown
            isScanningEnabled = false // Disable scanning while the alert is shown

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Code barres trouvé")
            builder.setMessage("Souhaitez vous rechercher $barcode ?")
            builder.setPositiveButton("Oui") { dialog, _ ->
                dialog.dismiss()
                val action = ScanFragmentDirections.actionSearchQuery(barcode)
                findNavController().navigate(action)
            }
            builder.setNegativeButton("Non") { dialog, _ ->
                dialog.dismiss()
                isAlertShown = false
                isScanningEnabled = true
            }
            val alertDialog = builder.create()

            // Show the alert dialog
            alertDialog.show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            bindCameraUseCases()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll() // Unbind all camera use cases
        }, ContextCompat.getMainExecutor(requireContext()))
        _binding = null
    }
}