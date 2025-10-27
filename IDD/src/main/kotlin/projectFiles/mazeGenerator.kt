package projectFiles

import kotlin.random.Random

/**
 * Generates a random maze using recursive backtracking.
 *
 * @param rows The number of rows in the maze.
 * @param columns The number of columns in the maze.
 * @return A 2D character array representing the generated maze,
 *         with 'S' as the start, 'E' as the end, '.' as open paths, and '#' as walls.
 */
fun generateMaze(rows: Int, columns: Int): Array<CharArray> {
    // Ensure we have odd dimensions for clean boundaries
    val r = if (rows % 2 == 0) rows + 1 else rows
    val c = if (columns % 2 == 0) columns + 1 else columns
    // Create the maze grid filled with walls
    val maze = Array(r) { CharArray(c) { '#' } }
    // Recursive backtracking function that carves out paths two cells away at a time
    fun carve(x: Int, y: Int) {
        val directions = listOf(
            0 to 2,   // Right
            0 to -2,  // Left
            2 to 0,   // Down
            -2 to 0   // Up
        ).shuffled(Random)
        for ((dx, dy) in directions) {
            val nx = x + dx
            val ny = y + dy
            if (nx in 1 until r - 1 && ny in 1 until c - 1 && maze[nx][ny] == '#') {
                // Carve through the wall between (x, y) and (nx, ny)
                maze[x + dx / 2][y + dy / 2] = '.'
                maze[nx][ny] = '.'
                carve(nx, ny)
            }
        }
    }
    // Pick a starting cell and begin carving
    val startX = 1
    val startY = 1
    maze[startX][startY] = '.'
    carve(startX, startY)
    // Randomly add a few loops to create multiple possible paths
    addLoops(maze, probability = Random.nextDouble(0.02, 0.08))
    // Place start and end markers
    maze[1][1] = 'S'
    maze[r - 2][c - 2] = 'E'
    return maze
}

/**
 * Adds random loops to the maze to make it imperfect (i.e., with multiple possible paths).
 *
 * @param maze The 2D maze grid to modify.
 * @param probability The chance of converting a wall into a path.
 */

private fun addLoops(maze: Array<CharArray>, probability: Double) {
    val r = maze.size
    val c = maze[0].size
    for (x in 1 until r - 1) {
        for (y in 1 until c - 1) {
            if (maze[x][y] == '#' && Math.random() < probability) {
                // Only remove walls that connect at least two open cells
                val openNeighbors = listOf(
                    maze[x - 1][y],
                    maze[x + 1][y],
                    maze[x][y - 1],
                    maze[x][y + 1]
                ).count { it == '.' }

                if (openNeighbors >= 2) {
                    maze[x][y] = '.'
                }
            }
        }
    }
}
