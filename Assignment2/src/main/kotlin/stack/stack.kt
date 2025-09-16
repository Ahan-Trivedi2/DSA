package stack

import linkedlist.DoublyLinkedList

interface Stack<T> {
    /**
     * Add [data] to the top of the stack
     */
    fun push(data: T)
    /**
     * Remove the element at the top of the stack.  If the stack is empty, it remains unchanged.
     * @return the value at the top of the stack or nil if none exists
     */
    fun pop(): T?
    /**
     * @return the value on the top of the stack or nil if none exists
     */
    fun peek(): T?
    /**
     * @return true if the stack is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

/**
 * Stack implementation backed by a DoublyLinkedList.
 *
 * Provides standard stack operations (push, pop, peek, isEmpty).
 * All operations delegate to the underlying doubly linked list.
 *
 * @param T the type of elements stored in the stack
 */
class DLLStackImplementation<T> : Stack<T> {
    private val list = DoublyLinkedList<T>()

    /**
     * Pushes an element onto the stack.
     * @param data the value to insert
     */
    override fun push(data: T) {
        list.pushFront(data)
    }

    /**
     * Removes and returns the top element of the stack.
     * @return the removed element, or null if the stack is empty
     */
    override fun pop(): T? {
        return list.popFront()
    }

    /**
     * Returns the top element without removing it.
     * @return the top element, or null if the stack is empty
     */
    override fun peek(): T? {
        return list.peekFront()
    }

    /**
     * Checks if the stack is empty.
     * @return true if the stack has no elements, false otherwise
     */
    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }
}
