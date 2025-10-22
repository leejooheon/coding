package com.coding.app.week1

import junit.framework.TestCase.assertEquals
import org.junit.Test
import kotlin.collections.forEach
import kotlin.test.assertContentEquals

class Anagram {
    @Test
    fun test() {
        val tests = listOf(
            Pair("anagram", "nagaram") to true,
            Pair("rat", "car") to false,
            Pair("listen", "silent") to true,
            Pair("hello", "billion") to false,
            Pair("aabbcc", "abcabc") to true,
            Pair("abcd", "dcba") to true,
            Pair("abcd", "abce") to false,
            Pair("", "") to true,
            Pair("a", "a") to true,
            Pair("a", "b") to false
        )

        tests.forEach { (case, expected) ->
            val result = anagram(case.first, case.second)
            assertEquals(expected,  result)
        }
    }

    @Test
    fun test2() {
        val tests = listOf(
            // ✅ 1️⃣ 기본 예제
            arrayOf("eat", "tea", "tan", "ate", "nat", "bat") to
                    listOf(
                        listOf("eat", "tea", "ate"),
                        listOf("tan", "nat"),
                        listOf("bat")
                    ),

            // ✅ 2️⃣ 모두 애너그램인 경우
            arrayOf("abc", "cab", "bca") to
                    listOf(listOf("abc", "cab", "bca")),

            // ✅ 3️⃣ 하나도 겹치지 않는 경우
            arrayOf("abc", "def", "ghi") to
                    listOf(listOf("abc"), listOf("def"), listOf("ghi")),

            // ✅ 4️⃣ 빈 문자열 포함 (""도 서로 애너그램)
            arrayOf("", "") to
                    listOf(listOf("", "")),

            arrayOf("", "a") to
                    listOf(listOf(""), listOf("a")),

            arrayOf("ab", "ba", "ab") to
                    listOf(listOf("ab", "ba", "ab")),

            // ✅ 6️⃣ 한 글자씩만 존재
            arrayOf("a", "b", "c") to
                    listOf(listOf("a"), listOf("b"), listOf("c")),
        )

        tests.forEach { (param, expected) ->
            val result = groupAnagram(param)
            assertContentEquals(expected, result)
        }
    }

    private fun groupAnagram(strs: Array<String>): List<List<String>> {
        val resultMap = mutableMapOf<String, MutableList<Int>>()

        strs.forEachIndexed { index, s ->
            val signature = signature26(s)
            val indices = resultMap.getOrPut(signature, { mutableListOf() })
            resultMap[signature] = indices.apply { add(index) }
        }

        val result = resultMap.values
            .map { indices -> indices.map { strs[it] } }

        return result
    }

    private fun signature26(s: String): String {
        val charCount = IntArray(26) { 0 }

        s.forEach {
            val index = it - 'a'
            charCount[index] += 1
        }

        return charCount.joinToString { it.toString() }
    }

    private fun anagram(s: String, t: String): Boolean {
        if(s.length != t.length) return false

        val s1 = signature26(s)
        val t1 = signature26(t)
        return s1 == t1

//        val charCount = IntArray(26) { 0 }
//        repeat(s.length) { index ->
//            val sAlphabetIndex = s[index] - 'a'
//            val tAlphabetIndex = t[index] - 'a'
//
//            charCount[sAlphabetIndex] += 1
//            charCount[tAlphabetIndex] -= 1
//        }
//
//        return charCount.all { it == 0 }
    }
}