package com.lpodev.bookmybook.fragments.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.viewmodels.BookViewModel
import androidx.lifecycle.Observer

class LibraryFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var mBookViewModel: BookViewModel
    private lateinit var recyclerViewAdapter : BookListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val floatingActionButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_addBookFragment)
        }

        val adapter = BookListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.booksRecyclerView)
        val search = view.findViewById<SearchView>(R.id.search_bar)

        search.setOnQueryTextListener(this)
        recyclerViewAdapter = adapter

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        mBookViewModel.readAllData.observe(viewLifecycleOwner, Observer {book ->
            adapter.setData(book)
        })

        return view
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchBookInDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            searchBookInDatabase(query)
        }
        return true
    }

    private fun searchBookInDatabase(query: String?){
        val searchQuery = "%$query%"

        mBookViewModel.searchBook(searchQuery).observe(this) { list ->
            list.let {
                recyclerViewAdapter.setData(it)
            }
        }
    }
}