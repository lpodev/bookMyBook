package com.lpodev.bookmybook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load

class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        view.findViewById<ImageView>(R.id.cover).load(args.currentBook.cover)
        view.findViewById<TextView>(R.id.title).text = args.currentBook.title
        view.findViewById<TextView>(R.id.author).text = args.currentBook.author
        view.findViewById<TextView>(R.id.isbn).text = args.currentBook.isbn

        return view
    }
}