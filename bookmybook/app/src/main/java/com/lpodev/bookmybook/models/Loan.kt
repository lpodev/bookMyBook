package com.lpodev.bookmybook.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "loans_table")
data class Loan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val contact_id: Int,
    val start_date: Date,
    val end_date: Date,
    val book_id: Int,
)