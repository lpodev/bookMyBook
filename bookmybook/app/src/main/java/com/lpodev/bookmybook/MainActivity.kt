package com.lpodev.bookmybook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.lpodev.bookmybook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateBooks()

        val mainActivity = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 1, GridLayoutManager.VERTICAL, false)
            adapter = CardAdapter(bookList)
        }
    }

    private fun populateBooks() {
        val book1 = Book(
            R.drawable.gentleman_cambrioleur,
            author = "Maurice Leblanc",
            title = "Arsène Lupin, gentleman-cambrioleur",
            description = "Lupin qui vole des choses",
            isbn = "978-2-1234-5680-3"
        )
        bookList.add(book1)

        val book2 = Book(
            R.drawable.gentleman_cambrioleur,
            author = "Maurice Leblanc",
            title = "Arsène Lupin, gentleman-cambrioleur",
            description = "Lupin qui vole des choses",
            isbn = "978-2-1234-5680-3"
        )

        bookList.add(book2)
        bookList.add(book2)
        bookList.add(book2)
        bookList.add(book2)
    }

}