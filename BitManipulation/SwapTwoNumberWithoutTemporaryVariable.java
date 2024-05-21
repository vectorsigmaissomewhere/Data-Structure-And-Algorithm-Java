// Description: Swap two integers without using a temporary variable.
public class SwapNumbers {
    public static void swap(int a, int b) {
        System.out.println("Before swap: a = " + a + ", b = " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("After swap: a = " + a + ", b = " + b);
    }

    public static void main(String[] args) {
        int a = 5, b = 3;
        swap(a, b);
    }
}
