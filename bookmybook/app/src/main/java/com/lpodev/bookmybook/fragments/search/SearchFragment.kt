package com.lpodev.bookmybook.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.data.BookViewModel
import androidx.lifecycle.Observer

class SearchFragment : Fragment() {
    private lateinit var mBookViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false) 

        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_addBookFragment)
        }

        val adapter = BookListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.booksRecyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        mBookViewModel.readAllData.observe(viewLifecycleOwner, Observer {book ->
            adapter.setData(book)
        })

        return view
    }
}