package com.lpodev.bookmybook.fragments.listLoan

import android.provider.ContactsContract
import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.data.LoanWithBook
import com.lpodev.bookmybook.databinding.LoanCellBinding

class LoanListViewHolder(

    private val binding: LoanCellBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loan: LoanWithBook) {
        binding.title.text = loan.book.title
    }

    fun setContactName(contactName: String?) {
        binding.contactName.text = contactName ?: "Contact not found"
    }

    fun setDaysToEndDate(daysLeft: Long) {
        binding.daysToEndDate.text = daysLeft.toString()
    }
}