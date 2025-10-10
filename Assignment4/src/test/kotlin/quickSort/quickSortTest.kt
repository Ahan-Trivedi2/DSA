package quickSort

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class QuickSortTest {

    /**
     * Tests the [quicksort] function on a simple unsorted array.
     */
    @Test
    fun testQuicksort() {
        val arr = intArrayOf(9, 3, 7, 1, 5)
        quicksort(arr, 0, arr.size - 1)
        assertContentEquals(intArrayOf(1, 3, 5, 7, 9), arr)
    }

    /**
     * Tests the [partition] function to ensure it correctly places the pivot.
     */
    @Test
    fun testPartition() {
        val arr = intArrayOf(9, 3, 7, 1, 5)
        val pivotIndex = partition(arr, 0, arr.size - 1)

        // The pivot (5) should be in its sorted position.
        assertEquals(2, pivotIndex)
        // Check that elements to the left are <= pivot and right are >= pivot.
        val pivotValue = arr[pivotIndex]
        for (i in 0 until pivotIndex) {
            assert(arr[i] <= pivotValue)
        }
        for (i in pivotIndex + 1 until arr.size) {
            assert(arr[i] >= pivotValue)
        }
    }
}

