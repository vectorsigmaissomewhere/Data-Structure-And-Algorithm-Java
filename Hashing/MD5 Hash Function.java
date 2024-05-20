/*
MD5 (Message-Digest Algorithm 5) is a widely used cryptographic hash function
producing a 128-bit hash value. It is commonly used to check data integrity but 
is not recommended for security purposes due to vulnerabilities.
*/
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class MD5HashFunction {
    public static String md5Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(input.getBytes());
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String input = "example";
        String hash = md5Hash(input);
        System.out.println("MD5 hash value: " + hash);
    }
}

// MD5 hash value: 1a79a4d60de6718e8e5b326e338ae533

