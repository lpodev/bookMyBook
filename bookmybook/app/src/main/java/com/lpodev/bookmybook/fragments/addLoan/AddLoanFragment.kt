package com.lpodev.bookmybook.fragments.addLoan

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lpodev.bookmybook.databinding.FragmentAddLoanBinding
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.models.Loan
import com.lpodev.bookmybook.viewmodels.BookViewModel
import com.lpodev.bookmybook.viewmodels.LoanViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

class AddLoanFragment : Fragment() {
    private var _binding: FragmentAddLoanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: BookSelectorAdapter
    private var bookSelected: String? = null
    private val bookViewModel: BookViewModel by viewModels()
    private val CONTACT_PERMISSION_CODE = 1
    private val CONTACT_PICK_CODE = 2
    private var contact_id = 0
    private lateinit var selectedBook: Book
    private lateinit var loanViewModel: LoanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLoanBinding.inflate(inflater, container, false)
        loanViewModel = ViewModelProvider(this)[LoanViewModel::class.java]

        val endDatePicker = binding.endDatePicker
        val calendar = Calendar.getInstance()
        endDatePicker.minDate = calendar.timeInMillis

        endDatePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ) { _, year, monthOfYear, dayOfMonth ->
            calendar.set(year, monthOfYear, dayOfMonth)
        }

        val bookSelector = binding.bookSelector
        adapter = BookSelectorAdapter(requireContext(), emptyList()) // Pass an empty list for now
        bookSelector.adapter = adapter

        bookViewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.dataSource = books
            adapter.notifyDataSetChanged()

            if (books.isEmpty()) {
                // Show a dialog indicating that there are no books
                showDialogNoBooks()
            }
        }

        binding.edtContact.setOnClickListener {
            if (checkContactPermission()) {
                pickContact()
            } else {
                requestContactPermission()
            }
        }

        bookSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedBook = adapter.getItem(position) as Book
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected, if needed
            }
        }

        binding.btnAddLoan.setOnClickListener {
            if (contact_id != 0) {
                val today = calendar.time

                calendar.set(endDatePicker.year, endDatePicker.month, endDatePicker.dayOfMonth)
                val endDate = calendar.time

                val job = lifecycleScope.launch {
                    val loan = Loan(
                        0,
                        contact_id,
                        today,
                        endDate,
                        selectedBook.id,
                    )
                    loanViewModel.addLoan(loan)
                }
                job.invokeOnCompletion {
                    Toast.makeText(requireContext(), "Ajouté avec succès !", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().popBackStack()
                }
            }
        }

        return binding.root
    }

    private fun showDialogNoBooks() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("No Books")
        alertDialogBuilder.setMessage("Ajoutez un livre avant de venir ici !")
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            findNavController().popBackStack()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun checkContactPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestContactPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(requireActivity(), permission, CONTACT_PERMISSION_CODE)
    }

    private fun pickContact() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, CONTACT_PICK_CODE)
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("Range", "Recycle")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == CONTACT_PICK_CODE) {
            val contactUri = data?.data
            val contactId = contactUri?.lastPathSegment

            if (contactId != null) {
                val contactName = getContactName(contactId)
                binding.edtContact.setText(contactName)
                contact_id = contactId.toInt()
            }
        } else {
            Toast.makeText(requireContext(), "Error during picking contact", Toast.LENGTH_SHORT)
                .show()
        }
    }

    @SuppressLint("Range")
    private fun getContactName(contactId: String): String? {
        val contactCursor = requireActivity().contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            ContactsContract.Contacts._ID + "=?",
            arrayOf(contactId),
            null
        )
        contactCursor?.use {
            if (it.moveToFirst()) {
                return it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            }
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}