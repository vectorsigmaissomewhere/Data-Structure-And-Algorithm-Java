/*
Prim's algorithm finds a minimum spanning tree for a weighted undirected graph. 
It starts with a single vertex and grows the spanning tree by adding the cheapest possible connection 
from the tree to another vertex.
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
        adj.get(v).add(new int[]{u, weight});
    }

    public void primMST() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];
        
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        pq.add(new int[]{0, 0});
        key[0] = 0;
        
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            inMST[u] = true;
            
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    pq.add(new int[]{v, key[v]});
                    parent[v] = u;
                }
            }
        }
        
        printMST(parent);
    }

    private void printMST(int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + adj.get(i).stream().filter(edge -> edge[0] == parent[i]).findFirst().get()[1]);
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 8);
        g.addEdge(1, 4, 5);
        g.addEdge(2, 4, 7);
        g.addEdge(3, 4, 9);

        g.primMST();
    }
}

/*
Edge    Weight
0 - 1   2
1 - 2   3
0 - 3   6
1 - 4   5
*/
