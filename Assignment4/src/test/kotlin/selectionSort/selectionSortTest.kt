package selectionSort

import kotlin.test.Test
import kotlin.test.assertContentEquals

/**
 * Unit test class for the [selectionSort] function.
 *
 * Contains one test function corresponding to the single function
 * defined in the main source file.
 */
class SelectionSortTest {

    /**
     * Tests the [selectionSort] function to ensure that it correctly
     * sorts an unsorted integer array in ascending order.
     *
     * The test verifies that after sorting, the array matches
     * the expected sorted sequence.
     */
    @Test
    fun testSelectionSort() {
        // Arrange: create an unsorted array and define the expected sorted result.
        val arr = intArrayOf(64, 25, 12, 22, 11)
        val expected = intArrayOf(11, 12, 22, 25, 64)

        // Act: call the selectionSort function to sort the array in place.
        selectionSort(arr)

        // Assert: verify that the sorted array matches the expected output.
        assertContentEquals(expected, arr, "The array should be sorted in ascending order.")
    }
}
