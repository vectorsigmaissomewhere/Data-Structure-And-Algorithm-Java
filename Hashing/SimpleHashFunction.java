// A simple hash function could be something like summing the ASCII values 
// of the characters in a string and then taking the modulus with a prime number.
public class SimpleHashFunction {
    public static int simpleHash(String key, int tableSize) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            hashValue += key.charAt(i);
        }
        return hashValue % tableSize;
    }

    public static void main(String[] args) {
        String key = "example";
        int tableSize = 10;
        int hash = simpleHash(key, tableSize);
        System.out.println("Hash value: " + hash);
    }
}


// Hash value: 8
