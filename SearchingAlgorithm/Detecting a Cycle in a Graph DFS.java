/*
Detecting a cycle in an undirected graph can be solved using Depth-First Search (DFS). 
During the DFS traversal, if we encounter a visited node that is not the parent of the current node, 
a cycle is detected.
*/
import java.util.*;

public class DFSCycleDetection {

    public static boolean hasCycle(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(graph, node, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(Map<Integer, List<Integer>> graph, int current, Set<Integer> visited, int parent) {
        visited.add(current);

        for (int neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                if (dfs(graph, neighbor, visited, current)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(0, 3));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(1, 2, 4));
        graph.put(4, Arrays.asList(3, 5));
        graph.put(5, Arrays.asList(4));

        boolean cycle = hasCycle(graph);
        System.out.println("Does the graph have a cycle? " + cycle);
    }
}

// Does the graph have a cycle? true
