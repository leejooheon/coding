package com.coding.app.week1

import org.junit.Test

class BestBuyAndSell {
    @Test
    fun test() {
        val tests = listOf(
            intArrayOf(7, 1, 5, 3, 6, 4) to 5,
        )

        for ((input, expected) in tests) {
            val result = solution(input)
            assert(result == expected)
        }
    }

    private fun solution(prices: IntArray): Int {
        var best = 0
        var minPrice = Int.MAX_VALUE
        prices.forEachIndexed { index, price ->
            val profit = price - minPrice
            best = maxOf(best, profit)
            minPrice = minOf(minPrice, price)
        }

        return best
    }
}