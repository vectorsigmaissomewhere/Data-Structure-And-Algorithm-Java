// Given an array of positive integers and a positive integer s,
// find the minimal length of a contiguous subarray of which the sum is at least s.
// If there isn't one, return 0 instead.

public class MinSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int left = 0, sum = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (sum >= s) {
                minLength = Math.max(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int s = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println("Minimum size subarray sum: " + minSubArrayLen(s, nums));
    }
}
