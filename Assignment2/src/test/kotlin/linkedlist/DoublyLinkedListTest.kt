package linkedlist

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/**
 * Unit tests for the DoublyLinkedList implementation.
 * Tests are written in the same order as the LinkedList interface.
 */

// I wrote the base of all test cases myself, and then used chatGPT to expand on them
class DoublyLinkedListTest {

    /**
     * Tests inserting elements at the front using [pushFront].
     * Ensures that the head updates correctly.
     */
    @Test
    fun testPushFront() {
        val list = DoublyLinkedList<Int>()
        assertTrue(list.isEmpty(), "List should start empty")

        list.pushFront(10)
        assertEquals(10, list.peekFront(), "Front should now be 10")
        assertEquals(10, list.peekBack(), "Back should also be 10 since it's the only element")

        list.pushFront(20)
        assertEquals(20, list.peekFront(), "Front should update to 20 after pushFront")
        assertEquals(10, list.peekBack(), "Back should remain 10")
    }

    /**
     * Tests inserting elements at the back using [pushBack].
     * Ensures that the tail updates correctly.
     */
    @Test
    fun testPushBack() {
        val list = DoublyLinkedList<String>()
        list.pushBack("A")
        list.pushBack("B")

        assertEquals("A", list.peekFront(), "Front should be A after first insert")
        assertEquals("B", list.peekBack(), "Back should be B after second insert")
    }

    /**
     * Tests removing elements from the front using [popFront].
     * Ensures that values are returned in the correct order and
     * that the head updates correctly.
     */
    @Test
    fun testPopFront() {
        val list = DoublyLinkedList<Int>()
        list.pushBack(1)
        list.pushBack(2)

        assertEquals(1, list.popFront(), "Popping front should return 1")
        assertEquals(2, list.peekFront(), "Front should now be 2")
        assertEquals(2, list.popFront(), "Next popFront should return 2")
        assertTrue(list.isEmpty(), "List should now be empty")
        assertNull(list.popFront(), "Popping from empty list should return null")
    }

    /**
     * Tests removing elements from the back using [popBack].
     * Ensures that values are returned in the correct order and
     * that the tail updates correctly.
     */
    @Test
    fun testPopBack() {
        val list = DoublyLinkedList<Int>()
        list.pushBack(5)
        list.pushBack(6)

        assertEquals(6, list.popBack(), "Popping back should return 6")
        assertEquals(5, list.peekBack(), "Back should now be 5")
        assertEquals(5, list.popBack(), "Next popBack should return 5")
        assertTrue(list.isEmpty(), "List should now be empty")
        assertNull(list.popBack(), "Popping from empty list should return null")
    }

    /**
     * Tests peeking at the front using [peekFront].
     * Ensures the value is returned without removal.
     */
    @Test
    fun testPeekFront() {
        val list = DoublyLinkedList<Int>()
        assertNull(list.peekFront(), "Peeking an empty list should return null")

        list.pushBack(42)
        assertEquals(42, list.peekFront(), "Front should be 42 after one insert")
        assertFalse(list.isEmpty(), "List should not be empty after insert")
    }

    /**
     * Tests peeking at the back using [peekBack].
     * Ensures the value is returned without removal.
     */
    @Test
    fun testPeekBack() {
        val list = DoublyLinkedList<Int>()
        assertNull(list.peekBack(), "Peeking an empty list should return null")

        list.pushBack(100)
        list.pushBack(200)
        assertEquals(200, list.peekBack(), "Back should be 200 after two inserts")
        assertEquals(100, list.peekFront(), "Front should still be 100")
    }

    /**
     * Tests checking if the list is empty using [isEmpty].
     * Ensures correct behavior after inserts and removals.
     */
    @Test
    fun testIsEmpty() {
        val list = DoublyLinkedList<Int>()
        assertTrue(list.isEmpty(), "New list should be empty")

        list.pushFront(1)
        assertFalse(list.isEmpty(), "List should not be empty after adding an element")

        list.popBack()
        assertTrue(list.isEmpty(), "List should be empty again after removing the only element")
    }
}
