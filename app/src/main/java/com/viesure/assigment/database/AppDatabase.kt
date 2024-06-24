package com.viesure.assigment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viesure.assigment.database.dao.BooksDao
import com.viesure.assigment.database.tables.EncryptedBook

@Database(
    entities = [EncryptedBook::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}