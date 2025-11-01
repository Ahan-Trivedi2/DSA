package lempel_ziv

import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Unit tests for the Lempel–Ziv (LZ78) compression function.
 *
 * Runs the algorithm on several test strings made up of A’s and B’s
 * with different repetition patterns to observe how well the compression works.
 */
class LZCompressTest {
    @Test
    /**
     * Tests [lzcompress] on various input strings and prints compression results.
     *
     * For each string, this test shows the original input, the compressed
     * output pairs, the lengths, and the overall compression ratio.
     */
    fun lzCompressTest() {
        val inputs = listOf(
            "ABABABA",                   // short alternating pattern
            "AAAAAA",                    // short uniform
            "ABABABABABABABAB",          // long alternating pattern
            "AAAAAAAAAAAAAAAAAAAA",      // long uniform
            "AABBAABB",                  // short grouped pattern
            "AABBAABBAABBAABBAABB"       // long grouped pattern
        )
        for (input in inputs) {
            val compressed = lzcompress(input)
            println("Input: $input")
            println("Compressed Output: $compressed")
            println("Original Length: ${input.length}")
            println("Compressed Length: ${compressed.size}")
            val ratio = "%.2f".format(compressed.size.toDouble() / input.length)
            println("Compression Ratio: $ratio")
            println()
            assertTrue(compressed.isNotEmpty(), "Compression output should not be empty")
        }
    }
}
