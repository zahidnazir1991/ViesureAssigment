package com.viesure.assigment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viesure.assigment.database.tables.EncryptedBook

@Dao
interface BooksDao {

    @Query("SELECT * FROM tblBooks")
    fun getAllBooks(): List<EncryptedBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(books: List<EncryptedBook>)
}