public class ManacherAlgorithm {

    // Function to preprocess the string
    private static String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append('^'); // Starting boundary
        for (int i = 0; i < s.length(); i++) {
            sb.append('#');
            sb.append(s.charAt(i));
        }
        sb.append('#');
        sb.append('$'); // Ending boundary
        return sb.toString();
    }

    // Function to find the longest palindromic substring
    public static String longestPalindromicSubstring(String s) {
        if (s == null || s.length() == 0) return "";

        String T = preprocess(s);
        int n = T.length();
        int[] P = new int[n]; // Array to store the radius of palindrome around each center
        int C = 0, R = 0; // Center and right boundary of the current rightmost palindrome

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * C - i; // Calculate mirror i' of i

            // If i is within the right boundary R, use previously computed minimum value
            if (i < R)
                P[i] = Math.min(R - i, P[mirror]);

            // Attempt to expand palindrome centered at i
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 + P[i]))
                P[i]++;

            // If the expanded palindrome at i is beyond the right boundary R, update C and R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }

        // Find the maximum element in P and its index
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // Extract the longest palindromic substring from the original string
        int start = (centerIndex - maxLen) / 2; // Remove the '#' characters
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Longest Palindromic Substring: " + longestPalindromicSubstring(s));

        s = "cbbd";
        System.out.println("Longest Palindromic Substring: " + longestPalindromicSubstring(s));
    }
}
/*
Longest Palindromic Substring: bab
Longest Palindromic Substring: bb

*/
/*
Manacher’s Algorithm Steps
Transform the original string to handle palindromes of both even and odd lengths uniformly. 
This is done by inserting a special character (usually #) between each character and at the ends of the string.
Use an array P to store the radius of the palindrome centered at each position in the transformed string.
Iterate through the transformed string to fill the P array using the previously computed values to skip unnecessary comparisons.
Find the longest palindrome by scanning the P array.
*/

/*
Explanation
Preprocessing:

Insert special characters (#) between each character of the
original string and add boundary characters (^ and $) at the start and end.
This ensures that every palindrome (including even-length ones) can be treated as 
centered around a single character in the transformed string.
Array P:

P[i] contains the radius of the palindrome centered at T[i].
The radius is the number of characters that the palindrome extends on either side from the center i.
Iterate and Expand:

For each character in the transformed string, attempt to expand the palindrome centered at that character.
Use the previously computed values in P to minimize the number of comparisons.
Update the center C and right boundary R whenever a longer palindrome is found.
Finding the Longest Palindromic Substring:

The maximum value in P gives the radius of the longest palindromic substring.
The corresponding center index can be used to extract the actual substring from the original string.
Advantages
Linear Time Complexity: Manacher’s algorithm runs in O(n) time, making it very efficient 
for finding the longest palindromic substring.
Uniform Handling of Even and Odd Lengths: By transforming the string, 
it simplifies the handling of both even and odd length palindromes.
Manacher's algorithm is a powerful technique for string processing problems, 
especially those involving palindromes. Its linear time complexity and elegant approach make it a preferred choice for such tasks.
*/
