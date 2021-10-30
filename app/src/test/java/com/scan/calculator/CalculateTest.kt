package com.scan.calculator

import com.scan.calculator.utils.Calculate
import junit.framework.TestCase.assertEquals
import org.junit.Test


class CalculateTest {

    @Test
    fun add_isCorrect() {
        assertEquals(5, Calculate.add(0,5))
    }
    @Test
    fun minus_isCorrect() {
        assertEquals(-5, Calculate.minus(0,5))
    }
    @Test
    fun multiply_isCorrect() {
        assertEquals(0, Calculate.multiply(0,5))
    }
    @Test
    fun divide_isCorrect() {
        assertEquals(0, Calculate.divide(0,5))
    }
}