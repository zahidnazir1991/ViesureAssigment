package com.viesure.assigment.repository

import android.content.Context
import android.util.Log
import com.viesure.assigment.api.ApiResult
import com.viesure.assigment.api.ApiService
import com.viesure.assigment.api.performApiCall
import com.viesure.assigment.database.dao.BooksDao
import com.viesure.assigment.database.tables.EncryptedBook
import com.viesure.assigment.models.BookDetailsResponse
import com.viesure.assigment.models.BookDetailsResponseItem
import com.viesure.assigment.util.EncryptionHelper
import com.viesure.assigment.util.EncryptionHelper.decrypt

import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.crypto.SecretKey
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService,
    @ApplicationContext private val context: Context,
    private val booksDao: BooksDao
) {
    private val secretKey: SecretKey = EncryptionHelper.getOrCreateKey(context)
    suspend fun getBooks(): ApiResult<BookDetailsResponse?> {
        return performApiCall(apiService.getBooks())
    }

    suspend fun encryptAndSaveBooks(bookslist: BookDetailsResponse) {
        val encryptedBooks = bookslist.map { book ->
            EncryptedBook(
                id = book.id,
                title = EncryptionHelper.encrypt(book.title, secretKey),
                description = EncryptionHelper.encrypt(book.description, secretKey),
                author = EncryptionHelper.encrypt(book.author, secretKey),
                release_date = EncryptionHelper.encrypt(book.release_date, secretKey),
                image = EncryptionHelper.encrypt(book.image, secretKey)
            )
        }

        booksDao.insertBooks(encryptedBooks)
    }

    suspend fun getDecryptedBooks(): List<BookDetailsResponseItem> {
        return try {
            val encryptedBooks = booksDao.getAllBooks()
           var check =  encryptedBooks.get(0).title
            var dec = decrypt(check,secretKey)
            if (encryptedBooks.isEmpty()) {
                Log.d("DecryptedBooks", "No encrypted books found.")
                emptyList()
            } else {
                val decryptedBooks = encryptedBooks.map { book ->
                    BookDetailsResponseItem(
                        id = book.id,
                        title = decrypt(book.title, secretKey),
                        description = decrypt(book.description, secretKey),
                        author = decrypt(book.author, secretKey),
                        release_date = decrypt(book.release_date, secretKey),
                        image = decrypt(book.image, secretKey)
                    )
                }
                Log.d("DecryptedBooks", "Decrypted books: $decryptedBooks")
                decryptedBooks
            }
        } catch (e: Exception) {
            Log.e("DecryptionError", "Error during decryption", e)
            emptyList() // Return an empty list in case of error
        }
    }

}