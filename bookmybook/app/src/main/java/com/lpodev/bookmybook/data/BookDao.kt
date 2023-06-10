package com.lpodev.bookmybook.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Query("SELECT * FROM book_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE title LIKE :searchQuery OR author LIKE :searchQuery OR isbn LIKE :searchQuery")
    fun searchBook(searchQuery: String): LiveData<List<Book>>
}