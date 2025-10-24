package DP
/**
 *
 * Unit test for the Smith–Waterman sequence alignment algorithm.
 *
 * This file tests a few simple DNA alignments to make sure the algorithm
 * finds the correct best score and reconstructs a reasonable alignment.
 */
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class SequenceAlignerTest {
    /**
     * Runs a few example alignments to check correctness of the Smith–Waterman algorithm.
     */
    @Test
    fun testLocalAlignmentExamples() {
        // Perfect match
        val seq1 = "GATTACA"
        val seq2 = "GATTACA"
        val result1 = SequenceAligner.smithWaterman(seq1, seq2)
        println("Alignment 1:")
        println(result1.alignedSeq1)
        println(result1.alignedSeq2)
        println("Score: ${result1.score}\n")
        // In a perfect match, score should be (length * MATCH_SCORE)
        assertEquals(seq1.length * 2, result1.score)
        // Partial match (local region)
        val seq3 = "GATTACA"
        val seq4 = "GGTTACG"
        val result2 = SequenceAligner.smithWaterman(seq3, seq4)
        println("Alignment 2:")
        println(result2.alignedSeq1)
        println(result2.alignedSeq2)
        println("Score: ${result2.score}\n")
        // Expect some positive score but less than the perfect one
        assertTrue(result2.score in 1 until result1.score)
        // Sequence with mismatches and gaps
        val seq5 = "ACACACTA"
        val seq6 = "AGCACACA"
        val result3 = SequenceAligner.smithWaterman(seq5, seq6)
        println("Alignment 3:")
        println(result3.alignedSeq1)
        println(result3.alignedSeq2)
        println("Score: ${result3.score}\n")
        assertTrue(result3.score > 0)
    }
}
