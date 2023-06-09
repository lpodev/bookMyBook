package com.lpodev.bookmybook.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lpodev.bookmybook.R

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val addButton = view.findViewById<FloatingActionButton>(R.id.add_btn)
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_addBookFragment)
        }

        return view
    }
}