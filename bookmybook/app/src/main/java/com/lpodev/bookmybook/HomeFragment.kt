package com.lpodev.bookmybook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CardAdapter

    private var isBooksPopulated = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isBooksPopulated) {
            populateBook()
            isBooksPopulated = true
        }

        adapter = CardAdapter(bookList)
        binding.loansRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }


    }

    private fun populateBook() {
        val book1 = Book(
            R.drawable.cvr_ta,
            "The Alchemist",
            "Paulo Coelho",
            "978-3-16-148410-0"
        )
        val book2 = Book(
            R.drawable.cvr_tkr,
            "The Kite Runner",
            "Khaled Hosseini",
            "978-3-16-148410-1"
        )
        val book3 = Book(
            R.drawable.cvr_tgg,
            "The Great Gatsby",
            "F. Scott Fitzgerald",
            "978-3-16-148410-2"
        )
        val book4 = Book(
            R.drawable.cvr_tfios,
            "The Fault in Our Stars",
            "John Green",
            "978-3-16-148410-3"
        )
        val book5 = Book(
            R.drawable.cvr_tdvc,
            "The Da Vinci Code",
            "Dan Brown",
            "978-3-16-148410-4"
        )
        bookList.add(book1)
        bookList.add(book2)
        bookList.add(book3)
        bookList.add(book4)
        bookList.add(book5)
    }

}