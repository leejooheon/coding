package com.coding.app.test1

import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

internal class Solution1 {
    @Test
    fun test1() = runTest {
        val tests = listOf(
            intArrayOf(1, 10, 12, 3) to 8,
            intArrayOf(2, 29, 3) to 7,
            intArrayOf(100, 101, 102, 103) to 208,
            intArrayOf(55) to 1,
            intArrayOf(7) to 7,
            intArrayOf(99) to 9,
            intArrayOf(9999) to 9,
            intArrayOf(10, 20, 30, 40) to 37,
            intArrayOf(98, 89) to 16,
            intArrayOf(10000, 1, 9999) to 11
        )

        for ((input, expected) in tests) {
            val result = solution1(input)
            assertEquals(expected, result)
        }
    }

    private fun solution1(A: IntArray): Int {
        var total = 0
        val diffs = mutableListOf<Int>()

        A.forEach { num ->
            total += num

            val step1 = sumDigits(num)
            val step2 = sumDigits(step1)

            val diff1 = num - step1
            val diff2 = num - step2

            diffs += maxOf(diff1, diff2)
        }

        diffs.sortDescending()

        val bestReduction = diffs.take(2).sum()
        return total - bestReduction
    }

    private fun sumDigits(num: Int): Int {
        var value = num
        var acc = 0
        while (value > 0) {
            acc += value % 10
            value /= 10
        }
        return acc
    }
}
