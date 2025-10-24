package matrix_class

import kotlin.test.Test

/**
 * Runs a benchmark test comparing regular, Strassen,
 * and hybrid matrix multiplication methods.
 */
class MatrixBenchmarkTest {
    @Test
    fun runBenchmark() {
        println("Benchmarking Regular vs Strassen Multiplication...\n")
        benchmarkMatrixMultiplication()
    }
}
