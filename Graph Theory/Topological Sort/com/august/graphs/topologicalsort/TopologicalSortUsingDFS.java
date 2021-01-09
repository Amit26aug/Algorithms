package com.august.graphs.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class TopologicalSortUtil {
    public static List<Integer> topologicalSort(Map<Integer, List<Integer>> adjacencyList) {
        List<Integer> topologicalSequence = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int start: adjacencyList.keySet()) {
            if (!visited.contains(start)) {
                depthFirstSearch(adjacencyList, visited, topologicalSequence, start);
            }
        }
        Collections.reverse(topologicalSequence);
        return topologicalSequence;
    }

    private static void depthFirstSearch(Map<Integer, List<Integer>> adjacencyList, Set<Integer> visited,
     List<Integer> topologicalSequence, int current) {
        visited.add(current);
        for (int neighbor: adjacencyList.get(current)) {
            if (visited.contains(neighbor)) {
                continue;
            }
            depthFirstSearch(adjacencyList, visited, topologicalSequence, neighbor);
        }
        topologicalSequence.add(current);
    }
}

class TopologicalSortUsingDFS {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        adjacencyList.put(0, Arrays.asList(1, 4));
        adjacencyList.put(1, Arrays.asList(2));
        adjacencyList.put(2, Arrays.asList(3));
        adjacencyList.put(3, Arrays.asList());
        adjacencyList.put(4, Arrays.asList(1, 2));

        List<Integer> topologicalSequence = TopologicalSortUtil.topologicalSort(adjacencyList);
        System.out.println(topologicalSequence.stream()
            .map(s -> String.valueOf(s)).collect(Collectors.joining("->")));
    }
}