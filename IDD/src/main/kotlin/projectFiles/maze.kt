package projectFiles

/**
 * Represents a maze with helper functions to find start and end points,
 * and to print the maze in plain or solved form.
 *
 * @property grid 2D character array representing the maze layout.
 *                'S' marks the start, 'E' marks the end, '.' is a path, and '#' is a wall.
 */
class Maze(val grid: Array<CharArray>) {
    // class properties, rows and columns
    val rows = grid.size
    val columns = grid[0].size
    /**
     * Finds the start ('S') and end ('E') positions in the maze grid.
     *
     * @return A pair containing the start and end coordinates as (row, column) pairs.
     * @throws IllegalStateException if either the start or end point is missing.
     */

    fun findStartEnd(): Pair<Pair<Int, Int>, Pair<Int,Int>> {
        // initialize a variable start which can hold a pair object
        var start: Pair<Int,Int>? = null
        // initialize a variable ebd which can hold a pair object
        var end: Pair<Int,Int>? = null
        for (r in 0 until rows) {
            for (c in 0 until columns) {
                when (grid[r][c]) {
                    'S' -> start = Pair(r,c)
                    'E' -> end = Pair(r,c)
                }
            }
        }
        if (start == null || end == null) {
            throw IllegalStateException("Maze must contain both start and end points")
        }
        return Pair(start,end)
    }
    /**
     * Prints the maze grid to the console in plain ASCII format.
     */
    fun print() {
        for (row in grid) {
            println(row.concatToString())
        }
    }
    /**
     * Prints the maze with the given path marked using '*' characters.
     *
     * @param path A list of (row, column) coordinates representing the path to draw.
     */
    fun printWithPath(path: List<Pair<Int,Int>>) {
        // make a DEEP COPY of the maze grid so we can freely edit it
        val copy = Array(grid.size) { CharArray(grid[0].size) }
        for (r in 0 until grid.size) {
                for (c in 0 until grid[0].size) {
                    copy[r][c] = grid[r][c]
                }
        }
        // for every row, column in the path, mark it in copy
        for ((r,c) in path) {
            if (copy[r][c] == '.') {
                copy[r][c] = '*'
            }
        }
        // Print the solved maze
        for (row in copy) {
            println(row.concatToString())
        }
    }

}
