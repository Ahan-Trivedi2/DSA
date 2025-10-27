package projectFiles

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

class DFS_test {
    @Test
    fun dfs_test() {
        // Sample maze grid
        // S = Start, E = End, . = open path, # = wall
        val mazeGrid = arrayOf(
            charArrayOf('#', '#', '#', '#', '#', '#', '#'),
            charArrayOf('#', 'S', '.', '.', '.', 'E', '#'),
            charArrayOf('#', '#', '#', '#', '#', '#', '#')
        )
        // Create Maze object
        val maze = Maze(mazeGrid)
        // Run DFS
        val (path, metrics) = dfs(maze)
        // Basic checks
        assertTrue(path.isNotEmpty(), "Path should not be empty")
        assertNotNull(metrics, "Metrics should not be null")
        // Start and end correctness
        val (start, end) = maze.findStartEnd()
        assertEquals(start, path.first(), "Path should start at the start position")
        assertEquals(end, path.last(), "Path should end at the end position")
        // Path should not go through walls
        for ((x, y) in path) {
            assertTrue(maze.grid[x][y] != '#', "Path should not go through walls")
        }
        // Path should be continuous
        for (i in 0 until path.size - 1) {
            val (x1, y1) = path[i]
            val (x2, y2) = path[i + 1]
            val diff = kotlin.math.abs(x1 - x2) + kotlin.math.abs(y1 - y2)
            assertEquals(1, diff, "Each move should be to an adjacent cell")
        }
        // Metrics sanity check
        assertTrue(metrics.nodesVisited > 0, "Nodes visited should be positive")
        assertTrue(metrics.runtimeMs > 0, "Runtime should be greater than zero")
        assertTrue(metrics.pathLength == path.size, "Path length should match path size")
    }
}
