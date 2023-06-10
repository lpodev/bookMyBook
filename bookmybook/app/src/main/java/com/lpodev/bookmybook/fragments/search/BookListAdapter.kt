package com.lpodev.bookmybook.fragments.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.data.Book
import com.lpodev.bookmybook.databinding.BookCellBinding

class BookListAdapter : RecyclerView.Adapter<BookListViewHolder>() {
    private var bookList = emptyList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = BookCellBinding.inflate(from, parent, false)
        return BookListViewHolder(binding)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.itemView.findViewById<TextView>(R.id.title).text = currentItem.title.toString()
        holder.itemView.findViewById<TextView>(R.id.author).text = currentItem.title.toString()
    }

    fun setData(book: List<Book>){
        this.bookList = book
        notifyDataSetChanged()
    }
}