/*
Huffman coding is a greedy algorithm used for lossless data compression. Given a set of characters 
and their frequencies, the goal is to build a prefix-free binary code (Huffman Tree) with minimum 
cost (weighted path length).
*/
import java.util.PriorityQueue;

class HuffmanNode {
    int frequency;
    char character;
    HuffmanNode left, right;
    
    public HuffmanNode(int frequency, char character) {
        this.frequency = frequency;
        this.character = character;
    }
}

class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        return o1.frequency - o2.frequency;
    }
}

public class HuffmanCoding {
    public static void printCodes(HuffmanNode root, String code) {
        if (root == null) return;
        
        if (root.character != '-') {
            System.out.println(root.character + ": " + code);
        }
        
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void buildHuffmanTree(char[] characters, int[] frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new HuffmanComparator());
        
        for (int i = 0; i < characters.length; i++) {
            queue.add(new HuffmanNode(frequencies[i], characters[i]));
        }
        
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode node = new HuffmanNode(left.frequency + right.frequency, '-');
            node.left = left;
            node.right = right;
            queue.add(node);
        }
        
        printCodes(queue.poll(), "");
    }

    public static void main(String[] args) {
        char[] characters = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] frequencies = { 5, 9, 12, 13, 16, 45 };
        
        buildHuffmanTree(characters, frequencies);
    }
}

/*
f: 0
c: 100
d: 101
a: 1100
b: 1101
e: 111
*/
