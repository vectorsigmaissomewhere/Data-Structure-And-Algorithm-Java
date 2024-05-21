// Given patterns that may have overlapping occurrences in the text, find all occurrences.
import java.util.*;

public class AhoCorasickAlgorithm {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        TrieNode failLink = null;
        List<String> output = new ArrayList<>();
    }

    public static TrieNode buildTrie(String[] patterns) {
        TrieNode root = new TrieNode();
        for (String pattern : patterns) {
            TrieNode current = root;
            for (char c : pattern.toCharArray()) {
                current = current.children.computeIfAbsent(c, k -> new TrieNode());
            }
            current.output.add(pattern);
        }
        return root;
    }

    public static void buildFailureLinks(TrieNode root) {
        Queue<TrieNode> queue = new LinkedList<>();
        root.failLink = root;
        for (TrieNode node : root.children.values()) {
            node.failLink = root;
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (Map.Entry<Character, TrieNode> entry : current.children.entrySet()) {
                char c = entry.getKey();
                TrieNode child = entry.getValue();
                TrieNode fail = current.failLink;
                while (fail != root && !fail.children.containsKey(c)) {
                    fail = fail.failLink;
                }
                if (fail.children.containsKey(c) && fail.children.get(c) != child) {
                    child.failLink = fail.children.get(c);
                } else {
                    child.failLink = root;
                }
                child.output.addAll(child.failLink.output);
                queue.add(child);
            }
        }
    }

    public static Map<String, List<Integer>> search(String text, TrieNode root) {
        Map<String, List<Integer>> result = new HashMap<>();
        TrieNode current = root;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            while (current != root && !current.children.containsKey(c)) {
                current = current.failLink;
            }
            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            } else {
                current = root;
            }

            for (String pattern : current.output) {
                result.computeIfAbsent(pattern, k -> new ArrayList<>()).add(i - pattern.length() + 1);
            }
        }
        return result;
    }
  
  public static void main(String[] args) {
    String[] patterns = {"ab", "bab"};
    String text = "ababbab";
    TrieNode root = buildTrie(patterns);
    buildFailureLinks(root);
    Map<String, List<Integer>> result = search(text, root);

    for (Map.Entry<String, List<Integer>> entry : result.entrySet()) {
        System.out.println("Pattern: " + entry.getKey() + ", Positions: " + entry.getValue());
    }
  }
}

/*
Pattern: ab, Positions: [0, 2, 5]
Pattern: bab, Positions: [1, 4]
*/

/*
Explanation
Patterns "ab" and "bab" overlap in the text "ababbab". 
The algorithm correctly identifies all starting positions of both patterns, including overlaps.
*/
