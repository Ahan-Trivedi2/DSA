package mergeSort

import kotlin.test.Test
import kotlin.test.assertContentEquals

class MergeSortTest {

    /**
     * Tests the [mergeSort] function on a simple unsorted array.
     */
    @Test
    fun testMergeSort() {
        val arr = intArrayOf(9, 3, 7, 1, 5)
        val sorted = mergeSort(arr)
        assertContentEquals(intArrayOf(1, 3, 5, 7, 9), sorted)
    }

    /**
     * Tests the [merge] function to ensure it correctly merges two sorted subarrays.
     */
    @Test
    fun testMerge() {
        val left = intArrayOf(1, 4, 6)
        val right = intArrayOf(2, 3, 5)
        val merged = merge(left, right)

        // The merged array should contain all elements from left and right in sorted order.
        assertContentEquals(intArrayOf(1, 2, 3, 4, 5, 6), merged)
    }
}
