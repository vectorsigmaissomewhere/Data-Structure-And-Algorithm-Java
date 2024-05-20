/*
This binary search variation finds the smallest element in the array
that is greater than or equal to the target.
*/
public class SmallestGreaterThanOrEqual {
    public static int smallestGreaterThanOrEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] >= target) {
                result = mid;
                right = mid - 1; // look on the left side
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        int target = 6;
        int result = smallestGreaterThanOrEqual(arr, target);
        if (result == -1) {
            System.out.println("No element greater than or equal to target");
        } else {
            System.out.println("Smallest element greater than or equal to target found at index " + result);
        }
    }
}
// Smallest element greater than or equal to target found at index 3
