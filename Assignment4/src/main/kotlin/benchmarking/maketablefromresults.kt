package benchmarking

/**
 * Prints a formatted table comparing runtimes of various sorting algorithms
 * across different input sizes for easy visual comparison in the console.
 */
fun main() {
    // experimental data from one run
    val sizes = listOf(10, 100, 1_000, 10_000, 100_000)

    val selection = listOf(0.001793, 0.000066, 0.001422, 0.024232, 1.439713)
    val insertion = listOf(7.165E-4, 4.51E-5, 0.0014447, 0.0110203, 1.2107101)
    val merge = listOf(0.005115, 2.54E-4, 0.0020016, 0.0039501, 0.0140123)
    val quick = listOf(5.206E-4, 2.04E-5, 3.259E-4, 0.0010693, 0.0105391)
    val builtin = listOf(2.001E-4, 2.538E-4, 3.9E-4, 0.0040062, 0.0379375)

    // Column names
    val headers = listOf("Size", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Built-in Sort")

    // Set column width for nice alignment
    val columnWidth = 17

    // Print header
    println("Sorting Algorithm Benchmark Results\n")
    println(headers.joinToString("") { it.padEnd(columnWidth) })
    println("-".repeat(headers.size * columnWidth))

    // Print each row of data
    for (i in sizes.indices) {
        println(
            String.format(
                "%-${columnWidth}d%-${columnWidth}.6f%-${columnWidth}.6f%-${columnWidth}.6f%-${columnWidth}.6f%-${columnWidth}.6f",
                sizes[i],
                selection[i],
                insertion[i],
                merge[i],
                quick[i],
                builtin[i]
            )
        )
    }
}
