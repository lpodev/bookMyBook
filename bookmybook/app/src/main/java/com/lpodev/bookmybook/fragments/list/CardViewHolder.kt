package com.lpodev.bookmybook.fragments.list

import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.data.Book
import com.lpodev.bookmybook.databinding.CardCellBinding

class CardViewHolder(
    private val cardCellBinding: CardCellBinding
) : RecyclerView.ViewHolder(cardCellBinding.root) {
    fun bind(book: Book) {
        cardCellBinding.title.text = book.title
        cardCellBinding.author.text = book.author
    }
}