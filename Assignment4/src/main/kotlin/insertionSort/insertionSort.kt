package insertionSort

/**
 * Sorts the given array of integers in ascending order using insertion sort
 *
 * The algorithm goes through the array one element at a time,
 * and for each element, finds the correct position in the sorted
 * part of the array (to the left) by shifting larger elements right.
 *
 * @param arr The array of integers to sort.
 * @return The same array, sorted in ascending order.
 */
fun insertionSort(arr: IntArray) : IntArray {
    // loop over the array from 1 till the last index
    for (i in 1 until arr.size) {
        // store the value of the current index
        val key = arr[i]
        // j references our sorted leftwards sub-array
        var j = i - 1
        // while loop shifts bigger elements to the right, until j points to the last element smaller than key
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j]
            j--
        }
        // j+1 is the first position where the key should go...dump it there
        arr[j + 1] = key
    }
    return arr
}
