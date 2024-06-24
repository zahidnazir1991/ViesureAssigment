package com.viesure.assigment.util

import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.UnsupportedEncodingException

@RunWith(Parameterized::class)
class URIUtilsTest(
    private val input: String?,
    private val expectedEncoded: String?,
    private val expectedDecoded: String?
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: encodeUri({0})={1}, decodeUri({1})={2}")
        fun data(): Collection<Array<String?>> {
            return listOf(
                arrayOf(
                    "https://c27b2d72-8d9c-4aa0-b549-7ae7e5666815.mock.pstmn.io/",
                    "https%3A%2F%2Fc27b2d72-8d9c-4aa0-b549-7ae7e5666815.mock.pstmn.io%2F",
                    "https://c27b2d72-8d9c-4aa0-b549-7ae7e5666815.mock.pstmn.io/"
                ),
                arrayOf(null, null, null),
                arrayOf("viesure It!", "viesure+It%21", "viesure It!"),
                arrayOf("", "", "")
            )
        }
    }

    @Test
    fun `test encodeUri`() {
        val result = encodeUri(input)
        assertEquals(expectedEncoded, result)
    }


    fun `test decodeUri`() {
        val result = decodeUri(expectedEncoded)
        assertEquals(expectedDecoded, result)
    }
}