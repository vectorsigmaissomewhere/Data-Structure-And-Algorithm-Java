// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
// find the one that is missing from the array.
public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int xor = n; // XOR with the index n since we have n+1 numbers including 0.
        for (int i = 0; i < n; i++) {
            xor ^= i ^ nums[i];
        }
        return xor;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
        System.out.println("Missing number is: " + missingNumber(nums)); // Output: 2
    }
}

// Find Missing Number: Uses XOR to find the missing number in a sequence.
