// metrics.kt
package projectFiles

/**
 * Stores performance metrics for a maze-solving algorithm.
 *
 * @property nodesVisited The total number of nodes visited during the search.
 * @property pathLength The number of steps in the final path.
 * @property runtimeMs The total runtime of the algorithm in milliseconds.
 */
data class Metrics(
    val nodesVisited: Int,
    val pathLength: Int,
    val runtimeMs: Double
) {
    /**
     * Returns a formatted string representation of the metrics.
     */
    override fun toString(): String {
        return """
            Nodes Visited: $nodesVisited
            Path Length: $pathLength
            Runtime: ${"%.3f".format(runtimeMs)} ms
        """.trimIndent()
    }
}