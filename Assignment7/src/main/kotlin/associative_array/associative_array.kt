package associative_array

import kotlin.math.absoluteValue

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 */
interface AssociativeArray<K, V> {
    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    operator fun set(k: K, v: V)

    /**
     * @return true if [k] is a key in the associative array
     */
    operator fun contains(k: K): Boolean

    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    operator fun get(k: K): V?

    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    fun remove(k: K): Boolean

    /**
     * @return the number of elements stored in the hash table
     */
    fun size(): Int

    /**
     * @return the full list of key value pairs for the associative array
     */
    fun keyValuePairs(): List<Pair<K, V>>
}

/**
 * Hash table implementation of the AssociativeArray interface using
 * separate chaining and division-based hashing
 *
 */
class HashAssociativeArray<K, V> (
    // default number of buckets in our hash table
    private var capacity: Int = 11,
    // How full the hash table can get before it is allowed to expand
    private val loadFactor: Double = 0.75
) : AssociativeArray<K, V> {
    // create the buckets array
    private var buckets: Array<MutableList<Entry<K,V>>?> = arrayOfNulls(capacity)
    // we need to keep track of how many key-value pairs (entries) are currently stored in the buckets array
    private var count = 0
    /**
     * Computes the array index (bucket) for a given key.
     *
     * Uses the key's built-in [hashCode] and the division method to map it
     * to a valid index within the current table capacity.
     *
     * @param key the key to hash
     * @return a non-negative index in the range [0, capacity)
     */
    private fun hash(key: K) : Int {
        // return that integer index or 0 if key is null
        return (key?.hashCode()?.absoluteValue ?: 0) % capacity
    }
    /**
     * Rebuilds the hash table with a larger prime capacity when the load factor is exceeded.
     *
     * Doubles the current capacity, finds the next prime number, creates a new bucket array,
     * and reinserts all existing entries to preserve correct key–bucket mappings.
     */
    private fun rehash() {
        // reference to the current buckets array with our hashtable
        val oldBuckets = buckets
        // find the new capacity (current * 2) and then make it the next prime number (we define this)
        capacity = nextPrime(capacity * 2)
        // now lets make 'buckets' reference a new array (of our new capacity)
        buckets = arrayOfNulls(capacity)
        count = 0
        // goes over every oldBuckets entry, and sets it in our new bucket array
        for (bucket in oldBuckets) {
            if (bucket != null) {
                for (entry in bucket) {
                    set(entry.key, entry.value)
                }
            }
        }
    }
    /**
     * Returns the smallest prime number greater than or equal to [n].
     *
     * Used to ensure the hash table capacity is a prime number for better key distribution.
     *
     * @param n the starting number to check
     * @return the next prime number ≥ [n]
     */
    private fun nextPrime(n: Int): Int {
        var num = n
        while (true) {
            if (isPrime(num)) {
                return num
            }
            num++
        }
    }
    /**
     * Determines whether [n] is a prime number.
     *
     * A number is prime if it is greater than 1 and has no divisors
     * other than 1 and itself. Checks divisibility only up to √n for efficiency.
     *
     * @param n the number to test
     * @return true if [n] is prime, false otherwise
     */
    private fun isPrime(n: Int): Boolean {
        if (n < 2) {
            return false
        }
        // only need to check uptil the sqrt of n
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }
    /**
     * Insert the mapping from the key, [k], to the value, [v].
     * If the key already maps to a value, replace the mapping.
     */
    override operator fun set(k: K, v: V) {
        // call the hash function to find the appropriate bucket (index in bucket array)
        val index = hash(k)
        // check if this index has an entry, if not lets put an empty list there
        if (buckets[index] == null) {
            buckets[index] = mutableListOf()
        }
        // assert that the buckets_index we care about it not null and call it bucket (we are going to insert into here)
        val bucket = buckets[index]!!
        // lets loop over every Entry object in this buckets
        for (entry in bucket) {
            // two choices, replace the value of a key that already exists
            // or put a completely new Entry object in
            if (entry.key == k) {
                entry.value = v
                return
            }
        }
        // if we looped over every bucket and our goal Entry object does not exist
        // add it
        bucket.add(Entry(k,v))
        // increment count
        count++
        // since we added something we are going to need to check if we are staying within
        // capacity of the hashmap
        if (count.toDouble() / capacity > loadFactor) {
            rehash()
        }
    }
    /**
     * @return the value associated with the key [k] or null if it doesn't exist
     */
    override operator fun get(k: K): V? {
        // assign bucket to be the arraylist in the specific hash index in buckets
        val bucket = buckets[hash(k)] ?: return null
        // loop over every entry in bucket, if the key is there, get the associated value and return it
        for (entry in bucket) {
            if (entry.key == k) {
                return entry.value
            }
        }
        return null
    }
    /**
     * @return true if [k] is a key in the associative array
     */
    override operator fun contains(key: K) : Boolean {
        if (get(key) != null){
            return true
        }
        else{
            return false
        }
    }
    /**
     * Remove the key, [k], from the associative array
     * @param k the key to remove
     * @return true if the item was successfully removed and false if the element was not found
     */
    override fun remove(k: K): Boolean {
        // find the correct bucket to go to by the k argument hashed
        val index = hash(k)
        // assign bucket to be the arraylist in that bucket, or else return false
        val bucket = buckets[index] ?: return false
        // loop over every entry in bucket...but we need to turn the bucket
        // arraylist into an iterator object b/c of the kotlin compiler
        val iterator = bucket.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.key == k) {
                iterator.remove()
                count--
                return true
            }
        }
        // else the element wasn't found
        return false
    }

    /**
     * @return the number of elements stored in the hash table
     */
    override fun size(): Int {
        return count
    }
    /**
     * @return the full list of key value pairs for the associative array
     */
    override fun keyValuePairs(): List<Pair<K, V>> {
        // initiatize an empty list
        val list = mutableListOf<Pair<K, V>>()
        // loop over every bucket
        for (bucket in buckets) {
            if (bucket != null) {
                for (entry in bucket) {
                    list.add(Pair(entry.key, entry.value))
                }
            }
        }
        return list
    }
}
/**
* Represents a single key–value pair stored in a hash table bucket.
* @param K the type of the key
* @param V the type of the value
*/
data class Entry<K,V> (
    val key: K, // the key used for lookup
    var value: V // the value associated with the key (mutable so we can update)
)