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
class UserInfoHorizontalInstrumentedTest {

    @get:Rule
    val viewTest: ViewTestRule<UserInfoHorizontal> = ViewTestRule(R.layout.test_user_info_horizontal)

    @Test
    fun givenUserInfoHorizontalIsShowing_whenSetTitleAndBody_thenShowTheGivenTexts() {
        val userName = "google"
        val fullName = "google chrome"
        viewTest.runOnMainSynchronously(object : ViewTestRule.Runner<UserInfoHorizontal> {
            override fun run(view: UserInfoHorizontal?) {
                view?.setUserFullName(fullName)
                view?.setUserName(userName)
                view?.setAvatarUser("https://avatars1.githubusercontent.com/u/1342004?v=4")
            }
        })

        Espresso.onView(ViewMatchers.withId(R.id.tvUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(userName)))

        Espresso.onView(ViewMatchers.withId(R.id.tvUserFullName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(fullName)))

        Espresso.onView(ViewMatchers.withId(R.id.ivAvatarUser))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}