package com.lpodev.bookmybook.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "loans_table")
data class Loan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val contact_id: Int,
    val start_date: LocalDateTime,
    val end_date: LocalDateTime,
    val book_id: Int,
)