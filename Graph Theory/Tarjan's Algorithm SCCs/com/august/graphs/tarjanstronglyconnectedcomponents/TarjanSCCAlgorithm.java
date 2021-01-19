package com.august.graphs.tarjanstronglyconnectedcomponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class TarjanSCCSolver {
    // constant to define the unvisited vertex id.
    private static final int UNVISITED = -1;

    // the number of vertices in the graph.
    int n;

    // the adjacency list representing the directed graph.
    List<List<Integer>> adjacencyList;
    // the ids of the vertices.
    int[] ids;
    // the low link values of the vertices.
    int[] lowLinks;
    // the variable for assigning ids to the vertices.
    int currentId;
    // the variable representing whether a vertex is on stack or not.
    boolean[] onStack;
    // the stack for holding vertices of strongly connected components.
    Stack<Integer> stack = new Stack<>();
    // the variable to keep track whether the algorithm is run or not.
    boolean solved;
    // the variable to store count of connected components
    int connectComponentsCount;

    public TarjanSCCSolver(int n, List<List<Integer>> adjacencyList) {
        this.n = n;
        this.adjacencyList = adjacencyList;
        this.connectComponentsCount = 0;
    }

    public Map<Integer, Set<Integer>> solve() {
        if (solved) return formStronglyConnectComponentsMap();
        
        ids = new int[n];
        onStack = new boolean[n];
        lowLinks = new int[n];
        currentId = 0;
        Arrays.fill(ids, UNVISITED);

        for (int current = 0; current < n; current++) {
            if (ids[current] != UNVISITED) continue;
            applyDFS(current);
        }
        solved = true;
        return formStronglyConnectComponentsMap();
    }

    private void applyDFS(int current) {
        stack.push(current);
        onStack[current] = true;
        ids[current] = lowLinks[current] = currentId++;

        for (int neighbor: adjacencyList.get(current)) {
            if (ids[neighbor] == UNVISITED) {
                applyDFS(neighbor);
            }
            if (onStack[neighbor]) {
                lowLinks[current] = Math.min(lowLinks[neighbor], lowLinks[current]);
            }

        }
        if (ids[current] == lowLinks[current]) {
            for (int node = stack.pop(); ; node = stack.pop()) {
                lowLinks[node] = ids[current];
                onStack[node] = false;
                if (node == current) {
                    break;
                }
            }
            connectComponentsCount++;
        }
    }

    private Map<Integer, Set<Integer>> formStronglyConnectComponentsMap() {
        Map<Integer, Set<Integer>> sccMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!sccMap.containsKey(lowLinks[i])) {
                sccMap.put(lowLinks[i], new HashSet<>());
            }
            sccMap.get(lowLinks[i]).add(i);
        }
        return sccMap;
    }

    public int getConnectedComponentsCount() {
        return connectComponentsCount;
    }

    
}

public class TarjanSCCAlgorithm {

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
        }
    }

    public static void main(String[] args) {
        int n = 8;
        List<List<Integer>> adjacencyList = initializeGraph(n);
        addEdges(adjacencyList, 0, 1);
        addEdges(adjacencyList, 1, 2);
        addEdges(adjacencyList, 2, 0);
        addEdges(adjacencyList, 3, 4, 7);
        addEdges(adjacencyList, 4, 5);
        addEdges(adjacencyList, 5, 0, 6);
        addEdges(adjacencyList, 6, 0, 2, 4);
        addEdges(adjacencyList, 7, 3, 5);

        TarjanSCCSolver tarjanSCCSolver = new TarjanSCCSolver(n, adjacencyList);
        Map<Integer, Set<Integer>> connectedComponents = tarjanSCCSolver.solve();
        System.out.println("Connected components count: " + tarjanSCCSolver.getConnectedComponentsCount());
        for (int componentId: connectedComponents.keySet()) {
            System.out.println("Nodes: " + connectedComponents.get(componentId) + "form a connect component.");
        } 
    }
}