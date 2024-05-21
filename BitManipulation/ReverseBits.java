// Reverse the bits of a given 32 bits unsigned integer.
public class ReverseBits {
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= (n & 1);
            n >>>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 43261596; // binary representation is 00000010100101000001111010011100
        System.out.println("Reversed bits: " + reverseBits(n)); // Output: 964176192
        // which in binary is 00111001011110000010100101000000
    }
}
