package DP
/**
 *
 * Implements the Smith–Waterman local sequence alignment algorithm using DP.
 *
 * This class computes the best local alignment between two DNA or protein sequences.
 * It uses a scoring system for matches, mismatches, and gaps, builds a DP table to find
 * the maximum alignment score, and then backtracks to recover the aligned sequences.
 */
data class AlignmentResult(
    val alignedSeq1: String,
    val alignedSeq2: String,
    val score: Int
)
object SequenceAligner {
    // Let's define some basic scoring parameters.
    // You can tweak these if you want to experiment.
    private const val MATCH_SCORE = 2
    private const val MISMATCH_PENALTY = -1
    private const val GAP_PENALTY = -2
    /**
     * Aligns two sequences using the Smith–Waterman algorithm.
     *
     * @param seq1 The first sequence (e.g., testAgainst)
     * @param seq2 The second sequence (e.g., targetGenome)
     * @return AlignmentResult containing the best local alignment and its score
     */
    fun smithWaterman(seq1: String, seq2: String): AlignmentResult {
        val n = seq1.length
        val m = seq2.length
        // Create the DP matrix of size (n+1) x (m+1)
        val dp = Array(n + 1) { IntArray(m + 1) { 0 } }
        // Keep track of where the best score occurs
        var maxScore = 0
        var maxI = 0
        var maxJ = 0
        // Fill out the DP matrix
        for (i in 1..n) {
            for (j in 1..m) {
                // Check if the current characters match or mismatch
                val matchOrMismatch = if (seq1[i - 1] == seq2[j - 1]) MATCH_SCORE else MISMATCH_PENALTY
                // The DP recurrence relation
                val scoreDiagonal = dp[i - 1][j - 1] + matchOrMismatch
                val scoreUp = dp[i - 1][j] + GAP_PENALTY
                val scoreLeft = dp[i][j - 1] + GAP_PENALTY
                // Take the maximum of these, but never let it go below zero
                dp[i][j] = maxOf(0, scoreDiagonal, scoreUp, scoreLeft)
                // Update max score position
                if (dp[i][j] > maxScore) {
                    maxScore = dp[i][j]
                    maxI = i
                    maxJ = j
                }
            }
        }
        // Now that we’ve built the DP table, we need to backtrack.
        // We'll reconstruct the aligned sequences by following the path that led to the max score.
        val aligned1 = StringBuilder()
        val aligned2 = StringBuilder()
        var i = maxI
        var j = maxJ
        // Keep going until we hit a 0 (which means the local alignment ended)
        while (i > 0 && j > 0 && dp[i][j] > 0) {
            val current = dp[i][j]
            val diagonal = dp[i - 1][j - 1]
            val up = dp[i - 1][j]
            val left = dp[i][j - 1]
            // Case 1: Diagonal → characters were aligned
            if (current == diagonal + (if (seq1[i - 1] == seq2[j - 1]) MATCH_SCORE else MISMATCH_PENALTY)) {
                aligned1.append(seq1[i - 1])
                aligned2.append(seq2[j - 1])
                i--
                j--
            }
            // Case 2: Up → gap in seq2
            else if (current == up + GAP_PENALTY) {
                aligned1.append(seq1[i - 1])
                aligned2.append('-')
                i--
            }
            // Case 3: Left → gap in seq1
            else if (current == left + GAP_PENALTY) {
                aligned1.append('-')
                aligned2.append(seq2[j - 1])
                j--
            }
            // If none match, break out
            else break
        }
        // Since we built the alignment backwards, we need to reverse it at the end
        val finalSeq1 = aligned1.reverse().toString()
        val finalSeq2 = aligned2.reverse().toString()
        // Return the result as a data class
        return AlignmentResult(finalSeq1, finalSeq2, maxScore)
    }
}
