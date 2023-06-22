package com.lpodev.bookmybook.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.models.Loan

@Dao
interface LoanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLoan(loan: Loan)

    @Query("SELECT * FROM loans_table ORDER BY start_date ASC")
    fun getLoans(): List<Loan>

    @Delete
    suspend fun deleteLoan(loan: Loan)

    @Query("SELECT loans_table.*, books_table.id AS bookId, books_table.title FROM loans_table INNER JOIN books_table ON loans_table.book_id = books_table.id")
    suspend fun getLoansWithBooks(): List<LoanWithBook>
}