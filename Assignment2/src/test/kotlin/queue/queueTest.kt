package queue

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/**
 * Unit tests for the DLLQueueImplementation class.
 * Each test corresponds 1-to-1 with a public method.
 */
class QueueTest {

    /**
     * Tests the enqueue function to ensure an item is added to the back of the queue.
     */
    @Test
    fun testEnqueue() {
        val queue = DLLQueueImplementation<Int>()
        queue.enqueue(10)
        assertEquals(10, queue.peek())
    }

    /**
     * Tests the dequeue function to ensure the front item is removed and returned.
     */
    @Test
    fun testDequeue() {
        val queue = DLLQueueImplementation<String>()
        queue.enqueue("first")
        queue.enqueue("second")
        val result = queue.dequeue()
        assertEquals("first", result)
    }

    /**
     * Tests the peek function to ensure it returns the front item without removing it.
     */
    @Test
    fun testPeek() {
        val queue = DLLQueueImplementation<Char>()
        queue.enqueue('A')
        val firstPeek = queue.peek()
        assertEquals('A', firstPeek)
        val secondPeek = queue.peek()
        assertEquals('A', secondPeek)
    }

    /**
     * Tests the isEmpty function to confirm it accurately reflects queue state.
     */
    @Test
    fun testIsEmpty() {
        val queue = DLLQueueImplementation<Double>()
        assertTrue(queue.isEmpty())
        queue.enqueue(3.14)
        assertFalse(queue.isEmpty())
    }
}
