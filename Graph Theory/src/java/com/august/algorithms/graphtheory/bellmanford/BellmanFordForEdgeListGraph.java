package com.august.algorithms.graphtheory.bellmanford;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Edge {
    int from, to;
    double cost;

    Edge(int from, int to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

class BellmanFord {
    public static Map<Integer, Double> bellmanFordSingleSourceShortestPath(List<Edge> edges, int vertexCount, int source) {
        Map<Integer, Double> distanceMap = new HashMap<>();
        for (int i = 0; i < vertexCount; i++) {
            distanceMap.put(i, Double.POSITIVE_INFINITY);
        }
        distanceMap.put(source, 0d);

        // first iteration of bellman ford determines the shortest distance among all paths, including negative
        for (int i = 0; i < vertexCount; i++) {
            for (Edge edge: edges) {
                double distanceTo = distanceMap.get(edge.from) + edge.cost;
                if (distanceTo < distanceMap.get(edge.to)) {
                    distanceMap.put(edge.to, distanceTo);
                }
            }
        }
        
        // second iteration of bellman ford determines the nodes involved in negative cycles or are affected because of the negative cycles.
        // all such nodes are set to a distance of negative infinity.
        for (int i = 0; i < vertexCount; i++) {
            for (Edge edge: edges) {
                double distanceTo = distanceMap.get(edge.from) + edge.cost;
                if (distanceTo < distanceMap.get(edge.to)) {
                    distanceMap.put(edge.to, Double.NEGATIVE_INFINITY);
                }
            }
        }

        return distanceMap;
    }
}


public class BellmanFordForEdgeListGraph {
    public static void main(String[] args) {
        int vertices = 8;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(2, 4, 1));
        edges.add(new Edge(4, 3, -3));
        edges.add(new Edge(3, 2, 1));
        edges.add(new Edge(1, 5, 4));
        edges.add(new Edge(1, 6, 4));
        edges.add(new Edge(5, 6, 5));
        edges.add(new Edge(6, 7, 4));
        edges.add(new Edge(5, 7, 3));
        int source = 0;

        Map<Integer, Double> distanceMap = BellmanFord.bellmanFordSingleSourceShortestPath(edges, vertices, source);
        for (Entry<Integer, Double> entry: distanceMap.entrySet()) {
            System.out.println(source + "->" + entry.getKey() + ": " + entry.getValue());
        }
    }
}