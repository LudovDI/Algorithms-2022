package lesson3

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*


class BinarySearchTreeTest : AbstractBinarySearchTreeTest() {

    override fun create(): CheckableSortedSet<Int> =
        BinarySearchTree()

    @Test
    @Tag("Example")
    fun initTestJava() {
        doInitTest()
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("Example")
    fun firstAndLastTestJava() {
        doFirstAndLastTest()
    }

    @Test
    @Tag("5")
    fun removeTestJava() {
        doRemoveTest()
        val binarySet = create()
        assertFalse(binarySet.remove(0))
        assertFalse(binarySet.remove(null))
        binarySet.add(1)
        assertFalse(binarySet.remove(2))
        assertTrue(binarySet.remove(1))
    }

    @Test
    @Tag("5")
    fun iteratorTestJava() {
        doIteratorTest()
        val binarySet = create()
        assertFailsWith<NoSuchElementException>("Something was supposedly returned after the elements ended") {
            binarySet.iterator().next()
        }
        assertFailsWith<NoSuchElementException>("Something was supposedly returned after the elements ended") {
            binarySet.iterator().hasNext()
        }
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
    }

    @Test
    @Tag("5")
    fun subSetTestJava() {
        doSubSetTest()
    }

    @Test
    @Tag("8")
    fun subSetRelationTestJava() {
        doSubSetRelationTest()
    }

    @Test
    @Tag("7")
    fun subSetFirstAndLastTestJava() {
        doSubSetFirstAndLastTest()
    }

    @Test
    @Tag("4")
    fun headSetTestJava() {
        doHeadSetTest()
    }

    @Test
    @Tag("7")
    fun headSetRelationTestJava() {
        doHeadSetRelationTest()
    }

    @Test
    @Tag("4")
    fun tailSetTestJava() {
        doTailSetTest()
    }

    @Test
    @Tag("7")
    fun tailSetRelationTestJava() {
        doTailSetRelationTest()
    }

}