package dijkstraImplementation


/**
 * Reads a matrix of integers from a resource file and converts it into a 2D list.
 *
 * The file should be located in the project's `resources` directory and contain
 * comma-separated values (CSV), where each line represents a row in the matrix.
 * @param fileName The name of the file in the `resources` directory. Defaults to `"matrix.txt"`.
 * @return A list of lists of integers representing the matrix.
 * @throws IllegalArgumentException if the resource file cannot be found.
 */
fun readMatrixFromResources(fileName: String = "matrix.txt"): List<List<Int>> {
    val inputStream = object {}.javaClass.getResourceAsStream("/$fileName")
        ?: throw IllegalArgumentException("Resource file $fileName not found.")
    // this right here is responsible for converting our matrix.txt to a List of lists we can work with
    return inputStream.bufferedReader().readLines()
        .map { line ->
            line.split(",").map { it.trim().toInt() }
        }
}
