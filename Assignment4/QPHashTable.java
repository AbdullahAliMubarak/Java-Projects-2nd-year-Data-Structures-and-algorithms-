/**
 * Simple hash table implementation using quadratic probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 * 
 * Completed by Abdullah Ali Mubarak (ALMABD030)
 * 18/04/2025
 */
public class QPHashTable extends HashTable {

     /**
     * Create QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when too many probes are needed).
     *
     */
    protected int findIndex(String key) {
        // Calculate initial hash value
        int hashVal = hashFunction(key);
        
        // Quadratic probing loop
        for (int i = 0; i < tableSize(); i++) {
            // Increment probe count for performance tracking
            incProbeCount();
            
            // Calculate current index using quadratic probing: h(k) + i^2
            int currentIndex = (hashVal + i*i) % tableSize();
            
            // If key found, return index
            if (table[currentIndex] != null && table[currentIndex].equals(key)) {
                return currentIndex;
            }
            
            // If empty slot found, return index (for insertion)
            if (table[currentIndex] == null) {
                return currentIndex;
            }
        }
        
        // If we've checked table_size positions and found no match or empty slot,
        // return -1 (failure)
        return -1;
    }
}