package com.coding.app.week1

import org.junit.Test
import kotlin.test.assertEquals

class AA {
    @Test
    fun test() {
        val tests = listOf(
//            Pair("abcabcbb", 3),
//            Pair("bbbbb", 1),
            Pair("pwwkew", 3),
//            Pair("aab", 2),
//            Pair("dvdf", 3),
        )

        tests.forEach { (input, expected) ->
            val result = longestSubstringWithoutRepeatingCharacters(input)
            assertEquals(expected,  result)
        }
    }

    private fun longestSubstringWithoutRepeatingCharacters(s: String): Int {
        val set = mutableSetOf<Char>()
        var left = 0
        var best = 0

        s.forEachIndexed { right, ch ->
            while(set.contains(ch)) {
                set.remove(s[left])
                left += 1
            }
            set.add(ch)

            val length = right - left + 1
            best = maxOf(best, length)
        }

        return best
    }
}