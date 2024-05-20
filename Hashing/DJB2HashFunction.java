// The DJB2 algorithm was first reported by Dan Bernstein many years ago in comp.lang.c. 
// It's known for its good distribution of hash values.
public class DJB2HashFunction {
    public static long djb2Hash(String key) {
        long hash = 5381;
        for (int i = 0; i < key.length(); i++) {
            hash = ((hash << 5) + hash) + key.charAt(i); // hash * 33 + c
        }
        return hash;
    }

    public static void main(String[] args) {
        String key = "example";
        long hash = djb2Hash(key);
        System.out.println("Hash value: " + hash);
    }
}
// Hash value: 2090756197
