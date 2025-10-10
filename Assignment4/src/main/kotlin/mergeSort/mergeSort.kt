package mergeSort

/**
 * Sorts the given [arr] using the Merge Sort algorithm.
 *
 * Recursively splits the array into halves and merges them
 * back together in sorted order.
 *
 * @param arr The array to sort.
 * @return A new sorted array.
 */
fun mergeSort(arr: IntArray): IntArray {
    // base case
    if (arr.size <= 1) {
        return arr
    }
    // mid value to split at
    val mid = arr.size / 2
    // recursive left and right split calls using array slicing
    val left = mergeSort(arr.sliceArray(0 until mid))
    val right = mergeSort(arr.sliceArray(mid until arr.size))

    // return the merged array
    return merge(left, right)
}

/**
 * Merges two sorted arrays [left] and [right] into a single sorted array.
 *
 * Compares elements one by one and builds a new array
 * containing all elements in sorted order.
 *
 * @param left The left sorted half.
 * @param right The right sorted half.
 * @return A merged sorted array containing all elements from [left] and [right].
 *
 */
fun merge(left: IntArray, right: IntArray) : IntArray {
    // have a result array equivalent to the size of the left and right sub-arrays
    val result = IntArray(left.size + right.size)
    // have index variables for the left array, right array, and result array
    var i = 0
    var j = 0
    var k = 0

    // compare elements from the left and right arrays
    while (i < left.size && j < right.size) {
        if (left[i] < right[j]) {
            result[k] = left[i]
            i++
            k++
        }
        else {
            result[k] = right[j]
            j++
            k++
        }
    }
    // if the right array is empty, copy elements from the left array
    while (i < left.size) {
        result[k] = left[i]
        i++
        k++
    }
    // is the left array is empty, copy elements from the right array
    while (j < right.size) {
        result[k] = right[j]
        j++
        k++
    }

    return result

}

