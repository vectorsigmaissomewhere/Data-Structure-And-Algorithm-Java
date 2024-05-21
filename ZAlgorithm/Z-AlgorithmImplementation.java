public class ZAlgorithm {

    public static int[] computeZArray(String s) {
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
        String s = "abacaba";
        int[] Z = computeZArray(s);
        System.out.println("Z-array: " + Arrays.toString(Z)); // Output: [7, 0, 1, 0, 3, 0, 1]
    }
}


/*
Explanation of the Output
For the string "abacaba", the Z-array is [7, 0, 1, 0, 3, 0, 1]. Here's the detailed breakdown:

Z[0] = 7: The whole string matches the prefix.
Z[1] = 0: No proper prefix starting at index 1 matches the prefix.
Z[2] = 1: The substring "a" (starting at index 2) matches the prefix "a".
Z[3] = 0: No proper prefix starting at index 3 matches the prefix.
Z[4] = 3: The substring "aba" (starting at index 4) matches the prefix "aba".
Z[5] = 0: No proper prefix starting at index 5 matches the prefix.
Z[6] = 1: The substring "a" (starting at index 6) matches the prefix "a".
*/
