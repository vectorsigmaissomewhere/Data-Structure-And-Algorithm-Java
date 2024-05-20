/*
The Fibonacci sequence is a classic example of a problem that can be solved efficiently using dynamic programming. The sequence is defined as:

F(0) = 0
F(1) = 1
F(n) = F(n-1) + F(n-2) for n >= 2
*/
public class FibonacciDP {
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        
        return fib[n];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci of " + n + " is: " + fibonacci(n));
    }
}
// Fibonacci of 10 is: 55
