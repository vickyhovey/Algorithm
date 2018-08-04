package datastructures;

public class HashTable {

    private int INITIAL_SIZE = 16;
    private HashEntry[] data; // LinkedList

    class HashEntry {
        String key;
        String value;
        HashEntry next;

        HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    HashTable() { data = new HashEntry[INITIAL_SIZE]; }

    public void put(String key, String value) {

        // Get the index
        int index = getIndxex(key);

        // Create the linked list entry
        HashEntry entry = new HashEntry(key, value);

        // If no entry there - add it
        if (data[index] == null) {
            data[index] = entry;
        }
        // Else handle collision by adding to end of linked list
        else {
            HashEntry entries = data[index];
            // Walk to the end...
            while(entries.next != null) {
                entries = entries.next;
            }
            // Add our new entry there
            entries.next = entry;
        }
    }

    public String get(String key) {

        // Get the index
        int index = getIndex(key);

        // Get the current list of entries
        HashEntry entries = data[index];

        // if we have exising entries against this key...
        if (entries != null) {
            // else walk chain until find a match
            while (!entries.key.equals(key) && entries.next != null) {
                entries = entries.next;
            }
            // then return it
            return entries.value;
        }

        // if we have no entries against this key...
        return null;
    }

    private int getIndex(String key) {
        // Get the hash code
        int hashCode = key.hashCode();
        System.out.println("hashCode = " + hashCode);

        // Convert to index
        int index = (hashCode & 0x7fffffff) % INITIAL_SIZE;  // force hash code to positive
//        int index = hashCode % INITIAL_SIZE; // remainder operation

        System.out.println("index= " + index);

        // Hack to force collision for testing
        if (key.equals("John Simth") || key.equals("Sandra Dee")) {
            index = 4;
        }

        return index;
    }

    public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }


}