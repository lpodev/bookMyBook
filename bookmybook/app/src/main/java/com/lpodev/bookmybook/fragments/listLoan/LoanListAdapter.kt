package com.lpodev.bookmybook.fragments.listLoan

import android.annotation.SuppressLint
import android.database.Cursor
import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lpodev.bookmybook.data.LoanWithBook
import com.lpodev.bookmybook.databinding.LoanCellBinding
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class LoanListAdapter(private val context: Context): RecyclerView.Adapter<LoanListViewHolder>() {
    private var loanList = emptyList<LoanWithBook>()
    private lateinit var binding: LoanCellBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanListViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = LoanCellBinding.inflate(from, parent, false)
        return LoanListViewHolder(binding)
    }

    override fun getItemCount(): Int = loanList.size

    override fun onBindViewHolder(holder: LoanListViewHolder, position: Int) {
        val currentItem = loanList[position]

        holder.bind(currentItem)

        val endDate = currentItem.loan.end_date
        val endDateInstant = Instant.ofEpochMilli(endDate.time)
        val endDateLocalDate = endDateInstant.atZone(ZoneId.systemDefault()).toLocalDate()
        val today = LocalDate.now()
        val daysLeft = ChronoUnit.DAYS.between(today, endDateLocalDate)

        holder.setContactName(getContactName(currentItem.loan.contact_id))
        holder.setDaysToEndDate(daysLeft)
    }

    fun setData(loan: List<LoanWithBook>) {
        this.loanList = loan
        notifyDataSetChanged()
    }

    @SuppressLint("Range")
    private fun getContactName(contactId: Int): String? {
        val contentResolver: ContentResolver = context.contentResolver
        val cursor: Cursor? = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(ContactsContract.Contacts.DISPLAY_NAME),
            ContactsContract.Contacts._ID + " = ?",
            arrayOf(contactId.toString()),
            null
        )
        cursor?.use {
            if (it.moveToFirst()) {
                return it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            }
        }
        return null
    }
}