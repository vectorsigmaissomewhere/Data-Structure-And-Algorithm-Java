/*
Binary search can also be adapted to find the first occurrence of a 
target value in a sorted array that may contain duplicates.
*/
public class FirstOccurrenceBinarySearch {
    public static int firstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // look on the left side
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int result = firstOccurrence(arr, target);
        if (result == -1) {
            System.out.println("Element not present in array");
        } else {
            System.out.println("First occurrence of element found at index " + result);
        }
    }
}

//  First occurrence of element found at index 1
