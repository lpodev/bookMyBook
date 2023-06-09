package com.lpodev.bookmybook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cover: String?,
    val title: String?,
    val author: String?,
    val isbn: String?
)