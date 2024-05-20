/*
Given two strings, find the minimum number of operations required 
to convert one string into the other. The allowed operations are insert, delete, and replace.
*/
public class EditDistance {
    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // Insert all j characters of s2 into s1
                } else if (j == 0) {
                    dp[i][j] = i; // Remove all i characters of s1
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // Replace
                                            Math.min(dp[i - 1][j], // Remove
                                                     dp[i][j - 1])); // Insert
                }
            }
        }
        
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "sunday";
        String s2 = "saturday";
        System.out.println("Minimum edit distance: " + editDistance(s1, s2));
    }
}

// Minimum edit distance: 3
