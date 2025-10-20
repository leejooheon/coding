package com.coding.app.test1

import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

internal class Solution3 {
    @Test
    fun test3() = runTest {
        val testCases = listOf(
            // ✅ 기본 예제
            Pair(intArrayOf(2,4, 1,3, 4,6, 2,4, 1,6), 3),
            Pair(intArrayOf(5,1, 2,6, 6,1, 3,1, 4,3, 4,3, 4,6, 1,2, 4,1, 6,2), 7),
            Pair(intArrayOf(1,5, 3,3, 1,3), 2),
            Pair(intArrayOf(3,4), 0),

            // ✅ 엣지 케이스
            Pair(intArrayOf(), 0),                            // noDominoes
            Pair(intArrayOf(6,6), 0),                         // singleDomino
            Pair(intArrayOf(1,2, 2,3, 3,4, 4,5), 0),          // allConnected
            Pair(intArrayOf(1,2, 3,4, 5,6), 2),               // allDisconnected
            Pair(intArrayOf(1,2, 2,3, 3,1, 5,5), 1),          // midBreak
            Pair(intArrayOf(1,1, 1,1, 1,1, 1,1), 0),          // repeatedSameNumbers
            Pair(intArrayOf(2,3, 3,4, 1,5, 5,6, 6,6, 1,1), 3) // randomCase
        )
        for ((input, expected) in testCases) {
            val result = solution3(input)
            assertEquals(expected, result)
        }
    }

    private fun solution3(A: IntArray): Int {
        val totalCount = A.size / 2
        if (totalCount <= 1) return 0
        val maxChainEndingAt = IntArray(7)

        var best = 0
        A.toList().chunked(2).forEach { (left, right) ->
            val chainLength = maxOf(1, maxChainEndingAt[left] + 1)
            if (chainLength > maxChainEndingAt[right]) maxChainEndingAt[right] = chainLength
            if (maxChainEndingAt[right] > best) best = maxChainEndingAt[right]
        }

        val result = totalCount - best
        return result
    }
}