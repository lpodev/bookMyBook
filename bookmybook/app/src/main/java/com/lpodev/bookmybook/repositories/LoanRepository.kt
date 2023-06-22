package com.lpodev.bookmybook.repositories

import com.lpodev.bookmybook.data.LoanDao
import com.lpodev.bookmybook.data.LoanWithBook
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.models.Loan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoanRepository(private val loanDao: LoanDao) {

    suspend fun getLoans(): List<Loan> {
        return withContext(Dispatchers.IO) {
            loanDao.getLoans()
        }
    }

    suspend fun addLoan(loan: Loan) {
        loanDao.insertLoan(loan)
    }

    suspend fun deleteLoan(loan: Loan) {
        loanDao.deleteLoan(loan)
    }

    suspend fun getLoansWithBooks(): List<LoanWithBook> {
        return withContext(Dispatchers.IO) {
            loanDao.getLoansWithBooks()
        }
    }
}