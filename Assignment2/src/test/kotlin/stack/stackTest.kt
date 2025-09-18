package stack

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

// I wrote the base of all test cases myself, and then used chatGPT to expand on them

/**
 * Unit tests for the DLLStackImplementation class.
 * Each test corresponds 1-to-1 with a public method.
 */
class StackTest {

    /**
     * Tests the push function to ensure an item is added to the top of the stack.
     */
    @Test
    fun testPush() {
        val stack = DLLStackImplementation<Int>()
        stack.push(99)
        assertEquals(99, stack.peek())
    }

    /**
     * Tests the pop function to ensure the top item is removed and returned.
     */
    @Test
    fun testPop() {
        val stack = DLLStackImplementation<String>()
        stack.push("top")
        stack.push("newTop")
        val result = stack.pop()
        assertEquals("newTop", result)
    }

    /**
     * Tests the peek function to ensure it returns the top item without removing it.
     */
    @Test
    fun testPeek() {
        val stack = DLLStackImplementation<Char>()
        stack.push('A')
        val firstPeek = stack.peek()
        assertEquals('A', firstPeek)
        val secondPeek = stack.peek()
        assertEquals('A', secondPeek) // Should still be 'A'
    }

    /**
     * Tests the isEmpty function to confirm it accurately reflects stack state.
     */
    @Test
    fun testIsEmpty() {
        val stack = DLLStackImplementation<Double>()
        assertTrue(stack.isEmpty())
        stack.push(3.14)
        assertFalse(stack.isEmpty())
    }
}
