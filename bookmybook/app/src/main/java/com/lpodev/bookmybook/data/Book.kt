package com.lpodev.bookmybook.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cover: Bitmap,
    val title: String,
    val author: String,
    val isbn: String
)