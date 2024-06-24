package com.viesure.assigment.util
import com.google.gson.JsonSyntaxException
import com.viesure.assigment.models.BookDetailsResponseItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.net.URLDecoder
import java.net.URLEncoder

class UtilMethodKtTest {

    @Test
    fun formatDate() {
        //for valid input date
        val inputDate = "6/20/2024"
        val expectedOutput = "Thu, Jun 20, 24"
        val result = formatDate(inputDate)
        assertEquals(expectedOutput, result)
    }

    @Test
    fun `test formatDate with invalid input`() {
        //for invalid input date
        val inputDate = "invalid-date"
        val result = formatDate(inputDate)
        assertEquals(inputDate, result)
    }


    @Test
    fun `test objectToJSONString with valid object`() {
        val book = BookDetailsResponseItem(
            id = 1,
            title = "To Kill a Mockingbird",
            description = "Harper Lee",
            author = "Harper Lee",
            release_date = "7/11/1960",
            image = "https://dummyimage.com/366x582.png/5fa2dd/ffffff"
        )
        val expectedJson = "{\"id\":1,\"title\":\"To Kill a Mockingbird\",\"description\":\"Harper Lee\",\"author\":\"Harper Lee\",\"release_date\":\"7/11/1960\",\"image\":\"https://dummyimage.com/366x582.png/5fa2dd/ffffff\"}"
        val result = objectToJSONString(book)
        assertEquals(expectedJson, result)
    }

    @Test
    fun `test objectToJSONString with null object`() {
        val testObj: BookDetailsResponseItem? = null
        val expectedJson = "null"

        val result = objectToJSONString(testObj)

        assertEquals(expectedJson, result)
    }

    @Test
    fun `test objectToJSONString with complex object`() {
        val book = BookDetailsResponseItem(
            id = 1,
            title = "To Kill a Mockingbird",
            description = "Harper Lee",
            release_date = "7/11/1960",
            author = "Harper Lee",
            image = "https://dummyimage.com/366x582.png/5fa2dd/ffffff"
        )
        val complexObject = mapOf("book" to book, "available" to true)
        val expectedJson = "{\"book\":{\"id\":1,\"title\":\"To Kill a Mockingbird\",\"description\":\"Harper Lee\",\"author\":\"Harper Lee\",\"release_date\":\"7/11/1960\",\"image\":\"https://dummyimage.com/366x582.png/5fa2dd/ffffff\"},\"available\":true}"

        val result = objectToJSONString(complexObject)

        assertEquals(expectedJson, result)
    }


}