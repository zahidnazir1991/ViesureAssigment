package com.viesure.assigment

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun onLaunch() {

        composeTestRule.waitForIdle()
        val allNodes = composeTestRule.onAllNodesWithTag("book_").fetchSemanticsNodes()
        allNodes.forEach {
            println("Found node with tag: book_")
        }

        composeTestRule.onAllNodesWithTag("bookItem")[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("bookItem")[1].assertIsDisplayed()
        composeTestRule.onAllNodesWithTag("bookItem")[1].performClick()
        composeTestRule.waitForIdle()

    }

}