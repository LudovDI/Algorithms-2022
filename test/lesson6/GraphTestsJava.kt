package lesson6

import lesson6.impl.GraphBuilder
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*

class GraphTestsJava : AbstractGraphTests() {
    @Test
    @Tag("6")
    fun testFindEulerLoopJava() {
        findEulerLoop { let { JavaGraphTasks.findEulerLoop(it) } }
        //My Test
        val graph1 = GraphBuilder().apply {
            val a = addVertex("A")
        }.build()
        val loop1 = JavaGraphTasks.findEulerLoop(graph1)
        assertEquals(0, loop1.size)
        val graph2 = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            addConnection(a, b)
        }.build()
        val loop2 = JavaGraphTasks.findEulerLoop(graph2)
        assertEquals(0, loop2.size)
        val graph3 = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            addConnection(a, b)
            addConnection(a, c)
            addConnection(b, c)
        }.build()
        val loop3 = JavaGraphTasks.findEulerLoop(graph3)
        assertEquals(3, loop3.size)
    }

    @Test
    @Tag("7")
    fun testMinimumSpanningTreeJava() {
        minimumSpanningTree { let { JavaGraphTasks.minimumSpanningTree(it) } }
        // My Test
        val graph = GraphBuilder().apply {
            val a = addVertex("A")
            val b = addVertex("B")
            val c = addVertex("C")
            val d = addVertex("D")
            addConnection(a, b)
            addConnection(a, c)
            addConnection(a, d)
            addConnection(b, c)
            addConnection(b, d)
            addConnection(c, d)
        }.build()
        val tree = JavaGraphTasks.minimumSpanningTree(graph)
        assertEquals(3, tree.edges.size)
    }

    @Test
    @Tag("10")
    fun testLargestIndependentVertexSetJava() {
        largestIndependentVertexSet { let { JavaGraphTasks.largestIndependentVertexSet(it) } }
    }

    @Test
    @Tag("8")
    fun testLongestSimplePathJava() {
        longestSimplePath { let { JavaGraphTasks.longestSimplePath(it) } }
    }

    @Test
    @Tag("6")
    fun testBaldaSearcherJava() {
        baldaSearcher { inputName, words -> JavaGraphTasks.baldaSearcher(inputName, words) }
    }
}