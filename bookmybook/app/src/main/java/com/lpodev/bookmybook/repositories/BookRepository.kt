package com.lpodev.bookmybook.repositories

import androidx.lifecycle.LiveData
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.data.BookDao

class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<Book>> = bookDao.readAllData()

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun searchBook(searchQuery: String) : LiveData<List<Book>> {
        return bookDao.searchBook(searchQuery)
    }

}