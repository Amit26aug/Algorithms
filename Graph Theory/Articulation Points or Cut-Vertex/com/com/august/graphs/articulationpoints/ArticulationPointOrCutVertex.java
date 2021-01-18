package com.august.graphs.articulationpoints;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class ArticulationPointsSolver {
    // the number of vertices in the graph
    int n;
    // the adjacency list representing the graph
    List<List<Integer>> adjacencyList;
    // the ids to be assigned to the vertices
    int currentId;
    // the array storing the ids of the vertices
    int[] ids;
    // the array storing the low links of the vertices
    int[] lowLinks;
    // the array to track visited vertices
    boolean[] visited;
    // variable to track the number of outgoing edges for root vertices
    int rootVertexOutgoingEdges;

    public ArticulationPointsSolver(int n, List<List<Integer>> adjacencyList) {
        this.n = n;
        this.adjacencyList = adjacencyList;
    }

    public Set<Integer> solve() {
        ids = new int[n];
        visited = new boolean[n];
        lowLinks = new int[n];

        currentId = 0;

        Set<Integer> articulationPoints = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            rootVertexOutgoingEdges = 0;
            applyDFS(i, -1, i, articulationPoints);
            if (articulationPoints.contains(i) && rootVertexOutgoingEdges <= 1) {
                articulationPoints.remove(i);
            }
            if (rootVertexOutgoingEdges > 1) {
                articulationPoints.add(i);
            }
        }
        return articulationPoints;
    }

    private void applyDFS(int current, int parent, int root, Set<Integer> articulationPoints) {
        // counting the outgoing edges from the root
        if (current == root) rootVertexOutgoingEdges++;

        ids[current] = lowLinks[current] = currentId++;
        visited[current] = true;

        for (int neighbor: adjacencyList.get(current)) {
            if (neighbor == parent) continue;
            if (visited[neighbor]) {
                lowLinks[current] = Math.min(lowLinks[current], ids[neighbor]);
                continue;
            }
            applyDFS(neighbor, current, root, articulationPoints);
            lowLinks[current] = Math.min(lowLinks[current], lowLinks[neighbor]);
            if (ids[current] <= lowLinks[neighbor]) {
                // if we have a bridge, then we can get an articulation point (<)
                // if there is a cycle, then also we can get an articulation point (=)
                articulationPoints.add(current);
            }
        }
    }

}

public class ArticulationPointOrCutVertex {

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

        ArticulationPointsSolver articulationPointsSolver = new ArticulationPointsSolver(n, adjacencyList);
        Set<Integer> articulationPoints = articulationPointsSolver.solve();

        System.out.println(String.format("Articulation points or cut-vertices: %s",
                        articulationPoints.stream().map(vertex 
                            -> String.valueOf(vertex)).collect(Collectors.joining(", "))));

    }
}