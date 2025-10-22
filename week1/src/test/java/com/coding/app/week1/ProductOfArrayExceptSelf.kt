package com.coding.app.week1

import org.junit.Test
import kotlin.collections.map

class ProductOfArrayExceptSelf {
    @Test
    fun test() {
        val tests = listOf(
            intArrayOf(1,2,3,4) to intArrayOf(24,12,8,6),
            intArrayOf(-1, 1, 0, -3, 3) to intArrayOf(0, 0, 9, 0, 0),
        )

        for ((input, expected) in tests) {
            val result = productExceptSelf(input)
            assert(result.contentEquals(expected))
        }
    }

    private fun productExceptSelf(nums: IntArray): IntArray {
//        val result = nums.mapIndexed { index, num ->
//            val left = nums.take(index)
//            val right = nums.drop(index + 1)
//            (left + right).reduce { acc, i -> acc * i }
//        }
//
//        return result.toIntArray()


        val prefix = IntArray(nums.size)
        var prefixAcc = 1
        nums.indices.forEach { index ->
            prefix[index] = prefixAcc
            prefixAcc = nums[index] * prefixAcc
        }

        val suffix = IntArray(nums.size)
        var suffixAcc = 1
        nums.indices.reversed().forEach { index ->
            suffix[index] = suffixAcc
            suffixAcc = nums[index] * suffixAcc
        }

        val result = nums.indices
            .map { prefix[it] * suffix[it] }

        return result.toIntArray()
    }
}