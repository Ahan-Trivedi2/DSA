package insertionSort

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for insertionSort.kt
 */
class InsertionSortTest {

    /**
     * Verifies that a small unsorted array is sorted in ascending order.
     *
     * Given: [7, 4, 5, 2]
     * Expect: [2, 4, 5, 7]
     */
    @Test
    fun testInsertionSort_basicCase() {
        // Arrange: input array and expected sorted result
        val input = intArrayOf(7, 4, 5, 2)
        val expected = intArrayOf(2, 4, 5, 7)

        // Act: run the insertion sort
        val result = insertionSort(input)

        // Assert: compare contents (toList() makes it value-based for arrays)
        assertEquals(expected.toList(), result.toList())
        // If your Kotlin version supports it, you could also use:
        // assertContentEquals(expected, result)
    }
}
