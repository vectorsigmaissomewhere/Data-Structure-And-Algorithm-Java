/*
HA-256 (Secure Hash Algorithm 256-bit) is part of the SHA-2 family designed by NSA. 
It is widely used in security applications and protocols, including TLS and SSL, PGP, SSH, IPsec, and more.
*/
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class SHA256HashFunction {
    public static String sha256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 64) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String input = "example";
        String hash = sha256Hash(input);
        System.out.println("SHA-256 hash value: " + hash);
    }
}

// SHA-256 hash value: 50d858dd5d1dc3d52a08f34e97da9e9e720df8c69f274d3342fffe1c1a429d62
