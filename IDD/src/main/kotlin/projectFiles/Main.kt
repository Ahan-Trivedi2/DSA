// main.kt
package projectFiles

/**
 * Entry point for the Maze Solver program.
 * Generates a random maze, solves it using BFS and DFS,
 * and prints the original maze, performance metrics, and solved paths.
 */
fun main() {
    // print "Maze solver - BFS vs DFS" in output terminal
    println("Maze solver - BFS vs DFS\n")
    // Generate a random maze using generateMaze function
    val mazeArray = generateMaze(40, 40)
    // Wrap this in a Maze object to access helper functions from the Maze class
    val maze = Maze(mazeArray)
    // Print the original maze (using the print helper)
    println("Original Maze: ")
    maze.print()
    // run BFS and DFS
    val (bfsPath, bfsMetrics) = bfs(maze)
    val (dfsPath, dfsMetrics) = dfs(maze)
    // Print performance metrics
    println("\n BFS Metrics:")
    println(bfsMetrics)
    println("\n DFS Metrics")
    println(dfsMetrics)
    // Show the mazes side by side
    println("\n BFS Solved Maze:")
    maze.printWithPath(bfsPath)
    println("\n DFS Solved Maze:")
    maze.printWithPath(dfsPath)
}


