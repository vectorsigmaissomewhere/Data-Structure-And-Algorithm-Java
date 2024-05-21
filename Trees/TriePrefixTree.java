class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // Assuming only lowercase 'a' to 'z'
        isEndOfWord = false;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    // Search a word in the trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node != null && node.isEndOfWord;
    }

    // Check if there is any word that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node != null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));     // Output: false
        System.out.println(trie.startsWith("app")); // Output: true

        trie.insert("app");
        System.out.println(trie.search("app"));     // Output: true
    }
}
/*
A Trie (pronounced "try") is a tree-like data structure 
that stores a dynamic set of strings, where the keys are usually strings.
Tries are used for efficient retrieval of a key in a dataset of strings.
Here are some common operations supported by a Trie and their Java implementations:


STEPS 
Insert a word
Search for a word
Check if there is any word that starts with a given prefix



Explanation

Insert a word:

Start from the root and iterate through each character of the word.
For each character, calculate the corresponding index (0-25 for 'a'-'z').
If the child node at that index is null, create a new TrieNode.
Move to the child node and continue with the next character.
After inserting the last character, mark the node as the end of a word.

Search for a word:

Start from the root and iterate through each character of the word.
For each character, calculate the corresponding index.
If the child node at that index is null, return false.
Move to the child node and continue with the next character.
After checking the last character, return true if the current node is marked as the end of a word.

Check if there is any word that starts with a given prefix:

Start from the root and iterate through each character of the prefix.
For each character, calculate the corresponding index.
If the child node at that index is null, return false.
Move to the child node and continue with the next character.
If all characters are found, return true.

Use Cases
Autocomplete:
Tries are commonly used in search engines and text editors to provide suggestions as the user types.
Spell Checker:
Tries can be used to check the spelling of a word efficiently.
IP Routing:
Used in network routers to store routing tables.
Dictionary Implementation:
Efficient storage and retrieval of words and their meanings.

Time Complexity
Insert: O(n), where n is the length of the word.
Search: O(n), where n is the length of the word.
Prefix Search: O(n), where n is the length of the prefix.
Tries provide an efficient way to store and retrieve strings, making them useful for a variety of applications that involve large sets of strings.

*/
