package com.lpodev.bookmybook.data

import androidx.lifecycle.LiveData

class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<Book>> = bookDao.readAllData()

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun searchBook(searchQuery: String) : LiveData<List<Book>> {
        return bookDao.searchBook(searchQuery)
    }

}