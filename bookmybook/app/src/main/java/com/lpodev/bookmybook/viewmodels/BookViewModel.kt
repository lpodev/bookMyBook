package com.lpodev.bookmybook.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.data.BookMyBookDatabase
import com.lpodev.bookmybook.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application : Application) : AndroidViewModel(application) {
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books
    private val repository: BookRepository

    init {
        val bookDao = BookMyBookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        viewModelScope.launch {
            _books.value = repository.readAllData()
        }

    }

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun searchBook(searchQuery: String) {
        viewModelScope.launch {
            _books.value = repository.searchBook(searchQuery)
        }
    }

    fun deleteBook(book: Book){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteBook(book)
        }
    }

    fun getAllBooks(){
        viewModelScope.launch(Dispatchers.IO){
            _books.postValue(repository.readAllData())
        }
    }
}