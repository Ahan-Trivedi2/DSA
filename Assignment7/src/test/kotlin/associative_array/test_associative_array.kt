package associative_array

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/**
 * Unit tests for the HashAssociativeArray class.
 * This class verifies core functionality like insert, update, delete,
 * key lookup, and retrieval of all key–value pairs.
 */
class TestHashAssociativeArray {
    /**
     * Tests basic operations of the HashAssociativeArray including:
     * - Adding new key–value pairs
     * - Updating existing keys
     * - Checking key presence
     * - Removing keys
     * - Getting all key–value pairs
     */
    @Test
    fun testBasicOperations() {
        val map = HashAssociativeArray<String, Int>()
        // Insert new key-value pairs
        map["apple"] = 5
        map["banana"] = 10
        assertEquals(2, map.size())
        assertEquals(5, map["apple"])
        assertEquals(10, map["banana"])
        // Update existing key
        map["apple"] = 99
        assertEquals(99, map["apple"])
        // Check key presence
        assertTrue("apple" in map)
        assertFalse("orange" in map)
        // Try getting a non-existent key
        assertNull(map["orange"])
        // Remove a key
        val removed = map.remove("banana")
        assertTrue(removed)
        assertFalse("banana" in map)
        assertEquals(1, map.size())
        // Try removing something not there
        val notRemoved = map.remove("banana")
        assertFalse(notRemoved)
        // Get all key–value pairs
        val pairs = map.keyValuePairs()
        assertEquals(1, pairs.size)
        assertEquals(Pair("apple", 99), pairs[0])
    }
}
