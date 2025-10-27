package projectFiles

class Maze(val grid: Array<CharArray>) {
    // class properties, rows and columns
    val rows = grid.size
    val columns = grid[0].size
    // find start and end positions in the maze
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
    // Print the maze in plain ASCII
    fun print() {
        for (row in grid) {
            println(row.concatToString())
        }
    }
    // Print the maze with a path drawn (using *)
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
