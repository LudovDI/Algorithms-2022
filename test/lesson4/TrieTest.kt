package lesson4

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*

class TrieTest : AbstractTrieTest() {

    override fun create(): MutableSet<String> =
        Trie()

    @Test
    @Tag("Example")
    fun generalTestJava() {
        doGeneralTest()
    }

    @Test
    @Tag("7")
    fun iteratorTestJava() {
        doIteratorTest()
        // My Test
        val trieSet = create()
        assertFailsWith<NoSuchElementException> {
            trieSet.iterator().next()
        }
        assertFalse {
            trieSet.iterator().hasNext()
        }
        trieSet.add("aaaaa")
        assertEquals("aaaaa", trieSet.iterator().next())
        assertTrue {
            trieSet.iterator().hasNext()
        }
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
        // My Test
        val trieSet = create()
        assertFailsWith<IllegalStateException> {
            trieSet.iterator().remove()
        }
        trieSet.add("aaaaa")
        val iterator = trieSet.iterator()
        iterator.next()
        iterator.remove()
        assertEquals(0, trieSet.size)
    }
}