// Package declaration
package binarysearch

// Import functions from kotlin.test library
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for the Binary Search implementation.
 */
class SolutionTest {
    // Instance of Solution to run tests on
    private val solution = Solution()

    /** Tests when the target exists in the array. */
    @Test
    fun testTargetFound() {
        val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
        val result = solution.search(nums, 9)
        assertEquals(4, result, "Target 9 should be found at index 4")
    }

    /** Tests when the target is not in the array. */
    @Test
    fun testTargetNotFound() {
        val nums = intArrayOf(-1, 0, 3, 5, 9, 12)
        val result = solution.search(nums, 2)
        assertEquals(-1, result, "Target 2 should not be found")
    }

    /** Tests a single-element array case. */
    @Test
    fun testSingleElement() {
        val nums = intArrayOf(5)
        assertEquals(0, solution.search(nums, 5), "Target 5 should be found at index 0")
        assertEquals(-1, solution.search(nums, 3), "Target 3 should not be found")
    }

    /** Tests the empty array case. */
    @Test
    fun testEmptyArray() {
        val nums = intArrayOf()
        assertEquals(-1, solution.search(nums, 10), "Empty array should always return -1")
    }
}
