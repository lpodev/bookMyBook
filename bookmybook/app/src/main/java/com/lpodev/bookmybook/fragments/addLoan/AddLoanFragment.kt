package com.lpodev.bookmybook.fragments.addLoan

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.lpodev.bookmybook.data.BookDao
import com.lpodev.bookmybook.databinding.FragmentAddLoanBinding
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.repositories.BookRepository
import com.lpodev.bookmybook.viewmodels.BookViewModel
import java.util.Calendar

class AddLoanFragment : Fragment() {
    private var _binding: FragmentAddLoanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: BookSelectorAdapter;
    private lateinit var bookList: ArrayList<Book>
    private var bookSelected: String? = null
    private val bookViewModel: BookViewModel by viewModels()
    private val CONTACT_PERMISSION_CODE = 1;
    private val CONTACT_PICK_CODE = 2;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddLoanBinding.inflate(inflater, container, false)

        val calendar = Calendar.getInstance()
        binding.endDatePicker.minDate = calendar.timeInMillis

        val bookSelector = binding.bookSelector
        adapter = BookSelectorAdapter(requireContext(), emptyList()) // Pass an empty list for now
        bookSelector.adapter = adapter

        bookViewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.dataSource = books
            adapter.notifyDataSetChanged()
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
                val selectedBook = adapter.getItem(position) as Book
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected, if needed
            }
        }

        return binding.root
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