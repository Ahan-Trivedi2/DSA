package quickSort

/**
 * Sorts the given [arr] in-place using the QuickSort algorithm.
 *
 * Divides the array around a pivot so smaller elements go to the left
 * and larger ones to the right, then recursively sorts each side.
 *
 * @param arr The array to sort.
 * @param low Starting index of the subarray.
 * @param high Ending index of the subarray.
 * ```
 */
fun quicksort(arr: IntArray, low: Int, high: Int) {
    if (low < high){
        val pivotIndex = partition(arr, low, high)
        quicksort(arr, low, pivotIndex - 1)
        quicksort(arr, pivotIndex + 1, high)
    }
}

/**
 * Partitions [arr] around a pivot (chosen as the last element),
 * placing smaller values to the left and larger ones to the right.
 *
 * @param arr The array to partition.
 * @param low Starting index of the subarray.
 * @param high Ending index where the pivot is.
 * @return The final index of the pivot.
 *
 */
fun partition(arr: IntArray, low: Int, high: Int) : Int {
    // choose a pivot, lets just choose high
    val pivot = arr[high]
    // have an index i to be where we should insert elements at arr[j] that are less than pivot
    var i = low - 1
    // loop over the entire array to and including high-1
    for (j in low until high) {
        if (arr[j] <= pivot){
            // increment i so it is at the correct place to swap into
            i++
            // swap arr[i] and arr[j]
            val tmp = arr[i]
            arr[i] = arr[j]
            arr[j] = tmp
        }
    }
    // i holds the last value less than pivot, so now lets put pivot in i+1
    // swap i+1 with high(pivot)
    val tmp = arr[i+1]
    arr[i+1] = arr[high]
    arr[high] = tmp
    // return i+1, the index of the pivot
    return i+1
}