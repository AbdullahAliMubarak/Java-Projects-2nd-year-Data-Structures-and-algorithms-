import java.util.List;
/**
 * Simple hash table implementation using linear probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 * 
 * Completed by Abdullah Ali Mubarak (ALMABD030)
 * 17/04/2025
 */
public class LPHashTable extends HashTable {

    /**
     * Create LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { super(); }

    /**
     * Create LPHashTable with the given default size table.
     */
    public LPHashTable(final int size) { super(size); }

    /**
     * Find index for entry: if entry is in the table, return position;
     * if it is not in the table then return index of the first free slot.
     * Returns -1 otherwise.
     *
     */
    protected int findIndex(String key) {
        // Calculate initial hash value
        int hashVal = hashFunction(key);
        
        // Linear probing loop - we'll try at most tableSize() times
        for (int i = 0; i < tableSize(); i++) {
            // Increment probe count for performance tracking
            incProbeCount();
            
            // Calculate current index using linear probing: h(k) + i
            int currentIndex = (hashVal + i) % tableSize();
            
            // If key found, return index
            if (table[currentIndex] != null && table[currentIndex].equals(key)) {
                return currentIndex;
            }
            
            // If empty slot, return index (for insertion)
            if (table[currentIndex] == null) {
                return currentIndex;
            }
        }
        
        // If we've checked the entire table and found no match or empty slot,
        // return -1 (table is full)
        return -1;
    }
}