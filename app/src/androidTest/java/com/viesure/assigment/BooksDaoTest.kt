package com.viesure.assigment
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.viesure.assigment.database.AppDatabase
import com.viesure.assigment.database.dao.BooksDao
import com.viesure.assigment.database.tables.EncryptedBook
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BooksDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var booksDao: BooksDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries() // For testing purposes only
            .build()
        booksDao = database.booksDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertBooksAndGetAll() = runBlocking {
        val book1 = EncryptedBook(
            id = 1,
            title = "1984",
            author = "George Orwell",
            description = "Dystopian novel",
            image = "image_url",
            release_date = "1949-06-08"
        )
        val book2 = EncryptedBook(
            id = 2,
            title = "Brave New World",
            author = "Aldous Huxley",
            description = "Dystopian novel",
            image = "image_url",
            release_date = "1932-08-30"
        )
        booksDao.insertBooks(listOf(book1, book2))

        val books = booksDao.getAllBooks()
        assertEquals(2, books.size)
        assertTrue(books.contains(book1))
        assertTrue(books.contains(book2))
    }

    @Test
    fun insertBooksAndReplace() = runBlocking {
        val book1 = EncryptedBook(
            id = 1,
            title = "1984",
            author = "George Orwell",
            description = "Dystopian novel",
            image = "image_url",
            release_date = "1949-06-08"
        )
        booksDao.insertBooks(listOf(book1))

        val updatedBook1 = EncryptedBook(
            id = 1,
            title = "1984",
            author = "George Orwell",
            description = "Dystopian novel updated",
            image = "image_url_updated",
            release_date = "1949-06-08"
        )
        booksDao.insertBooks(listOf(updatedBook1))

        val books = booksDao.getAllBooks()
        assertEquals(1, books.size)
        assertEquals(updatedBook1, books[0])
    }
}