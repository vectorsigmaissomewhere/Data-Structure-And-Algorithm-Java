//Given an array of integers and a number k, find the maximum sum of a subarray of size k.
public class MaxSumSubarray {
    public static int maxSumSubarray(int[] arr, int k) {
        if (arr.length < k) return -1;
        
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }

        int windowSum = maxSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Maximum sum of subarray of size " + k + " is " + maxSumSubarray(arr, k));
    }
}


