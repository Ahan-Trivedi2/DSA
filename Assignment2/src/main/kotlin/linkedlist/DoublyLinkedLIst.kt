package linkedlist

// Interface defined on course website
interface LinkedList<T> {
    /**
     * Adds the element [data] to the front of the linked list.
     */
    fun pushFront(data: T)

    /**
     * Adds the element [data] to the back of the linked list.
     */
    fun pushBack(data: T)

    /**
     * Removes an element from the front of the list. If the list is empty, it is unchanged.
     * @return the value at the front of the list or nil if none exists
     */
    fun popFront(): T?

    /**
     * Removes an element from the back of the list. If the list is empty, it is unchanged.
     * @return the value at the back of the list or nil if none exists
     */
    fun popBack(): T?

    /**
     * @return the value at the front of the list or nil if none exists
     */
    fun peekFront(): T?

    /**
     * @return the value at the back of the list or nil if none exists
     */
    fun peekBack(): T?

    /**
     * @return true if the list is empty and false otherwise
     */
    fun isEmpty(): Boolean
}

/**
 * Implementation of a generic doubly linked list.
 *
 * Provides O(1) insertion and removal from both the front and back
 * of the list by maintaining head and tail pointers.
 *
 * @param T the type of elements stored in the list
 */
class DoublyLinkedList<T> : LinkedList<T> {
    /**
     * Private nested class representing a node in the doubly linked list.
     * Each node stores a value and pointers to its previous and next nodes.
     */
    private data class Node<T>(
        var value: T,
        var prev: Node<T>? = null,
        var next: Node<T>? = null
    )
    // Define DLL class properties
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    /**
     * Inserts a new element at the front of the list.
     * @param data the value to insert
     */
    override fun pushFront(data: T) {
        val newNode = Node(data)
        // If our linked list is empty, head is null
        // make new node both head and tail
        if (head == null) {
            head = newNode
            tail = newNode
        } else { // Make our newNode the first in the DLL
            newNode.next = head
            head!!.prev = newNode
            head = newNode
        }
    }

    /**
     * Inserts a new element at the back of the list.
     * @param data the value to insert
     */
    override fun pushBack(data: T) {
        val newNode = Node(data)
        // If our linked list is empty, head is null
        // make new node both head and tail
        if (head == null) {
            head = newNode
            tail = newNode
        } else { // Make our newNode the first in the DLL
            newNode.prev = tail
            tail!!.next = newNode
            tail = newNode
        }
    }

    /**
     * Removes and returns the element at the front of the list.
     * If the list is empty, returns null.
     * @return the removed value or null if the list is empty
     */
    override fun popFront(): T? {
        // Check if the list is empty
        if (head == null) {
            return null
        }
        // If the list is not empty, save the val of current head
        val value = head!!.value
        // Make the new head of the list the next node or null
        head = head!!.next
        // If null, make the tail reference also null (empty DLL)
        if (head == null){
            tail = null
        // Else we have to make the new head have a null prev
        } else {
            head!!.prev = null
        }
        return value
    }

    /**
     * Removes and returns the element at the back of the list.
     * If the list is empty, returns null.
     * @return the removed value or null if the list is empty
     */
    override fun popBack(): T? {
        // Check if the list is empty
        if (head == null) {
            return null
        }
        // If list is !empty, save the val of the current tail
        val value = tail!!.value
        tail = tail!!.prev
        // If tail is null, we know the list was one element, now zero element
        if (tail == null) {
            head = null
        // If tail points to an Node object
        } else {
            tail!!.next = null
        }
        return value
    }

    /**
     * Returns the value at the front of the list without removing it.
     * If the list is empty, returns null.
     * @return the first value or null if the list is empty
     */
    override fun peekFront(): T? {
        if (head == null) {
            return null
        } else {
            return head!!.value
        }
    }

    /**
     * Returns the value at the back of the list without removing it.
     * If the list is empty, returns null.
     * @return the last value or null if the list is empty
     */
    override fun peekBack(): T? {
        if (tail == null) {
            return null
        } else {
            return tail!!.value
        }
    }

    /**
     * Checks if the list is empty.
     * @return true if there are no elements, false otherwise
     */
    override fun isEmpty(): Boolean {
        return (head == null)
    }
}