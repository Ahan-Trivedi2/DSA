// metrics.kt
package projectFiles

// Metrics class
data class Metrics(
    val nodesVisited: Int,
    val pathLength: Int,
    val runtimeMs: Double
) {
    override fun toString(): String {
        return """
            Nodes Visited: $nodesVisited
            Path Length: $pathLength
            Runtime: ${"%.3f".format(runtimeMs)} ms
        """.trimIndent()
    }
}