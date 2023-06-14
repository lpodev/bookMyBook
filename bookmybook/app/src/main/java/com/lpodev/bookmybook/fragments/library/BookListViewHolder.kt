package com.lpodev.bookmybook.fragments.library

import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.databinding.BookCellBinding

class BookListViewHolder(
    private val bookCellBinding: BookCellBinding,
) : RecyclerView.ViewHolder(bookCellBinding.root) {
    fun bind(book: Book) {
        bookCellBinding.title.text = book.title
        bookCellBinding.author.text = book.author
    }
}