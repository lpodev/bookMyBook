package com.lpodev.bookmybook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.data.Book
import com.lpodev.bookmybook.databinding.CardCellBinding

class CardAdapter( private val books: List<Book>)
    : RecyclerView.Adapter<CardViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(books[position])
    }
}