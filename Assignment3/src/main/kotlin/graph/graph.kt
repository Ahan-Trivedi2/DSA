package graph

/**
 * ``Graph`` represents a directed graph
 * @param VertexType the type that represents a vertex in the graph
 */
interface Graph<VertexType> {
    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<VertexType>

    /**
     * Add an edge between [from] and [to] with edge weight [cost]
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double)

    /**
     * Get all the edges that begin at [from]
     * @return a map where each key represents a vertex connected to [from] and the value represents the edge weight.
     */
    fun getEdges(from: VertexType): Map<VertexType, Double>

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear()
}

/**
 * A directed, weighted graph implementation of the [Graph] interface.
 *
 * @param VertexType the type representing a vertex in the graph
 */
class DirectedWeightedGraph<VertexType> : Graph<VertexType> {
    // create vertices which is a set of all the vertices in the graph
    private val vertices = mutableSetOf<VertexType>()
    // create an adjacencyMap to understand what vertices are connected and with which weight
    private val adjacencyMap = mutableMapOf<VertexType, MutableMap<VertexType, Double>>()

    /**
     * Returns all vertices currently in the graph.
     *
     * @return a set of all vertices
     */
    override fun getVertices(): Set<VertexType> {
        // return the vertices set
        return vertices
    }

    /**
     * Adds a directed edge from [from] to [to] with the specified [cost].
     * If either vertex is not already present, it will be added to the graph.
     *
     * @param from the starting vertex
     * @param to the destination vertex
     * @param cost the weight of the edge
     */
    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        // Ensure these from and to edges are present in our set
        vertices.add(from)
        vertices.add(to)
        // check if our from vertex is in adjacency map, if not, make a blank value
        if (!adjacencyMap.containsKey(from)) {
            adjacencyMap[from] = mutableMapOf<VertexType, Double>()
        }
        // store the key for the map as a variable so we can reference the val map
        val edgesFrom = adjacencyMap[from]!! // we can assert not null cause the value will at least be a blank map
        edgesFrom[to] = cost // no matter what, rewrite the nested Mutable Map
    }

    /**
     * Retrieves all outgoing edges from the given [from] vertex.
     *
     * @param from the source vertex
     * @return a map of destination vertices to edge weights
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        // store the sub mutable map that resides in the key of from in some reference
        val edgeFrom = adjacencyMap[from] ?: emptyMap()
        // return that map
        return edgeFrom
    }

    /**
     * Removes all vertices and edges from the graph.
     */
    override fun clear() {
        // clear vertices set and adjacency map
        vertices.clear()
        adjacencyMap.clear()
    }
}