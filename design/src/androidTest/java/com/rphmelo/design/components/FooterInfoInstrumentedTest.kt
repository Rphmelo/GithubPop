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
class FooterInfoInstrumentedTest {

    @get:Rule
    val viewTest: ViewTestRule<FooterInfo> = ViewTestRule(R.layout.test_footer_info)

    @Test
    fun givenFooterInfoIsShowing_whenSetTitleAndBody_thenShowTheGivenTexts() {
        val count = "23"
        viewTest.runOnMainSynchronously(object : ViewTestRule.Runner<FooterInfo> {
            override fun run(view: FooterInfo?) {
                view?.setCount(count)
                view?.setImageView(R.drawable.bg_circular)
            }
        })

        Espresso.onView(ViewMatchers.withId(R.id.tvCount))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(count)))

        Espresso.onView(ViewMatchers.withId(R.id.ivImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}