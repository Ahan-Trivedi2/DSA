package selectionSort
/**
 * Sorts the given integer array in ascending order using the Selection Sort algorithm.
 *
 * Selection sort works by repeatedly finding the smallest element
 * from the unsorted portion of the array and swapping it into its correct position.
 *
 * @param arr The integer array to be sorted. Sorting is done in place.
 */
fun selectionSort(arr: IntArray) {
    val n = arr.size
    // Outer loop iterates over each element except the last one.
    // After each pass, the element at index 'i' is placed in its correct sorted position.
    for (i in 0 until n - 1) {
        var minIndex = i  // Assume the current index 'i' holds the smallest element.

        // Inner loop scans the rest of the array to find the actual smallest element.
        for (j in i + 1 until n) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j  // Update the index of the smallest element found so far.
            }
        }

        // Swap the smallest found element with the element at index 'i',
        // but only if they are different.
        if (minIndex != i) {
            val tmp = arr[i]
            arr[i] = arr[minIndex]
            arr[minIndex] = tmp
        }
    }
}
