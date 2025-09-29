package dijkstraImplementation

/**
 * Converts a 2D matrix of costs into a directed graph representation.
 *
 * @param matrix A 2D list of integers representing the cost of entering each cell.
 * @return A graph represented as a map where each key is a cell `(row, column)` and
 *         the value is a map of neighbor cells with their associated edge weights.
 */
fun buildGraph(matrix: List<List<Int>>): Map<Pair<Int, Int>, Map<Pair<Int, Int>, Double>> {
    // initialize our graph map
    val graph = mutableMapOf<Pair<Int, Int>, MutableMap<Pair<Int, Int>, Double>>()
    // get the rows and columns
    val numRows = matrix.size
    val numCols = matrix[0].size

    // loop over rows and columns, fill out the
    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            val neighbors = mutableMapOf<Pair<Int, Int>, Double>()
            if (i + 1 < numRows) {
                neighbors[Pair(i + 1, j)] = matrix[i + 1][j].toDouble()
            }
            if (j + 1 < numCols) {
                neighbors[Pair(i, j + 1)] = matrix[i][j + 1].toDouble()
            }
            graph[Pair(i, j)] = neighbors
        }
    }

    return graph
}
