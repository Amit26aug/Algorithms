package com.august.graphs.dijkstra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

class Edge {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }   
}

class Node implements Comparable<Node> {
    int nodeId;
    int value;

    public Node(int nodeId, int value) {
        this.nodeId = nodeId;
        this.value = value;
    }

    @Override
    public int compareTo(Node obj) {
        return this.value - obj.value;
    }
}

class DijkstraAlgorithmUtil {

    public static Map<Integer, Integer> shortestPathFrom(Map<Integer, List<Edge>> adjacencyList, int start) {
        return shortestPathFrom(adjacencyList, start, -1);
    }

    public static int shortestPathBetween(Map<Integer, List<Edge>> adjacencyList, int start, int end) {
        Map<Integer, Integer> shortestDistance = shortestPathFrom(adjacencyList, start, end);
        return shortestDistance.get(end);
    }

    private static Map<Integer, Integer> shortestPathFrom(Map<Integer, List<Edge>> adjacencyList, int start, int end) {
        Map<Integer, Integer> shortestDistance = new HashMap<>();
        shortestDistance.put(start, 0);
        dijkstra(adjacencyList, start, end, new HashSet<Integer>(), shortestDistance);
        return shortestDistance;
    }

    
    private static void dijkstra(Map<Integer, List<Edge>> adjacencyList, int source, int destination, Set<Integer> visited,
                                 Map<Integer, Integer> shortestDistance) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(source, 0));
        while (priorityQueue.size() > 0) {
            Node current = priorityQueue.poll();
            visited.add(current.nodeId);
            // Skip it if it not at a better distance already
            if (shortestDistance.get(current.nodeId) < current.value) {
                continue;
            }
            if (adjacencyList.get(current.nodeId) == null) {
                continue;
            }
            for (Edge edge: adjacencyList.get(current.nodeId)) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                int newDistance = current.value + edge.weight;
                if (shortestDistance.get(edge.to) == null || shortestDistance.get(edge.to) > newDistance) {
                    shortestDistance.put(edge.to, newDistance);
                    priorityQueue.add(new Node(edge.to, shortestDistance.get(edge.to)));
                }
            }
            if (current.nodeId == destination) {
                return;
            }
        }
    }
}

public class DijkstraSingleSourceShortestPath {
    public static void main(String[] args) {
        Map<Integer, List<Edge>> adjacencyList = new HashMap<>();
        int source = 0;
        adjacencyList.put(0, Arrays.asList(new Edge(0, 1, 5), new Edge(0, 4, 4)));
        adjacencyList.put(1, Arrays.asList(new Edge(1, 2, 6)));
        adjacencyList.put(2, Arrays.asList(new Edge(2, 3, 7)));
        adjacencyList.put(3, Arrays.asList());
        adjacencyList.put(4, Arrays.asList(new Edge(4, 1, 5), new Edge(4, 2, 3)));
        for (Entry<Integer, Integer> entry: DijkstraAlgorithmUtil.shortestPathFrom(adjacencyList, source).entrySet()) {
            System.out.println(source +  "->" + entry.getKey() + ": " + entry.getValue());
        }
        int destination = 2;
        System.out.println(source + " to " + destination + ": " 
            + DijkstraAlgorithmUtil.shortestPathBetween(adjacencyList, source, destination));
    }
}
