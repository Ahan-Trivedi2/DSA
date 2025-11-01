package lempel_ziv

import associative_array.HashAssociativeArray

/**
 * Compresses a string using the Lempel–Ziv algo.
 *
 * Builds a dictionary of previously seen substrings and outputs
 * pairs of (prefixIndex, nextCharacter).
 *
 * Example:
 * Input:  "ABABABA"
 * Output: [(0, A), (0, B), (1, B), (3, A)]
 */

fun lzcompress(input: String) : List<Pair<Int, Char>> {
    // Use custom associative array that I built
    val dict = HashAssociativeArray<String, Int>()
    // create an array list to store our output
    val output = mutableListOf<Pair<Int, Char>>()
    // current prefix
    var current = ""
    // next available dictionary index
    var indexCounter = 1
    // lets loop over the chars in our input string and start creating our output array
    for (char in input) {
        // this helps us extend the current prefix by adding the new character
        val candidate = current + char
        // if we have seen this string in the dict, lets not add it anywhere
        // but lets adjust current to ensure that it encompasses this substring
        if (dict.contains(candidate)) {
            current = candidate
        }
        // else we found a new pattern to output
        else
        {
            // we assume the prefix index (the previous portion the new substring depends on)
            // is zero at first, but if current is not empty, we know its the value
            // associated with dict[current]
            var prefixIndex = 0
            if (current.isNotEmpty()) {
                prefixIndex = dict[current]!!
            }
            // now we want to change the output list
            output.add(Pair(prefixIndex, char))
            // we also want to adjust the hashmap we created
            dict[candidate] = indexCounter
            indexCounter++
            // reset current
            current = ""
        }
    }
    // Handle leftover prefix if the string ends with an existing pattern
    if (current != "") {
        val prefixIndex = dict[current]!!
        output.add(Pair(prefixIndex, '\u0000')) // NULL char marks "no next char"
    }
    // return output
    return output
}