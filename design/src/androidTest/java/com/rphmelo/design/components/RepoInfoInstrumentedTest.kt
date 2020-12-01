package com.rphmelo.design.components

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rphmelo.design.rules.ViewTestRule
import com.rphmelo.design.test.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoInfoInstrumentedTest {

    @get:Rule
    val viewTest: ViewTestRule<RepoInfo> = ViewTestRule(R.layout.test_repo_info)

    @Test
    fun givenRepoInfoIsShowing_whenSetTitleAndDescription_thenShowTheGivenTexts() {
        val title = "title"
        val description = "body"
        viewTest.runOnMainSynchronously(object : ViewTestRule.Runner<RepoInfo> {
            override fun run(view: RepoInfo?) {
                view?.setTitle(title)
                view?.setDescription(description)
            }
        })

        Espresso.onView(ViewMatchers.withId(R.id.tvTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(title)))

        Espresso.onView(ViewMatchers.withId(R.id.tvDescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(description)))
    }
}