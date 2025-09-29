package DijkstrasAlgo

// Import kotlins MHPQ implementation since our next() function
// only returns the next priority element and not its priority value
import java.util.PriorityQueue

/**
 * Computes the shortest path between two nodes in a directed, weighted graph
 * using Dijkstra's algorithm.
 *
 * This implementation:
 * - Treats the graph as an adjacency list of nodes to their neighbors with edge weights.
 * - Uses a min-heap (priority queue) internally to efficiently select the next closest node.
 * - Tracks visited nodes to avoid reprocessing.
 * - Reconstructs and returns the path from the start node to the destination node.
 *
 * @param T the type of the graph's nodes (e.g. String, Int).
 * @param graph the graph represented as a map from each node to a map of its neighbors and edge weights.
 * @param start the starting node from which to compute the shortest path.
 * @param destination the destination node to which the shortest path is sought.
 * @return a list of nodes representing the shortest path from `start` to `destination`,
 */
fun <T> dijkstra (graph: Map<T, Map<T, Double>>, start: T, destination: T) : List<T>? {
    // create a distanced mutablemap to determine how far vertices are from start,
    // if the vertice does not have a value, make it infinity
    val distances = mutableMapOf<T, Double>().withDefault { Double.POSITIVE_INFINITY }
    // initialize distances to just have the root node
    distances[start] = 0.0
    // have a previous map to store all the previous nodes
    val previous = mutableMapOf<T, T?>()
    previous[start] = null
    // define our PQ implemented under the hood with a minheap
    val pq = PriorityQueue(compareBy<Pair<Double, T>> { it.first })
    pq.add(Pair(0.0, start))
    // have a set to see if we visited a node yet
    val visited = mutableSetOf<T>()
    // while the pq is not empty
    while (pq.isNotEmpty()) {
        // assign distU to the weight of the highest priority pq element
        // assign U to be the highest priority pq element
        val (distU, u) = pq.poll()
        // if we have already visited this graph element
        if (u in visited) {
            // move onto the next one
            continue
        }
        // if not lets add this to our visited list
        visited.add(u)
        // if we reach the destination node, lets reconstruct the path
        if (u == destination) {
            // create a place to store our backwards path
            var path = mutableListOf<T>()
            // make the current graph element the destination (it is)
            var curr : T? = destination
            while (curr != null) {
                path.add(curr)
                curr = previous[curr]
            }
            path.reverse()
            return path
        }
        // If we are not at our destination we want to update distances map
        // pq and previous map
        val neighbors = graph[u]
            if (neighbors != null) {
                for (entry in neighbors.entries) {
                    // destructuring declarations from the graph map
                    val neighbor = entry.key
                    val weight = entry.value

                    val newDist = distU + weight
                    if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                        distances[neighbor] = newDist
                        previous[neighbor] = u
                        pq.add(Pair(newDist, neighbor))
                    }
                }
            }
    }
    return null
}