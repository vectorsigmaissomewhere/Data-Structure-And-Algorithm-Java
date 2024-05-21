/*
Given an unweighted graph and two nodes (source and target), 
find the shortest path from the source node to the target node. 
This problem can be solved using Breadth-First Search (BFS) since BFS explores nodes layer by layer, 
ensuring that the first time we reach the target node, we do so with the shortest path.
*/
import java.util.*;

public class BFSShortestPath {

    public static List<Integer> bfsShortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> predecessor = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        predecessor.put(start, null);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                return reconstructPath(predecessor, end);
            }

            for (int neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    predecessor.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList(); // no path found
    }

    private static List<Integer> reconstructPath(Map<Integer, Integer> predecessor, int end) {
        List<Integer> path = new ArrayList<>();
        for (Integer at = end; at != null; at = predecessor.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3, 4));
        graph.put(2, Arrays.asList(0, 5, 6));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(1, 5));
        graph.put(5, Arrays.asList(2, 4, 6));
        graph.put(6, Arrays.asList(2, 5));

        int start = 0;
        int end = 6;

        List<Integer> path = bfsShortestPath(graph, start, end);
        System.out.println("Shortest path from " + start + " to " + end + ": " + path);
    }
}

// Shortest path from 0 to 6: [0, 2, 6]
