package com.coding.app.week1

import io.mockk.InternalPlatformDsl.toArray
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class TwoSum {
    @Test
    fun test() = runTest {
        val tests = listOf(
            (intArrayOf(2, 11, 7, 15) to 9) to intArrayOf(0, 2),  // 2 + 7 = 9
            (intArrayOf(3, 3) to 6) to intArrayOf(0, 1),
            (intArrayOf(-1, -2, -3, -4, -5) to -8) to intArrayOf(2, 4), // -3 + -5 = -8
            (intArrayOf(-10, -3, 2, 8, 13) to 5) to intArrayOf(1, 3),   // -3 + 8 = 5 ✅ 단일 해답
            (intArrayOf(1000000, 500000, -1500000) to -1000000) to intArrayOf(1, 2), // 500000 + (-1500000)
            (intArrayOf(0, 4, 3, 0) to 0) to intArrayOf(0, 3),          // 0 + 0 = 0
            (intArrayOf(9, 1, 5, 3, 7) to 16) to intArrayOf(0, 4)       // 9 + 7 = 16
        )

        for ((input, expected) in tests) {
            val result = twoSum(input.first, input.second)
            assertContentEquals(expected, result)
        }
    }

    private fun twoSum(nums: IntArray, target: Int): IntArray {
        val result = mutableListOf<Int>()
        nums.forEachIndexed { index, num ->
            val complement = target - num
            val complementIndex = nums.lastIndexOf(complement)

            if(complementIndex != -1) {
                result.addAll(listOf(index, complementIndex))
                return result.toIntArray()
            }
        }
        return intArrayOf()
    }

    @Test
    fun `exampleTest`() = runTest {
        var array = intArrayOf(2,7,11,15)
        repeat(4) {
            array = rotate(array)
            println("rotated: ${array.toList()}")
        }

        val a = arrayOf(
            intArrayOf(1,3),
            intArrayOf(7,10),
            intArrayOf(15,18),
            intArrayOf(2,6),
            intArrayOf(1, 2),
        )
        test(a)

        assertEquals(2, 1 + 1)
    }

    private fun test(A: Array<IntArray>) {
        fun shouldMerge(left: IntArray, right: IntArray): Boolean {
            // (a, b), (c,d) => a보다 d가 같거나 크고, b가 c보다 같거나 커야 겹침
            return left.first() <= right.last() && left.last() >= right.first()
        }

        val result = mutableListOf<IntArray>()
        val sorted = A.sortedBy { it.first() }
        var left = sorted.first()

        sorted.drop(1).forEach { right ->
            val arr = if(shouldMerge(left, right)) {
                intArrayOf(
                    minOf(left.first(), right.first()),
                    maxOf(left.last(), right.last())
                )
            } else {
                result.add(left)
                right
            }
            left = arr
        }.also {
            result.add(left)
        }

        println("result: ${result.map { it.toList() }}")
    }

    private fun rotate(array: IntArray): IntArray {
        val newArray = MutableList(array.size) { 0 }
        array.forEachIndexed { index, i ->
            val value = array[index]
            newArray[(index + 1) % array.size] = value
        }
        return newArray.toIntArray()
    }
}
