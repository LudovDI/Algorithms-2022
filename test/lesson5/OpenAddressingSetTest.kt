package lesson5

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*

class OpenAddressingSetTest : AbstractOpenAddressingSetTest() {

    override fun <T : Any> create(bits: Int): MutableSet<T> {
        return OpenAddressingSet(bits)
    }

    @Test
    @Tag("Example")
    fun addTestJava() {
        doAddTest()
    }

    @Test
    @Tag("7")
    fun removeTestJava() {
        doRemoveTest()
        val openAddressingSet = create<String>(3)
        assertFalse {
            openAddressingSet.remove("abcde")
        }
        openAddressingSet.add("abcde")
        assertTrue {
            openAddressingSet.remove("abcde")
        }
    }

    @Test
    @Tag("5")
    fun iteratorTestJava() {
        doIteratorTest()
        // My Test
        val openAddressingSet = create<String>(3)
        assertFailsWith<NoSuchElementException> {
            openAddressingSet.iterator().next()
        }
        assertFalse {
            openAddressingSet.iterator().hasNext()
        }
        openAddressingSet.add("abcde")
        assertEquals("abcde", openAddressingSet.iterator().next())
        assertTrue {
            openAddressingSet.iterator().hasNext()
        }
    }

    @Test
    @Tag("8")
    fun iteratorRemoveTestJava() {
        doIteratorRemoveTest()
        // My Test
        val openAddressingSet = create<String>(3)
        assertFailsWith<IllegalStateException> {
            openAddressingSet.iterator().remove()
        }
        openAddressingSet.add("abcde")
        val iterator = openAddressingSet.iterator()
        iterator.next()
        iterator.remove()
        assertEquals(0, openAddressingSet.size)
    }
}