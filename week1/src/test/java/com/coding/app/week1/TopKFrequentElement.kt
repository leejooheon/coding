package com.coding.app.week1

import org.junit.Test

class TopKFrequentElement {
    @Test
    fun test() {
        val tests = listOf(
            Pair(intArrayOf(1,1,1,2,2,3), 2) to intArrayOf(1,2),
            Pair(intArrayOf(1), 1) to intArrayOf(1),
            Pair(intArrayOf(4,4,4,4,5,5,6), 2) to intArrayOf(4,5),
            Pair(intArrayOf(1,2,3,4,5,6), 3) to intArrayOf(1,2,3), // all unique
            Pair(intArrayOf(1,1,1,1,1), 1) to intArrayOf(1),       // all same
        )

        for ((input, expected) in tests) {
            val result = topKFrequent(input.first, input.second)
            assert(result.contentEquals(expected))
        }
    }

    private fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val frequencyMap = mutableMapOf<Int, Int>()
        nums.forEach {
            val count = frequencyMap.getOrPut(it, { 0 })
            frequencyMap[it] = count + 1
        }

        val result = frequencyMap.entries
            .sortedByDescending { it.value }
            .take(k)
            .map { it.key }
            .toIntArray()

        return result
    }
}