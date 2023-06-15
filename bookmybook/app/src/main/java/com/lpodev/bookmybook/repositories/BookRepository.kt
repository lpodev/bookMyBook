package com.lpodev.bookmybook.repositories

import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.data.BookDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookRepository(private val bookDao: BookDao) {

    suspend fun readAllData(): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDao.readAllData()
        }
    }

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    suspend fun searchBook(searchQuery: String): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDao.searchBook(searchQuery)
        }
    }

    suspend fun deleteBook(book: Book){
        bookDao.deleteBook(book)
    }

}