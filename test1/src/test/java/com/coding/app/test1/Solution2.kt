package com.coding.app.test1

import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

internal class Solution2 {
    @Test
    fun test2() = runTest {
        val tests = arrayOf(
            arrayOf(2, "1A 2F 1C", 2),
            arrayOf(1, "", 2),
            arrayOf(22, "1A 3C 2B 20G 5A", 41),
            // 단일 행 엣지 케이스
            arrayOf(1, "1B 1C 1D 1E", 1),
            arrayOf(1, "1D 1E 1F 1G", 0),
            arrayOf(1, "1E", 1),
            arrayOf(1, "1F", 1),
            arrayOf(1, "1A 1K", 2),
            arrayOf(1, "1H 1J", 1),
            arrayOf(1, "1B 1E 1F 1J", 0),
            arrayOf(1, "1C 1D", 1),
            arrayOf(1, "1B 1C 1F 1G", 0),
            // 여러 행 및 공백 케이스
            arrayOf(3, "1D 1G 2A 2K 3C 3D 3E", 3),
            arrayOf(2, "   1A   1K  2G   ", 3),
            arrayOf(50, "", 100),
            arrayOf(2, "1C 1D 2G 2H", 2),
            arrayOf(2, "1D 1E 1F 1G", 2)
        )

        for ((n, s, expected) in tests) {
            val result = solution2(n as Int, s as String)
            assertEquals(expected, result)
//            Log.d("Jooheon", "N=$n, S='$s' → $result (expected $expected)")
        }
    }

    private fun solution2(N: Int, S: String): Int {
        val seatGroups = listOf(
            listOf("B", "C", "D", "E"), // 왼쪽 구역
            listOf("D", "E", "F", "G"), // 중앙 구역
            listOf("F", "G", "H", "J")  // 오른쪽 구역
        )

        val taken = S.trim().takeIf { it.isNotEmpty() }
            ?.split(" ")
            ?.filter { it.isNotBlank() }
            ?.toSet()
            ?: emptySet()

        var result = 0
        (1..N).forEach { rowNumber ->
            val zoneA = seatGroups[0].all { seatLabel ->  "$rowNumber$seatLabel" !in taken }
            val zoneB = seatGroups[1].all { seatLabel ->  "$rowNumber$seatLabel" !in taken }
            val zoneC = seatGroups[2].all { seatLabel ->  "$rowNumber$seatLabel" !in taken }

            result += when {
                zoneA && zoneC -> 2
                zoneA || zoneB || zoneC -> 1
                else -> 0
            }
        }
        return result
    }
}