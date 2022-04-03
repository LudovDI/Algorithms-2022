package lesson2

import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class AlgorithmsTestsJava : AbstractAlgorithmsTests() {
    @Test
    @Tag("2")
    fun testOptimizeBuyAndSellJava() {
        optimizeBuyAndSell { JavaAlgorithms.optimizeBuyAndSell(it) }
    }

    @Test
    @Tag("2")
    fun testJosephTaskJava() {
        josephTask { menNumber, choiceInterval -> JavaAlgorithms.josephTask(menNumber, choiceInterval) }
    }

    @Test
    @Tag("4")
    fun testLongestCommonSubstringJava() {
        longestCommonSubstring { first, second -> JavaAlgorithms.longestCommonSubstring(first, second) }
        assertEquals(
            "Умом — Россию не понять,\nАршином общим не измерить:\nУ ней особенная стать —\nВ Россию можно только верить.",
            JavaAlgorithms.longestCommonSubstring(
                """
Умом — Россию не понять,
Аршином общим не измерить:
У ней особенная стать —
В Россию можно только верить.
                """.trimIndent(),
                """
Умом — Россию не понять,
Аршином общим не измерить:
У ней особенная стать —
В Россию можно только верить.
                """.trimIndent()
            )
        )
    }

    @Test
    @Tag("3")
    fun testCalcPrimesNumberJava() {
        calcPrimesNumber { JavaAlgorithms.calcPrimesNumber(it) }
        assertEquals(5, JavaAlgorithms.calcPrimesNumber(11))
    }
}