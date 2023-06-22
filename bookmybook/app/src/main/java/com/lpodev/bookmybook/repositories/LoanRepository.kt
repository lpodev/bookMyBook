package com.lpodev.bookmybook.repositories

import com.lpodev.bookmybook.data.LoanDao
import com.lpodev.bookmybook.models.Loan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoanRepository(private val loanDao: LoanDao) {

    suspend fun getLoans(): List<Loan> {
        return withContext(Dispatchers.IO) {
            loanDao.getLoans()
        }
    }

    suspend fun addBook(loan: Loan) {
        loanDao.insertLoan(loan)
    }

}