package binarysearch

/**
 * Binary Search implementation in Kotlin.
 *
 * Given a sorted array `nums` and a target integer,
 * returns the index of the target if it exists, or -1 if not found.
 */

// Define Solution Class
class Solution {
    /**
     * Performs binary search on a sorted IntArray.
     *
     * @param nums the sorted array of integers see if push worksfkdsjklf
     * @param target the value to search for
     * @return the index of the target if found, otherwise -1
     */
    fun search(nums: IntArray, target: Int): Int {
        // initialize low and high indices to define bounds of binary search
        var low = 0
        var high = nums.size - 1
        // as long as we have not exhausted through all possibilities, compute mid, and compare to target
        while (high >= low) {
            val mid = (low + high) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] < target -> low = mid + 1
                else -> high = mid - 1
            }
        }
        // if we never found 'target' in our IntArray, return - 1
        return -1
    }
}
