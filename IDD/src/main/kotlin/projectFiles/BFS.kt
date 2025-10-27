// BFS.kt
package projectFiles

import kotlin.system.measureNanoTime

/**
 * Runs a breadth-first search (BFS) on the maze to find the shortest path
 * from the start ('S') to the end ('E') cell.
 *
 * @param maze The maze to solve.
 * @return A pair containing the path as a list of coordinates and
 *         a Metrics object with runtime, path length, and nodes visited.
 */
fun bfs(maze: Maze) : Pair<List<Pair<Int,Int>>, Metrics> {
    // extract the grid and start/end coordinates using the Maze helper methods
    val grid = maze.grid
    val (start, end) = maze.findStartEnd()
    // queue to append the bfs neighbor nodes to
    val queue: ArrayDeque<Pair<Int,Int>> = ArrayDeque()
    // we need a visited set to keep track of visited cells
    val visited = mutableSetOf<Pair<Int, Int>>()
    // we need a map to reconstruct the path later
    val parent = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>?>()
    // initial declarations
    var nodesVisited: Int = 0
    var path: List<Pair<Int,Int>> = emptyList<Pair<Int,Int>>()
    var elapsedMs: Double = 0.0
    // measure BFS runtime
    elapsedMs = measureNanoTime {
        // initialize all our data structures with start
        queue.addLast(start)
        visited.add(start)
        parent[start] = null
        // movement directions: Right, Down, Left, Up
        val directions = listOf(
            Pair(0,1), // Right
            Pair(1,0), // Down
            Pair(0,-1), // Left
            Pair(-1,0) // Up
        )
        // BFS main loop
        while (queue.isNotEmpty()) {
            // deque current element so we can find its neighbors or check if its the end
            val current = queue.removeFirst()
            nodesVisited ++
            // if we reached the end then lets return out
            if (current == end) break
            // explore neighbors
            for ((dx,dy) in directions) {
                val nx = current.first + dx
                val ny = current.second + dy
                val next = Pair(nx, ny)
                // check if we are at a valid coordinate point that is not already in set or a wall
                if (
                    nx in 0 until maze.rows &&
                    ny in 0 until maze.columns &&
                    grid[nx][ny] != '#' &&
                    next !in visited
                )
                {
                    visited.add(next)
                    parent[next] = current
                    queue.add(next)
                }
            }
        }
        // reconstruct path
        val pathList = mutableListOf<Pair<Int, Int>>()
        var node: Pair<Int,Int>? = end
        while (node != null && node in parent) {
            pathList.add(node)
            node = parent[node]
        }
        pathList.reverse()
        path = pathList
    } / 1e6 // convert nanoseconds to milliseconds
    // Return path and metrics
    return Pair(path, Metrics(
        nodesVisited = nodesVisited,
        pathLength = path.size,
        runtimeMs = elapsedMs
    ))
}

