package com.lpodev.bookmybook.fragments.listLoan

import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.databinding.BookCellBinding
import com.lpodev.bookmybook.models.Book

class LoanListViewHolder(

    private val bookCellBinding: BookCellBinding,
) : RecyclerView.ViewHolder(bookCellBinding.root) {
    fun bind(book: Book) {
        bookCellBinding.title.text = book.title
        bookCellBinding.author.text = book.author
    }
}