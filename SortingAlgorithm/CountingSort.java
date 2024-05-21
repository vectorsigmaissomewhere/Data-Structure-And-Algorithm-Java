import java.util.Arrays;

public class CountingSort {
    public static void countingSort(int[] array) {
        if (array.length == 0) {
            return;
        }

        // Find the maximum element in the array
        int max = Arrays.stream(array).max().getAsInt();

        // Initialize the count array
        int[] count = new int[max + 1];

        // Count occurrences of each element
        for (int num : array) {
            count[num]++;
        }

        // Compute prefix sums
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Build the sorted output array
        int[] output = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy the sorted elements back into the original array
        System.arraycopy(output, 0, array, 0, array.length);
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 2, 8, 3, 3, 1};
        countingSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}

// Sorted array: [1, 2, 2, 3, 3, 4, 8]


/*
Counting sort is an integer sorting algorithm that sorts an array by counting 
the number of occurrences of each unique element. 
The count is stored in an auxiliary array, and the sorting is done by iterating over the count array. 
This algorithm works well when the range of input values (k) is not significantly larger than the number of elements (n).
*/

/*
Counting Sort Algorithm
Key Concepts
Stable Sort: Maintains the relative order of equal elements.
Auxiliary Arrays: Used for counting occurrences and storing sorted output.
Steps
Count the Occurrences: Create an array to count occurrences of each unique element.
Compute Prefix Sums: Modify the count array such that each element at each index stores
the sum of previous counts. This helps in placing the elements directly in the output array.
Sort the Elements: Build the output array by placing elements at their correct positions and 
updating the count array accordingly.
*/
