package com.thermondo.androidchallenge

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFailedFetchLaunches() {

        // Wait for the ViewModel to fetch data (you may need to add a delay if necessary)
        Thread.sleep(2000)

        // Find and click the Compose button

        composeTestRule.onNodeWithText("Home").performClick()

        composeTestRule.onNodeWithText("No Data. Please try again").assertIsDisplayed()

        composeTestRule.onNodeWithText("Favorite").performClick()

        composeTestRule.onNodeWithText("No Data. Please try again").assertIsDisplayed()

        // You can perform more specific assertions based on your Compose UI layout and data
    }


    @Test
    fun testSuccessFetchLaunches() {

        // Wait for the ViewModel to fetch data
        Thread.sleep(3000)

        composeTestRule.onAllNodes(hasTestTag("item")).apply {
            fetchSemanticsNodes().forEachIndexed { i, _ ->
                get(i).performClick()
            }
        }

        // You can perform more specific assertions based on your Compose UI layout and data
    }

    // Similar tests can be written for other ViewModel functions like fetchUpcomingLaunches(), getNextLaunch(), etc.
}