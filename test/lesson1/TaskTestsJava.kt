package lesson1

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class TaskTestsJava : AbstractTaskTests() {

    @Test
    @Tag("3")
    fun testSortTimesJava() {
        sortTimes { inputName, outputName -> JavaTasks.sortTimes(inputName, outputName) }
        try {
            JavaTasks.sortTimes("input/myTest3.txt", "temp.txt")
            assertFileContent(
                "temp.txt",
                """
                     12:00:50 AM
                     12:00:51 AM
                     12:00:52 AM
                     12:00:53 AM
                     12:00:54 AM
                     12:00:55 AM
                     12:00:56 AM
                     12:00:57 AM
                     12:00:58 AM
                     12:00:59 AM
                     12:01:00 AM
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
    }


    @Test
    @Tag("4")
    fun testSortAddressesJava() {
        sortAddresses { inputName, outputName -> JavaTasks.sortAddresses(inputName, outputName) }
    }

    @Test
    @Tag("4")
    fun testSortTemperaturesJava() {
        sortTemperatures { inputName, outputName -> JavaTasks.sortTemperatures(inputName, outputName) }
        try {
            JavaTasks.sortTemperatures("input/myTest2.txt", "temp.txt")
            assertFileContent(
                "temp.txt",
                """
                    -0.9
                    -0.8
                    -0.7
                    -0.6
                    -0.5
                    -0.4
                    -0.3
                    -0.2
                    -0.1
                    0.0
                    0.1
                    0.2
                    0.3
                    0.4
                    0.5
                    0.6
                    0.7
                    0.8
                    0.9
                """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    @Tag("4")
    fun testSortSequenceJava() {
        sortSequence { inputName, outputName -> JavaTasks.sortSequence(inputName, outputName) }
        try {
            JavaTasks.sortSequence("input/myTest1.txt", "temp.txt")
            assertFileContent(
                "temp.txt",
                """
                        2
                        1
                        1
                        1
                        1
                        1
                        1
                        1
                        1
                        1
                    """.trimIndent()
            )
        } finally {
            File("temp.txt").delete()
        }
    }

    @Test
    @Tag("2")
    fun testMergeArraysJava() {
        mergeArrays { first, second -> JavaTasks.mergeArrays<Int?>(first, second) }
    }
}
