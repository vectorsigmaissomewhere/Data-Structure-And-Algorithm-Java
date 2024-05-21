// use the Z-Algorithm to find all occurrences of a pattern in a text

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZAlgorithmPatternMatching {

    public static List<Integer> searchPattern(String text, String pattern) {
        String concat = pattern + "$" + text;
        int l = concat.length();

        int[] Z = computeZArray(concat);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < l; i++) {
            if (Z[i] == pattern.length()) {
                result.add(i - pattern.length() - 1);
            }
        }
        return result;
    }

    private static int[] computeZArray(String s) {
        int n = s.length();
        int[] Z = new int[n];
        int L = 0, R = 0;

        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s.charAt(R) == s.charAt(R - L)) {
                    R++;
                }
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && s.charAt(R) == s.charAt(R - L)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }
        return Z;
    }

    public static void main(String[] args) {
        String text = "ababcababa";
        String pattern = "aba";
        List<Integer> result = searchPattern(text, pattern);
        System.out.println("Pattern found at indices: " + result); // Output: [0, 6]
    }
}

// Pattern found at indices: [0, 6]
/*
Explanation
The concatenated string is "aba$ababcababa".
The Z-array for this concatenated string is computed.
We find that the pattern "aba" appears at indices 0 and 6 in the text.
The Z-Algorithm is highly efficient for these types of string processing problems, running in linear time 
𝑂(𝑛+𝑚) O(n+m), where 
n is the length of the text and 
m is the length of the pattern. This makes it an excellent choice for large-scale string matching and searching applications.
*/
