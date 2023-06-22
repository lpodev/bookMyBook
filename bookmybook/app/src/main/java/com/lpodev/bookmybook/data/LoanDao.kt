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

}