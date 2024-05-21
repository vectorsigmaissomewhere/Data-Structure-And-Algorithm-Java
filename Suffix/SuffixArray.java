import java.util.Arrays;

public class SuffixArray {

    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        Suffix[] suffixes = new Suffix[n];
        
        // Create suffixes
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(s, i);
        }
        
        // Sort suffixes
        Arrays.sort(suffixes);
        
        // Store indexes of sorted suffixes
        int[] suffixArr = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArr[i] = suffixes[i].index;
        }
        
        return suffixArr;
    }

    static class Suffix implements Comparable<Suffix> {
        String text;
        int index;
        
        Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        public int compareTo(Suffix other) {
            return this.text.substring(this.index).compareTo(other.text.substring(other.index));
        }
    }

    public static void main(String[] args) {
        String s = "banana";
        int[] suffixArr = buildSuffixArray(s);
        System.out.println("Suffix Array: " + Arrays.toString(suffixArr));
    }
}

// Suffix Array: [5, 3, 1, 0, 4, 2]
