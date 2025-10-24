package matrix_class

/**
 * Benchmarks the performance of regular, Strassen, and hybrid
 * matrix multiplication across various matrix sizes.
 *
 * Prints the execution time (in milliseconds) for each method.
 */
fun benchmarkMatrixMultiplication() {
    val sizes = listOf(2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)
    for (n in sizes) {
        val A = Matrix(n)
        val B = Matrix(n)
        // Fill with random values
        for (i in 0 until n)
            for (j in 0 until n)
                A.set(i, j, Math.random())
        for (i in 0 until n)
            for (j in 0 until n)
                B.set(i, j, Math.random())

        val startRegular = System.nanoTime()
        A * B
        val endRegular = System.nanoTime()

        val startStrassen = System.nanoTime()
        A.strassenMultiply(B)
        val endStrassen = System.nanoTime()

        val startHybridStrassen = System.nanoTime()
        A.hybridStrassenMultiply(B)
        val endHybridStrassen = System.nanoTime()

        println(
            "n=${n.toString().padEnd(4)} | " +
                    "Regular: ${(endRegular - startRegular)/1e6} ms | " +
                    "Strassen: ${(endStrassen - startStrassen)/1e6} ms | " +
                    "Hybrid: ${(endHybridStrassen - startHybridStrassen)/1e6} ms"
        )
    }
}
