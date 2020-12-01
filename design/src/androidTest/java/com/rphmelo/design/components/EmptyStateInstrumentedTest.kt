package com.rphmelo.design.components

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rphmelo.design.test.R
import com.rphmelo.design.rules.ViewTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmptyStateInstrumentedTest {

    @get:Rule
    val viewTest: ViewTestRule<EmptyState> = ViewTestRule(R.layout.test_empty_state)

    @Test
    fun givenEmptyStateIsShowing_whenSetTitleAndBody_thenShowTheGivenTexts() {
        val title = "title"
        val body = "body"
        viewTest.runOnMainSynchronously(object : ViewTestRule.Runner<EmptyState> {
            override fun run(view: EmptyState?) {
                view?.setEmptyStateBody(body)
                view?.setEmptyStateTitle(title)
            }
        })

        onView(withId(R.id.tvTitleEmptyState))
            .check(matches(isDisplayed()))
            .check(matches(withText(title)))

        onView(withId(R.id.tvEmptyStateBody))
            .check(matches(isDisplayed()))
            .check(matches(withText(body)))
    }
}