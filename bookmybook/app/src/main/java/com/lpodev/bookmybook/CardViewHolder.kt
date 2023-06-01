package com.lpodev.bookmybook

import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.databinding.CardCellBinding

class CardViewHolder(private val cardCellBinding : CardCellBinding) : RecyclerView.ViewHolder(cardCellBinding.root)
{
    fun bind(book: Book) {
        cardCellBinding.cover.setImageResource(book.cover)
        cardCellBinding.title.text = book.title
        cardCellBinding.author.text = book.author
    }
}