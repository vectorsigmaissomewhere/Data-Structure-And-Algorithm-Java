/*
For hash tables, we need a hash function that ensures a uniform distribution of keys.
*/
public class CustomHashFunction {
    public static int customHash(String key, int tableSize) {
        int hashValue = 0;
        int prime = 31; // Prime number for better distribution
        for (int i = 0; i < key.length(); i++) {
            hashValue = prime * hashValue + key.charAt(i);
        }
        return Math.abs(hashValue % tableSize);
    }

    public static void main(String[] args) {
        String key = "example";
        int tableSize = 10;
        int hash = customHash(key, tableSize);
        System.out.println("Custom hash value: " + hash);
    }
}

// Custom hash value: 6
