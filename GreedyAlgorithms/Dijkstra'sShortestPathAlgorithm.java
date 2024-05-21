/*
Dijkstra's algorithm finds the shortest path from a source vertex to all other vertices 
in a weighted graph with non-negative weights.
*/
import java.util.*;

class Graph {
    private int V;
    private List<List<int[]>> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new int[]{v, weight});
    }

    public void dijkstra(int src) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        pq.add(new int[]{src, 0});
        
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        
        printSolution(dist);
    }

    private void printSolution(int[] dist) {
        System.out.println("Vertex \tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 9);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 3, 5);
        g.addEdge(0, 4, 3);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 3, 4);

        g.dijkstra(0);
    }
}
/*
Vertex  Distance from Source
0       0
1       8
2       6
3       5
4       3
*/
