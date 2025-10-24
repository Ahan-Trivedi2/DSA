package DP
/**
 *
 * Runs the Smith–Waterman local alignment algorithm on the Salmonella DNA sequences.
 */
fun main() {
    // Display a short description
    println("Running Smith–Waterman alignment on Salmonella DNA...\n")
    // The following variables are defined in Genome.kt
    println("Genome snippet length: ${genomeSnippet.length}")
    println("Target genome length: ${targetGenome.length}")
    println("Test-against length: ${testAgainst.length}\n")
    // Run the Smith–Waterman algorithm
    val result = SequenceAligner.smithWaterman(testAgainst, targetGenome)
    // Print the final results
    println("Best alignment score: ${result.score}\n")
    println("Best local alignment:")
    println(result.alignedSeq1)
    println(result.alignedSeq2)
    // Check whether the alignment overlaps part of the known genome snippet
    if (result.alignedSeq2.contains(genomeSnippet.take(12))) {
        println("\nAlignment overlaps part of the genome snippet.")
    } else {
        println("\nAlignment found, but not directly overlapping the snippet prefix.")
    }
}
