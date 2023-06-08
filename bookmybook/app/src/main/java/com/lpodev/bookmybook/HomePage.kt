package com.lpodev.bookmybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lpodev.bookmybook.databinding.HomepageBinding

class HomePage : AppCompatActivity()
{
    private lateinit var binding: HomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        populateBook()

        val mainActivity = this
        binding.loansRecyclerView.apply {
            layoutManager = LinearLayoutManager(mainActivity)
            adapter = CardAdapter(bookList)
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