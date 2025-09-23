package graph

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for the DirectedWeightedGraph class.
 */
class DirectedWeightedGraphTest {

    /**
     * Tests getVertices returns the correct set of vertices.
     */
    @Test
    fun testGetVertices() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 1.0)

        val vertices = graph.getVertices()
        assertTrue("A" in vertices)
        assertTrue("B" in vertices)
        assertEquals(2, vertices.size)
    }

    /**
     * Tests addEdge adds both vertices and the edge with the correct cost.
     */
    @Test
    fun testAddEdge() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 2.5)

        val edgesFromA = graph.getEdges("A")
        assertEquals(1, edgesFromA.size)
        assertEquals(2.5, edgesFromA["B"])
    }

    /**
     * Tests getEdges returns an empty map for a vertex with no outgoing edges.
     */
    @Test
    fun testGetEdgesEmpty() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("X", "Y", 3.0)

        val edgesFromZ = graph.getEdges("Z")
        assertTrue(edgesFromZ.isEmpty())
    }

    /**
     * Tests that addEdge overwrites an existing edge if one already exists.
     */
    @Test
    fun testOverwriteEdge() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("A", "B", 4.0)

        val edgesFromA = graph.getEdges("A")
        assertEquals(1, edgesFromA.size)
        assertEquals(4.0, edgesFromA["B"])
    }

    /**
     * Tests clear removes all vertices and edges from the graph.
     */
    @Test
    fun testClear() {
        val graph = DirectedWeightedGraph<String>()
        graph.addEdge("M", "N", 5.0)
        graph.addEdge("N", "O", 6.0)

        graph.clear()

        assertTrue(graph.getVertices().isEmpty())
        assertTrue(graph.getEdges("M").isEmpty())
        assertTrue(graph.getEdges("N").isEmpty())
    }
}
