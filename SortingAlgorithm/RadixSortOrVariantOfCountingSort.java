import java.util.Arrays;

public class RadixSort {
    public static void radixSort(int[] array) {
        if (array.length == 0) {
            return;
        }

        // Find the maximum number to determine the number of digits
        int max = Arrays.stream(array).max().getAsInt();

        // Apply counting sort for each digit (from least significant to most significant)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
        }
    }

    private static void countingSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Count occurrences of each digit
        for (int i = 0; i < n; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }

        // Compute prefix sums
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the sorted output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copy the sorted elements back into the original array
        System.arraycopy(output, 0, array, 0, n);
    }

    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}

// Sorted array: [2, 24, 45, 66, 75, 90, 170, 802]

/*
Radix sort is a non-comparative sorting algorithm that sorts numbers by processing individual digits.
It uses counting sort as a subroutine to sort the numbers by each digit, 
starting from the least significant digit to the most significant digit.

*/
