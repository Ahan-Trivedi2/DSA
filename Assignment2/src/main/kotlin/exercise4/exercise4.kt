package exercise4

// I did this on LeetCode and copy pasted my solution here.

class Solution {
    fun isValid(s: String): Boolean {
        // initialize our stack
        val stack = ArrayDeque<Char>()
        // loop over every char in s and either
        // 1. add it to the stack or 2. pop from the stack
        for (char in s) {
            when (char){
                '(', '{', '[' -> stack.addLast(char)
                ')' -> if (stack.isNotEmpty() && stack.last() == '(') stack.removeLast() else return false
                '}' -> if (stack.isNotEmpty() && stack.last() == '{') stack.removeLast() else return false
                ']' -> if (stack.isNotEmpty() && stack.last() == '[') stack.removeLast() else return false
            }
        }
        // If the stack is empty at the end, and we never had a mismatch, we have valid parentheses
        return stack.isEmpty()
    }
}