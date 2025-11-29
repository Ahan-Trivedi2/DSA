package kdTree

// Imports for script functionality
import kotlin.math.sqrt
import kotlin.math.abs

/**
 * A node in a k-d tree.
 *
 * @property point The point stored at this node.
 * @property axis The dimension this node splits on.
 * @property left Left child subtree.
 * @property right Right child subtree.
 */
data class KDNode(
    val point: List<Double>,
    val axis: Int,
    var left: KDNode? = null,
    var right: KDNode? = null,
)

/**
 * A k-d tree for nearest-neighbor search.
 *
 * Builds a tree from a list of k-dimensional points and provides methods
 * for finding the closest point to a given target using either the tree
 * or a brute-force scan.
 */
class KDTree(private val points: List<List<Double>>) {
    // Ensure our KDTree is created in the constructor subsection
    // when given points
    private val root: KDNode? = buildKDTree(points) // builds our tree and gives us the root node
    /**
     * Computes the Euclidean distance between two k-dimensional points.
     */
    private fun euclideanDistance(a: List<Double>, b: List<Double>) : Double {
        var sum : Double = 0.0
        for (i in a.indices) {
            val d = a[i] - b[i]
            val dSquared = d * d
            sum += dSquared
        }
        return sqrt(sum)
    }
    /**
     * Recursively builds the k-d tree by splitting points on the current axis
     * and choosing the median as the node for each level.
     */
    private fun buildKDTree(points: List<List<Double>>, depth: Int = 0) : KDNode? {
        // Base case
        if (points.isEmpty()) {
            return null
        }
        // Find the amount of dimensions in each point
        val k = points[0].size
        // Find the current axis to split on
        val axis = depth % k
        // Sort the list in ascending order by the coordinate given in axis
        val sorted = points.sortedBy( {it[axis]})
        // Find the median index which will be our root for this current axis split
        // If sorted.size is odd, you get the true middle
        // If sorted.size is even, you get the upper halves first index
        val median = sorted.size / 2
        // Now that we have our axis and exact kth dimensional point, get the head node
        val node = KDNode(sorted[median], axis)
        // Recursively build the left subtree
        node.left = buildKDTree(sorted.subList(0, median), depth + 1)
        node.right = buildKDTree(sorted.subList(median + 1, sorted.size), depth + 1)
        // This initializes the recursion
        return node
    }
    /**
     * Returns the nearest neighbor to the given target point using the k-d tree.
     */
    fun nearest(target: List<Double>): List<Double>? {
        // nearest is wrapper function of nearestHelper
        return nearestHelper(root, target, root?.point, Double.POSITIVE_INFINITY)
    }
    /**
     * Recursively searches the k-d tree to find the closest point to the target.
     *
     * @param node The current node being examined.
     * @param target The point we want to find the nearest neighbor to.
     * @param bestPoint The closest point found so far.
     * @param bestDist The distance to the closest point found so far.
     * @return The nearest neighbor to the target.
     */
    private fun nearestHelper(
        node: KDNode?, // Current node that we are comparing to target
        target: List<Double>, // We are trying to find the node closest to this
        bestPoint: List<Double>?, // Current closest point to target
        bestDist: Double, // Distance of current closest point to target
        ): List<Double>? {
        // Base case for the recursion
        if (node == null) {
            return bestPoint
        }
        // Store bestPoint and bestDist into a mutable variable
        // to allow the possibility of updating them
        var currentBestPoint = bestPoint
        var currentBestDist = bestDist
        // Find the distance betw   een the current node and the target point
        val d = euclideanDistance(target, node.point)
        // Determine if we want to update currentBestPoint and currentBestDist
        if (d < currentBestDist) {
            currentBestPoint = node.point
            currentBestDist = d
        }
        // Get the axis this node used to split the space (e.g., x, y, z...)
        val axis = node.axis
        // Decide if the target lies on the left side of the splitting plane
        val goLeft = target[axis] < node.point[axis]
        // Create a variable primary which nudges us to go toward the most likely path
        val primary = if (goLeft) node.left else node.right
        // Create a variable secondary is the other path if our primary path doesn't
        // End up working out
        val secondary = if (goLeft) node.right else node.left
        // Recursively call nearest helper on our subtree to see if we find a better point
        currentBestPoint = nearestHelper(primary, target, currentBestPoint, currentBestDist)
        // Since currentBestDist is a primitive, we pass by value and thus don't update our
        // overall value
        currentBestDist = euclideanDistance(target, currentBestPoint!!)
        // Check the distance from the target to the split line
        val axisDist = abs(target[axis] - node.point[axis])
        // Check if this axisDist is less than the current best distance
        if (axisDist < currentBestDist) {
            currentBestPoint = nearestHelper(secondary, target, currentBestPoint, currentBestDist)
        }
        // Return the closest point to target
        return currentBestPoint
    }
    /**
     * Performs a brute-force nearest-neighbor search by checking every point.
     *
     * @param points The list of points to search through.
     * @param target The point to find the closest match to.
     * @return The nearest point to the target, or null if the list is empty.
     */

    // We are going to use a static method since this function
    // does not need to work on an instance of the object
    companion object {
        fun bruteForce(points: List<List<Double>>, target: List<Double>) : List<Double>? {
            // Variable to store the closest point to target as we go through each point in points
            var bestPoint : List<Double>? = null
            // Variable to store the distance associated with bestPoint to use during comparisons
            var bestDistance : Double = Double.POSITIVE_INFINITY
            // Loop through every point and calculate the Euclidean distance
            for (point in points) {
                var sum : Double = 0.0
                for (i in point.indices) {
                    val d : Double = point[i] - target[i]
                    sum += (d * d)
                }
                val distance = sqrt(sum)
                if (distance < bestDistance) {
                    bestDistance = distance
                    bestPoint = point
                }
            }
            return bestPoint
        }
    }
}
