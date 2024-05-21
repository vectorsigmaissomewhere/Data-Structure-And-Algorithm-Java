// Given a non-empty array of integers where every element appears twice except for one, 
// find that single one.
public class SingleNumber {
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println("Single number is: " + singleNumber(nums)); // Output: 4
    }
}

/*
Find Single Number: XOR of a number with itself is 0, and XOR with 0 is the number itself. 
Thus, XORing all numbers gives the single number.
*/
