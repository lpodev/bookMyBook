package com.lpodev.bookmybook.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.data.Book
import com.lpodev.bookmybook.data.BookViewModel

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

        if (inputChecker(bookTitle, bookAuthor, bookIsbn)){
            val book = Book(0, "", bookTitle, bookAuthor, bookIsbn)
            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addBookFragment_to_searchFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecker(title: String, author: String, isbn: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(author) && TextUtils.isEmpty(isbn))
    }
}