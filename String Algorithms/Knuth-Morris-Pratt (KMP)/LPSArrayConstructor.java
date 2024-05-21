public class KMPAlgorithm {
    // Function to compute the LPS array
    public static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;

        lps[0] = 0; // lps[0] is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // KMP algorithm for pattern searching
    public static void KMPSearch(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();

        // Create lps[] that will hold the longest prefix suffix values for the pattern
        int[] lps = computeLPSArray(pattern);

        int i = 0; // Index for text[]
        int j = 0; // Index for pattern[]

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }

            if (j == m) {
                System.out.println("Found pattern at index " + (i - j));
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        KMPSearch(pattern, text);
    }
}

// Found pattern at index 10
/*
Explanation
LPS Array Construction:

The LPS array helps in optimizing the search by holding the length of the longest proper prefix which is also a suffix for the pattern up to that point.
For each character in the pattern, the LPS array is computed by comparing the current character with the longest proper prefix's next character.
Pattern Searching:

The main search phase uses the LPS array to skip characters in the text when a mismatch occurs, avoiding redundant comparisons.
If a match is found, the starting index of the pattern in the text is printed.
Detailed Steps:
Compute LPS Array:

Initialize an array lps of the same length as the pattern.
Set lps[0] to 0, as the longest proper prefix for a single character is always 0.
Use two pointers: i for traversing the pattern and len for keeping track of the length of the previous longest prefix suffix.
If pattern[i] matches pattern[len], increment both i and len and set lps[i] to len.
If there is a mismatch, set len to lps[len - 1] if len is not zero, otherwise set lps[i] to 0 and move to the next character.
KMP Search:

Use two pointers: i for traversing the text and j for traversing the pattern.
If pattern[j] matches text[i], increment both i and j.
If j reaches the length of the pattern, a match is found. Print the start index and set j to lps[j - 1].
If there is a mismatch and j is not zero, set j to lps[j - 1], otherwise increment i.
Advantages of KMP Algorithm
Efficient: The KMP algorithm has a time complexity of O(n + m), where n is the length of the text and m is the length of the pattern.
Avoids Redundant Comparisons: By preprocessing the pattern, it avoids unnecessary comparisons, making it faster than naive string matching algorithms in many cases.
The KMP algorithm is widely used in various applications such as text editors for search and replace functionality, DNA sequence analysis, and more. Its efficiency and simplicity make it a valuable tool in the field of computer science.








*/
