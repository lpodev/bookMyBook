package com.lpodev.bookmybook.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.data.BookMyBookDatabase
import com.lpodev.bookmybook.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application : Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Book>>
    private val repository: BookRepository

    init {
        val bookDao = BookMyBookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        readAllData = repository.readAllData
    }

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun searchBook(searchQuery: String) : LiveData<List<Book>> {
        return repository.searchBook(searchQuery)
    }
}