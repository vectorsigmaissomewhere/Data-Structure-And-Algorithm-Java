/*
This variation finds the largest element in the array that is less than or equal to the target.
*/
public class LargestLessThanOrEqual {
    public static int largestLessThanOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] <= target) {
                result = mid;
                left = mid + 1; // look on the right side
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int target = 6;
        int result = largestLessThanOrEqual(arr, target);
        if (result == -1) {
            System.out.println("No element less than or equal to target");
        } else {
            System.out.println("Largest element less than or equal to target found at index " + result);
        }
    }
}
/*
Largest element less than or equal to target found at index 2

*/
