package com.lpodev.bookmybook.fragments.library

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lpodev.bookmybook.R
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.databinding.BookCellBinding

class BookListAdapter(
) : RecyclerView.Adapter<BookListViewHolder>() {
    private var bookList = emptyList<Book>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = BookCellBinding.inflate(from, parent, false)
        return BookListViewHolder(binding)
    }

    override fun getItemCount(): Int = bookList.size

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.itemView.findViewById<TextView>(R.id.title).text = currentItem.title
        holder.itemView.findViewById<TextView>(R.id.author).text = currentItem.title
        holder.itemView.findViewById<ImageView>(R.id.cover).load(currentItem.cover)

        holder.itemView.findViewById<CardView>(R.id.bookCell).setOnClickListener{
            val action = LibraryFragmentDirections.actionSearchFragmentToDetailFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(book: List<Book>) {
        this.bookList = book
        notifyDataSetChanged()
    }
}