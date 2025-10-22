package com.coding.app.week1

import org.junit.Test

class LongestConsecutiveSequence {
    @Test
    fun test() {

        val tests = listOf(
            intArrayOf(100, 4, 200, 1, 3, 2) to 4,      // 1, 2, 3, 4
            intArrayOf(0,3,7,2,5,8,4,6,0,1) to 9,       // 0, 1, 2, 3, 4, 5, 6, 7, 8
            intArrayOf(1,2,0,1) to 3,                   // 0, 1, 2
            intArrayOf(9,1,-3,2,4,8,-1,0,6,-2,-4,7) to 7 // -4,-3,-2,-1,0,1,2
        )

        for ((input, expected) in tests) {
            val result = longestConsecutive(input)
            assert(result == expected)
        }
    }

    private fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toSet()

        var best = 1
        set.forEachIndexed { index, num ->
            if(set.contains(num - 1)) return@forEachIndexed

            var nextNumber = num
            var length = 1

            while (set.contains(nextNumber + 1)) {
                nextNumber += 1
                length += 1
            }
            best = maxOf(best, length)
        }

        return best
    }
}