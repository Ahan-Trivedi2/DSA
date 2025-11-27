package kdTree

// Required Imports
import kotlin.random.Random
import kotlin.time.Duration
import kotlin.time.TimeSource

/**
 * Function to generate a k dimensional point with each axis being an element between 0 and 1
 */
private fun randomPoint(k: Int) : List<Double> {
    return List(k) { Random.nextDouble() }
}
/**
 * Benchmark [kdTree] against brute force nearest neighbor.
 * 1000 test points will be generated to test against the training
 * points.
 *
 * @param k: the dimensionality of the dataset to create
 * @param numPoints: the number of points to use to match against
 *   (1000 test points will be used)
 * @return the triple of [Duration] objects where the first specifies
 * the time to build the tree, the second specifies the time it takes
 * to query the tree with 1,000 points, and the third is the time it
 * takes to find the nearest neighbor to these points using the brute
 * force approach.
 */
fun runExperiment(k: Int, numPoints: Int): Triple<Duration, Duration, Duration> {
    // Create training points (numPoints amount of them, each point has k axis's)
    val trainingPoints = List(numPoints) { randomPoint(k)}
    // Create our 1000 test points to query our KDTree on and generate metrics
    val testPoints = List(1000) { randomPoint(k) }
    // Build KDTree and record time to construct
    val buildTimer = TimeSource.Monotonic.markNow()
    val tree = KDTree(trainingPoints)
    val buildTime = buildTimer.elapsedNow()
    // Find the amount of time it takes to run NN search on instance of KDTree
    val kdTimer = TimeSource.Monotonic.markNow()
    for (test in testPoints) {
        tree.nearest(test)
    }
    val kdQueryTime = kdTimer.elapsedNow()
    // Find the amount of time it takes to run the brute force search on instance of KDTree
    val bruteTimer = TimeSource.Monotonic.markNow()
    for (test in testPoints) {
        KDTree.bruteForce(trainingPoints, test)
    }
    val bruteForceQueryTime = bruteTimer.elapsedNow()
    // Return Triple of 3 Duration objects
    return Triple(buildTime, kdQueryTime, bruteForceQueryTime)
}
/**
 * Main: Runs multiple experiments using different values of k and numPoints.
 * numPoints ranges over orders of magnitude as required.
 */
fun main() {
    val kValues = listOf(2, 5, 10, 20)         // test various dimensionalities
    val datasetSizes = listOf(10, 100, 1000, 10000)  // orders of magnitude
    for (k in kValues) {
        for (numPoints in datasetSizes) {
            println("------------------------------------------------")
            println("Experiment: k = $k dimensions, numPoints = $numPoints")
            val (buildTime, kdTime, bruteTime) = runExperiment(k, numPoints)
            println("Tree Build Time:            $buildTime")
            println("KDTree Query Time (1000):   $kdTime")
            println("Brute Force Time (1000):    $bruteTime")
            println()
        }
    }
}