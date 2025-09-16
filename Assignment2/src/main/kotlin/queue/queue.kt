package queue

import linkedlist.DoublyLinkedList

interface Queue<T> {
    /**
     * Add [data] to the end of the queue.
     */
    fun enqueue(data: T)
    /**
     * Remove the element at the front of the queue.  If the queue is empty, it remains unchanged.
     * @return the value at the front of the queue or nil if none exists
     */
    fun dequeue(): T?
    /**
     * @return the value at the front of the queue or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the queue is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

/**
 * Queue implementation backed by a DoublyLinkedList.
 *
 * Provides standard queue operations (enqueue, dequeue, peek, isEmpty).
 * Follows FIFO (First In, First Out): elements are added at the back (tail)
 * and removed from the front (head).
 *
 * @param T the type of elements stored in the queue
 */
class DLLQueueImplementation<T> : Queue<T> {
    // Underlying data structure used for queue operations
    private val list = DoublyLinkedList<T>()

    /**
     * Adds an element to the back of the queue.
     * @param data the value to insert
     */
    override fun enqueue(data: T) {
        list.pushBack(data)
    }

    /**
     * Removes and returns the element at the front of the queue.
     * @return the removed element, or null if the queue is empty
     */
    override fun dequeue(): T? {
        return list.popFront()
    }

    /**
     * Returns the element at the front without removing it.
     * @return the front element, or null if the queue is empty
     */
    override fun peek(): T? {
        return list.peekFront()
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue has no elements, false otherwise
     */
    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}
