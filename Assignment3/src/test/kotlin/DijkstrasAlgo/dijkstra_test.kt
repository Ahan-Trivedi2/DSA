package DijkstrasAlgo

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit test class for the Dijkstra shortest path algorithm.
 *
 * This test verifies that the `dijkstra()` function correctly computes
 * the shortest path between two nodes in a directed weighted graph.
 */
class DijkstraTest {

    /**
     * Test case: Verify that dijkstra() returns the correct shortest path
     * from node "A" to node "D" in a simple directed weighted graph.
     */
    @Test
    fun testBasicGraph() {
        // Define a sample graph as an adjacency list.
        // Each node maps to a map of its neighbors and edge weights.
        val graph = mapOf(
            "A" to mapOf("B" to 1.0, "C" to 4.0), // A → B (1), A → C (4)
            "B" to mapOf("C" to 2.0, "D" to 5.0), // B → C (2), B → D (5)
            "C" to mapOf("D" to 1.0),             // C → D (1)
            "D" to emptyMap()                    // D has no outgoing edges
        )

        // Call the Dijkstra function to find the shortest path from "A" to "D"
        val result = dijkstra(graph, "A", "D")

        // Define the expected shortest path: A → B → C → D
        val expected = listOf("A", "B", "C", "D")

        // Assert that the returned path matches the expected path
        assertEquals(expected, result)
    }
}
