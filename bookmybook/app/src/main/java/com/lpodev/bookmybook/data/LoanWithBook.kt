package com.lpodev.bookmybook.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.models.Loan

data class LoanWithBook(
    @Embedded val loan: Loan,

    @Relation(
        parentColumn = "book_id",
        entityColumn = "id"
    )
    val book: Book
) {
    val bookId: Int
        get() = book.id
}
