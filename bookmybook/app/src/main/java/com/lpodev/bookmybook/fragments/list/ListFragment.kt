package com.lpodev.bookmybook.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.data.BookViewModel

class ListFragment : Fragment() {
    private lateinit var mBookViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = CardAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.loansRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mBookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        mBookViewModel.readAllData.observe(viewLifecycleOwner, Observer {book ->
            adapter.setData(book)
        })

        return view
    }
}