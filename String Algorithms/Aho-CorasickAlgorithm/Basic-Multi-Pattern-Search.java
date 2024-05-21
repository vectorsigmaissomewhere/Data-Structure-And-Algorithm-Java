// Given a list of patterns and a text, find all occurrences of each pattern in the text.
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
        String[] patterns = {"he", "she", "his", "hers"};
        String text = "ahishers";
        TrieNode root = buildTrie(patterns);
        buildFailureLinks(root);
        Map<String, List<Integer>> result = search(text, root);

        for (Map.Entry<String, List<Integer>> entry : result.entrySet()) {
            System.out.println("Pattern: " + entry.getKey() + ", Positions: " + entry.getValue());
        }
    }
}
/*
Pattern: she, Positions: [3]
Pattern: he, Positions: [1, 4]
Pattern: his, Positions: [1]
Pattern: hers, Positions: [4]
*/

/*
Explanation

Trie Construction:
Each pattern is inserted into the trie. The trie node contains children nodes, fail link, and output list.

Failure Links Construction:
BFS is used to build the failure links. If a character c is not found in the current node,
follow the failure link until a node with the child c is found or the root is reached.

Search:
Traverse the text character by character. If a character is not found, follow the failure link.
When a match is found, record the position in the result.
*/
