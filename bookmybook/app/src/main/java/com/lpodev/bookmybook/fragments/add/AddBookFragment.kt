package com.lpodev.bookmybook.fragments.add

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.viewmodels.BookViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class AddBookFragment : Fragment() {
    private lateinit var mBookViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_book, container, false)

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val bookTitle = view?.findViewById<EditText>(R.id.bookTitle_et)?.text.toString()
        val bookAuthor = view?.findViewById<EditText>(R.id.bookAuthor_et)?.text.toString()
        val bookIsbn = view?.findViewById<EditText>(R.id.bookIsbn_et)?.text.toString()
        val bookBitmapUrl = view?.findViewById<EditText>(R.id.bookBitmapUrl_et)?.text.toString()

        if (inputChecker(bookTitle, bookAuthor, bookIsbn, bookBitmapUrl)) {

            lifecycleScope.launch {
                val book = Book(0, getBitmap(bookBitmapUrl), bookTitle, bookAuthor, bookIsbn)
                mBookViewModel.addBook(book)
            }

            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addBookFragment_to_searchFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill out all fields correctly.",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun inputChecker(
        title: String,
        author: String,
        isbn: String,
        bookBitmapUrl: String
    ): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(author) || TextUtils.isEmpty(isbn) || TextUtils.isEmpty(
            bookBitmapUrl
        ) || !URLUtil.isValidUrl(bookBitmapUrl))
    }

    private suspend fun getBitmap(imageUrl: String): Bitmap {
        val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data(imageUrl)
            .build()
        try {
            val result = (loading.execute(request) as SuccessResult).drawable
            return (result as BitmapDrawable).bitmap
        }catch (e: Exception){
            return getLocalAssetBitmap()
        }
    }

    private fun getLocalAssetBitmap(): Bitmap {
        val assetManager = requireContext().assets
        val inputStream: InputStream
        try {
            inputStream = assetManager.open("missing_cover.jpg")
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            throw e
        }
    }

}