package priorityQueue

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/**
 * Unit tests for [MinHeapPriorityQueue].
 *
 * Each test corresponds directly to one function in the priority queue.
 */
class PriorityQueueTest {

    /**
     * Tests [MinHeapPriorityQueue.isEmpty].
     */
    @Test
    fun testIsEmpty() {
        val pq = MinHeapPriorityQueue<String>()
        assertTrue(pq.isEmpty(), "Queue should be empty initially")

        pq.addWithPriority("A", 1.0)
        assertFalse(pq.isEmpty(), "Queue should not be empty after adding an element")
    }

    /**
     * Tests [MinHeapPriorityQueue.addWithPriority].
     */
    @Test
    fun testAddWithPriority() {
        val pq = MinHeapPriorityQueue<String>()
        pq.addWithPriority("A", 2.0)
        pq.addWithPriority("B", 1.0)

        // The lowest priority should be "B"
        assertEquals("B", pq.next(), "Element with lower priority should come out first")
    }

    /**
     * Tests [MinHeapPriorityQueue.next].
     */
    @Test
    fun testNext() {
        val pq = MinHeapPriorityQueue<String>()
        pq.addWithPriority("A", 3.0)
        pq.addWithPriority("B", 5.0)

        assertEquals("A", pq.next(), "Next should return the element with lowest priority")
        assertEquals("B", pq.next(), "Next should then return the remaining element")
        assertEquals(null, pq.next(), "Next on an empty queue should return null")
    }

    /**
     * Tests [MinHeapPriorityQueue.adjustPriority].
     */
    @Test
    fun testAdjustPriority() {
        val pq = MinHeapPriorityQueue<String>()
        pq.addWithPriority("A", 5.0)
        pq.addWithPriority("B", 2.0)

        // Adjust A to have highest priority (smallest number)
        pq.adjustPriority("A", 1.0)

        assertEquals("A", pq.next(), "After adjustment, A should be dequeued first")
    }
}
