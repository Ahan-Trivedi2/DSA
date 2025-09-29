package priorityQueue

import referenceHeap.MinHeap

/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
interface MinPriorityQueue<T> {
    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean

    /**
     * Add [elem] with at level [priority]
     */
    fun addWithPriority(elem: T, priority: Double)

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T?

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double)
}

/**
 * Implementation of a priority queue using a [MinHeap].
 *
 * This class wraps a [MinHeap] to maintain elements along with their priorities.
 * Lower priority values are dequeued before higher ones.
 *
 * @param T the type of elements stored in the priority queue
 */
class MinHeapPriorityQueue<T> : MinPriorityQueue<T> {
    private val heap = MinHeap<T>()

    /**
     * Check if the priority queue is empty.
     *
     * @return `true` if there are no elements in the queue, `false` otherwise
     */
    override fun isEmpty(): Boolean {
        return heap.isEmpty()
    }

    /**
     * Add a new element with the specified [priority] into the priority queue.
     *
     * If the element already exists in the queue, it will not be added again.
     *
     * @param elem the element to insert
     * @param priority the priority value associated with [elem];
     *   smaller values mean higher priority (dequeued earlier)
     */
    override fun addWithPriority(elem: T, priority: Double) {
        heap.insert(elem, priority)
    }

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    override fun next(): T? {
        return heap.getMin()
    }

    /**
     * Adjust the priority of an existing element in the queue.
     *
     * If the element is present, its priority is updated to [newPriority],
     * and the internal heap is adjusted accordingly.
     *
     * @param elem the element whose priority should be changed
     * @param newPriority the new priority value to assign
     */
    override fun adjustPriority(elem: T, newPriority: Double) {
        heap.adjustHeapNumber(elem, newPriority)
    }
}