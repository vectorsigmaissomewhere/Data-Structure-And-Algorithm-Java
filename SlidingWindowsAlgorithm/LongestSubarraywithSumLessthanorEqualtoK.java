// Given an array of integers, find the length of the longest subarray with sum less than or equal to k.
public class LongestSubarraySum {
    public static int longestSubarrayWithSumLessThanOrEqualToK(int[] arr, int k) {
        int n = arr.length;
        int maxLength = 0, left = 0, sum = 0;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > k) {
                sum -= arr[left];
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int k = 7;
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Longest subarray with sum less than or equal to " + k + ": " + longestSubarrayWithSumLessThanOrEqualToK(arr, k));
    }
}
