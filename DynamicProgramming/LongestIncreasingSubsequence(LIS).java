// Given an unsorted array of integers, find the length of the longest increasing subsequence.
public class LIS {
    public static int lis(int[] arr) {
        int n = arr.length;
        int[] lis = new int[n];
        
        for (int i = 0; i < n; i++) {
            lis[i] = 1; // Every element is an LIS of length 1 by itself
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < lis[i]) {
                max = lis[i];
            }
        }
        
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println("Length of LIS is: " + lis(arr));
    }
}

// Length of LIS is: 6

