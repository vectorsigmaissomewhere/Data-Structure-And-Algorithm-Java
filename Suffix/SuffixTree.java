import java.util.*;

public class SuffixTree {

    private final Node root = new Node();

    static class Node {
        Map<Character, Edge> edges = new HashMap<>();
        Node suffixLink;
    }

    static class Edge {
        String label;
        Node targetNode;

        Edge(String label, Node targetNode) {
            this.label = label;
            this.targetNode = targetNode;
        }
    }

    public SuffixTree(String s) {
        build(s);
    }

    private void build(String s) {
        root.suffixLink = root;
        Node activeNode = root;
        int activeLength = 0;
        int remainder = 0;
        String text = s + "$";

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            remainder++;
            Node lastNewNode = null;

            while (remainder > 0) {
                if (activeLength == 0) {
                    if (!activeNode.edges.containsKey(currentChar)) {
                        activeNode.edges.put(currentChar, new Edge(text.substring(i), new Node()));
                        remainder--;
                    } else {
                        activeLength = 1;
                        break;
                    }
                } else {
                    Edge edge = activeNode.edges.get(text.charAt(i - activeLength));
                    String edgeLabel = edge.label;
                    if (edgeLabel.length() > activeLength && edgeLabel.charAt(activeLength) == currentChar) {
                        activeLength++;
                        break;
                    } else {
                        Node splitNode = new Node();
                        activeNode.edges.put(edgeLabel.charAt(0), new Edge(edgeLabel.substring(0, activeLength), splitNode));
                        splitNode.edges.put(edgeLabel.charAt(activeLength), new Edge(edgeLabel.substring(activeLength), edge.targetNode));
                        splitNode.edges.put(currentChar, new Edge(text.substring(i), new Node()));

                        if (lastNewNode != null) {
                            lastNewNode.suffixLink = splitNode;
                        }

                        lastNewNode = splitNode;
                        activeNode = activeNode.suffixLink != null ? activeNode.suffixLink : root;
                        activeLength = activeLength > 0 ? activeLength - 1 : 0;
                        remainder--;
                    }
                }
            }
        }
    }

    public void print() {
        print(root, "");
    }

    private void print(Node node, String indent) {
        for (Map.Entry<Character, Edge> entry : node.edges.entrySet()) {
            System.out.println(indent + entry.getKey() + " -> " + entry.getValue().label);
            print(entry.getValue().targetNode, indent + "  ");
        }
    }

    public static void main(String[] args) {
        String s = "banana";
        SuffixTree st = new SuffixTree(s);
        st.print();
    }
}


/*
b -> banana$
a -> nana$
  n -> na$
    a -> na$
      n -> a$
        a -> $
  n -> a$
    a -> $
n -> na$
  a -> na$
    n -> a$
      a -> $
  n -> a$
    a -> $
*/
