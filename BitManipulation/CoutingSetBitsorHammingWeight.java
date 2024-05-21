// Given an integer, count the number of set bits (1s) in its binary representation.
public class CountSetBits {
    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 11; // binary representation is 1011
        System.out.println("Number of set bits: " + hammingWeight(n)); // Output: 3
    }
}

// Counting Set Bits: Uses bitwise AND to check each bit.
