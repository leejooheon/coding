package com.coding.app.week1

import org.junit.Test

class Rob {
    @Test
    fun test() {
        val tests = listOf(
            intArrayOf(2,7,9,3,1) to 12,
            intArrayOf(1,2,3,1) to 4,
            intArrayOf(2,1,1,2) to 4,
            intArrayOf(0) to 0,
            intArrayOf(1) to 1,
            intArrayOf(1,2) to 2,
            intArrayOf(2,1) to 2,
            intArrayOf(100,1,1,100) to 200
        )

        for ((input, expected) in tests) {
            val result = rob(input)
            assert(result == expected)
        }
    }

    private fun rob(nums: IntArray): Int {
        val dp = IntArray(nums.size)
        nums.forEachIndexed { index, num ->
            val takeCurrent = nums[index] + dp.getOrElse(index - 2, {0})
            val skipCurrent = dp.getOrElse(index - 1, {0})
            val best = maxOf(takeCurrent, skipCurrent)
            dp[index] = best
        }

        return dp.max()
    }
}