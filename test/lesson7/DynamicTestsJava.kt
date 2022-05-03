package lesson7

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*

class DynamicTestsJava : AbstractDynamicTests() {

    @Test
    @Tag("5")
    fun testLongestCommonSubSequenceJava() {
        longestCommonSubSequence { first, second -> JavaDynamicTasks.longestCommonSubSequence(first, second) }
        // My Test
        assertEquals("", JavaDynamicTasks.longestCommonSubSequence("", "1111"))
        assertEquals("", JavaDynamicTasks.longestCommonSubSequence("", ""))
    }

    @Test
    @Tag("7")
    fun testLongestIncreasingSubSequenceJava() {
        longestIncreasingSubSequence { JavaDynamicTasks.longestIncreasingSubSequence(it) }
        // My Test
        assertEquals(listOf(1), JavaDynamicTasks.longestIncreasingSubSequence(listOf(1, 1, 1, 1, 1, 1, 1, 1)))
        assertEquals(listOf(10), JavaDynamicTasks.longestIncreasingSubSequence(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)))
        assertEquals(listOf(13, 14, 15, 16, 17, 18, 19),
            JavaDynamicTasks.longestIncreasingSubSequence(listOf(13, 14, 15, 1, 2, 8, 16, 17, 18, 19)))
    }

    @Test
    @Tag("4")
    fun testShortestPathOnFieldJava() {
        shortestPathOnField { JavaDynamicTasks.shortestPathOnField(it) }
    }
}