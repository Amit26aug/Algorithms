package com.august.algorithms.graphtheory.bridgesorcutedges;

import java.util.ArrayList;
import java.util.List;

class Edge {
    int from, to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

class BridgesOrCutEdgesSolver {
    // the number of vertices in the graph
    int n; 
    // the adjacency list representing the graph
    List<List<Integer>> adjacencyList;
    // the ids to be assigned to the vertices
    int currentId;
    // the array of ids for the vertices
    int[] ids;
    // the array of low links for the vertices
    int[] lowLinks;
    // the array to track visited nodes
    boolean[] visited;

    public BridgesOrCutEdgesSolver(int n, List<List<Integer>> adjacencyList) {
        this.n = n;
        this.adjacencyList = adjacencyList;
    }

    public List<Edge> solve() {
        currentId = 0;
        ids = new int[n];
        lowLinks = new int[n];

        visited = new boolean[n];
        
        List<Edge> bridges = new ArrayList<>();

        for (int cur = 0; cur < n; cur++) {
            if (visited[cur]) {
                continue;
            }
            applyDFS(cur, -1, bridges);
        }

        return bridges;
    }

    private void applyDFS(int current, int parent, List<Edge> bridges) {
        ids[current] = lowLinks[current] = currentId++;
        visited[current] = true;
        
        for (int neighbor: adjacencyList.get(current)) {
            if (neighbor == parent) continue;
            if (visited[neighbor]) {
                lowLinks[current] = Math.min(lowLinks[current], ids[neighbor]);
                continue;
            }
            applyDFS(neighbor, current, bridges);
            lowLinks[current] = Math.min(lowLinks[current], lowLinks[neighbor]);
            if (ids[current] < lowLinks[neighbor]) {
                bridges.add(new Edge(current, neighbor));
            }
        }
    }

}


public class BridgesOrCutEdges {
    
    static List<List<Integer>> initializeGraph(int n) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        return adjacencyList;
    }

    static void addEdges(List<List<Integer>> adjacencyList, int from, int ... tos) {
        for (int to: tos) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }
    }

    public static void main(String[] args) {

        int n = 9;
        List<List<Integer>> adjacencyList = initializeGraph(n);
        addEdges(adjacencyList, 0, 1, 2);
        addEdges(adjacencyList, 1, 2);
        addEdges(adjacencyList, 2, 3, 5);
        addEdges(adjacencyList, 3, 4);
        addEdges(adjacencyList, 5, 6, 8);
        addEdges(adjacencyList, 6, 7);
        addEdges(adjacencyList, 7, 8);

        BridgesOrCutEdgesSolver bridgesSolver = new BridgesOrCutEdgesSolver(n, adjacencyList);
        List<Edge> bridges = bridgesSolver.solve();

        for (Edge e: bridges) {
            System.out.println(String.format("Bridge from: %d to %d", e.from, e.to));
        }
    }

}