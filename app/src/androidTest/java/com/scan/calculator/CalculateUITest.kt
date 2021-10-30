package com.scan.calculator

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CalculateUITest {

    @Test
    fun addTwoNumbers() {
        launchActivity<MainActivity>()
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("3")))
    }
    @Test
    fun minusTwoNumbers() {
        launchActivity<MainActivity>()
        onView(withId(R.id.minus)).perform(click())
        onView(withId(R.id.input_value)).perform(replaceText("2"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("-2")))
    }
    @Test
    fun multiplyTwoNumbers() {
        launchActivity<MainActivity>()
        onView(withId(R.id.multiply)).perform(click())
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("0")))
    }
    @Test
    fun divideTwoNumbers() {
        launchActivity<MainActivity>()
        onView(withId(R.id.division)).perform(click())
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("0")))
    }
    @Test
    fun undoOperation() {
        launchActivity<MainActivity>()
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("3")))
        onView(withId(R.id.undo)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("0")))
    }
    @Test
    fun redoOperation() {
        launchActivity<MainActivity>()
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("3")))
        onView(withId(R.id.undo)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("0")))
        onView(withId(R.id.redo)).perform(click())
        onView(withId(R.id.result_value)).check(matches(withText("3")))
    }

    @Test
    fun checkEqualNotEnabled() {
        launchActivity<MainActivity>()
        onView(withId(R.id.equal)).check(matches(isNotEnabled()))
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.equal)).check(matches(isNotEnabled()))
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).check(matches(isEnabled()))
    }

    @Test
    fun checkUndoNotEnabled() {
        launchActivity<MainActivity>()
        onView(withId(R.id.undo)).check(matches(isNotEnabled()))
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.undo)).check(matches(isNotEnabled()))
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.undo)).check(matches(isEnabled()))
    }

    @Test
    fun checkRedoNotEnabled() {
        launchActivity<MainActivity>()
        onView(withId(R.id.redo)).check(matches(isNotEnabled()))
        onView(withId(R.id.plus)).perform(click())
        onView(withId(R.id.redo)).check(matches(isNotEnabled()))
        onView(withId(R.id.input_value)).perform(replaceText("3"))
        onView(withId(R.id.equal)).perform(click())
        onView(withId(R.id.undo)).perform(click())
        onView(withId(R.id.redo)).check(matches(isEnabled()))
    }
}