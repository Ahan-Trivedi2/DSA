package benchmarking

import kotlin.random.Random
import kotlin.time.measureTime
import kotlin.time.DurationUnit

// Import your sorting algorithms (adjust these if your package names differ)
import insertionSort.insertionSort
import selectionSort.selectionSort
import mergeSort.mergeSort
import quickSort.quicksort

/**
 * Benchmarks and compares the performance of multiple sorting algorithms
 * (Selection, Insertion, Merge, Quick, and Kotlin's built-in sort) by measuring
 * their runtime on lists of increasing size.
 */
fun main() {
    // List sizes to test
    val sizes = listOf(10, 100, 1_000, 10_000, 100_000)

    // Sorting algorithm names
    val algorithms = listOf("Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Built-in Sort")

    println("Sorting Algorithm Benchmark\n")

    // Loop through algorithms
    for (algo in algorithms) {
        println("=== $algo ===")
        val runTimes = mutableListOf<Double>()

        // Loop through different list sizes
        for (size in sizes) {
            // Generate random integer data of given size
            val data = (1..size).map { Random.nextInt(0, 1000) }

            // Measure runtime for each algorithm
            val runtime = measureTime {
                when (algo) {
                    "Selection Sort" -> selectionSort(data.toIntArray())
                    "Insertion Sort" -> insertionSort(data.toIntArray())
                    "Merge Sort" -> mergeSort(data.toIntArray())
                    "Quick Sort" -> quicksort(data.toIntArray(), 0, data.size - 1)
                    "Built-in Sort" -> data.sorted()
                }
            }

            val seconds = runtime.toDouble(DurationUnit.SECONDS)
            runTimes.add(seconds)
            println("Size $size → ${"%.6f".format(seconds)} s")
        }

        println("Runtimes (s): $runTimes\n")
    }
}
