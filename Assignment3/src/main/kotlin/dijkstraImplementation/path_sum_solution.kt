package dijkstraImplementation

import dijkstraImplementation.readMatrixFromResources
import dijkstraImplementation.buildGraph
import DijkstrasAlgo.dijkstra

/**
 * Entry point for solving the minimum path sum problem using Dijkstra's algorithm.
 *
 * Expected output is the minimum total cost to traverse the matrix under the constraint
 * of only moving right or down.
 */
fun main() {
    val matrix = readMatrixFromResources("matrix.txt")
    val graph = buildGraph(matrix)
    val start = Pair(0, 0)
    val end = Pair(matrix.lastIndex, matrix[0].lastIndex)

    val path = dijkstra(graph, start, end)
    val cost = path?.sumOf { (i, j) -> matrix[i][j] }

    println("Minimum path sum: $cost")
}
