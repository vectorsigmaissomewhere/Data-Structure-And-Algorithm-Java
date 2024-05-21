//  Given an integer, check if it is a power of two.
public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        int n = 16;
        System.out.println(n + " is power of two: " + isPowerOfTwo(n)); // Output: true
    }
}
