package com.viesure.assigment.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tblBooks"
)
data class EncryptedBook(
    val author: String,
    val description: String,
    @PrimaryKey val id: Int,
    val image: String,
    val release_date: String,
    val title: String
)