package com.lpodev.bookmybook.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.data.BookMyBookDatabase
import com.lpodev.bookmybook.models.Loan
import com.lpodev.bookmybook.repositories.LoanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoanViewModel (application : Application) : AndroidViewModel(application) {
    private val _loans = MutableLiveData<List<Loan>>()
    val loans: LiveData<List<Loan>>
        get() = _loans
    private val repository: LoanRepository

    init {
        val loanDao = BookMyBookDatabase.getDatabase(application).loanDao()
        repository = LoanRepository(loanDao)
        viewModelScope.launch {
            _loans.value = repository.getLoans()
        }

    }

    fun addLoan(loan: Loan) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLoan(loan)
        }
    }

    fun deleteLoan(loan: Loan){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteLoan(loan)
        }
    }
}