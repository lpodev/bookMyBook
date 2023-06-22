package com.lpodev.bookmybook.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lpodev.bookmybook.models.Book
import com.lpodev.bookmybook.models.Loan

@Database(entities = [Book::class, Loan::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookMyBookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    abstract fun loanDao() : LoanDao

    companion object {
        @Volatile
        private var INSTANCE: BookMyBookDatabase? = null

        fun getDatabase(context:Context) : BookMyBookDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    BookMyBookDatabase::class.java,
                    "bmb_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}