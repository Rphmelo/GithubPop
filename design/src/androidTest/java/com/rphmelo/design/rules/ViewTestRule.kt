package com.rphmelo.design.rules

import android.app.Activity
import android.app.Instrumentation
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.rphmelo.design.R

open class ViewTestRule<T : View> : ActivityTestRule<EmptyActivity> {

    var instrumentation: Instrumentation
    var viewCreator: ViewCreator<T>

    var view: T? = null
        private set

    constructor(@LayoutRes layoutId: Int) : this(InflateFromXmlViewCreator<T>(layoutId)) {}

    constructor(viewCreator: ViewCreator<T>) : this(InstrumentationRegistry.getInstrumentation(), viewCreator) {}

    protected constructor(
        instrumentation: Instrumentation,
        viewCreator: ViewCreator<T>
    ) : super(EmptyActivity::class.java) {
        this.instrumentation = instrumentation
        this.viewCreator = viewCreator
    }

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        val activity = activity
        createView(activity)
        runOnMainSynchronously(object : Runner<T> {
            override fun run(view: T?) {
                activity.setContentView(view, view!!.layoutParams)
            }
        })
    }

    private fun createView(activity: Activity) {
        instrumentation.runOnMainSync {
            view = viewCreator.createView(activity, activity.findViewById<View>(android.R.id.content) as ViewGroup)
            view!!.setTag(R.id.espresso_support__view_test_rule, true)
        }
    }

    fun runOnMainSynchronously(runner: Runner<T>) {
        instrumentation.runOnMainSync { runner.run(view) }
    }

    interface Runner<T> {
        fun run(view: T?)
    }
}