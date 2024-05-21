import java.util.HashSet;
import java.util.Set;

public class RabinKarpMultiplePatterns {
    public final static int d = 256;

    public static void search(String[] patterns, String txt, int q) {
        Set<Integer> patternHashes = new HashSet<>();
        int M = patterns[0].length(); // Assuming all patterns are of same length
        int N = txt.length();
        int t = 0; // hash value for txt
        int h = 1;

        // Calculate hash values for all patterns
        for (String pat : patterns) {
            int p = 0;
            for (int i = 0; i < M; i++)
                p = (d * p + pat.charAt(i)) % q;
            patternHashes.add(p);
        }

        // The value of h would be "pow(d, M-1)%q"
        for (int i = 0; i < M - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of first window of text
        for (int i = 0; i < M; i++)
            t = (d * t + txt.charAt(i)) % q;

        // Slide the pattern over text one by one
        for (int i = 0; i <= N - M; i++) {
            // Check if the hash value of the current window matches any pattern hash
            if (patternHashes.contains(t)) {
                for (String pat : patterns) {
                    if (txt.substring(i, i + M).equals(pat)) {
                        System.out.println("Pattern '" + pat + "' found at index " + i);
                    }
                }
            }

            // Calculate hash value for next window of text: Remove leading digit, add trailing digit
            if (i < N - M) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;

                // We might get negative value of t, converting it to positive
                if (t < 0)
                    t = (t + q);
            }
        }
    }

    public static void main(String[] args) {
        String txt = "GEEKS FOR GEEKS";
        String[] patterns = {"GEEK", "FOR"};
        int q = 101; // A prime number
        search(patterns, txt, q);
    }
}
/*
Pattern 'GEEK' found at index 0
Pattern 'FOR' found at index 6
Pattern 'GEEK' found at index 10
*/

/*
Explanation for Multiple Pattern Search
Hash Calculation for Patterns:

Compute the hash values for all patterns and store them in a set for quick lookup.
Hash Calculation for Text Windows:

Compute the hash value for the first window of the text.
Slide the window over the text and update the hash value using the rolling hash technique.
Hash Comparison:

Check if the hash value of the current window matches any pattern hash.
If there is a match, verify by comparing the actual strings to avoid false positives.

*/
