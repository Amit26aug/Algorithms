package com.august.graphs.floydwarshall;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FloydWarshallAlgorithmSolver {


    // the memo that contains the All Pair Shortest Paths
    double[][] dp;
    // the matrix to reconstruct the shortest path
    int[][] next;
    // indicator that the algorithm is applied
    boolean solved;

    FloydWarshallAlgorithmSolver(double[][] adjMatrix) {
        // the number of vertices
        int n = adjMatrix.length;
        dp = new double[n][n];
        next = new int[n][n];

        // setup the next and dp with initial values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = adjMatrix[i][j];
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }
        solved = false;
    }

    public double[][] solveFloydWarshall() {
        int n = dp.length;

        // applying Floyd Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double dist = dp[i][k] + dp[k][j];
                    if (dist < dp[i][j]) {
                        dp[i][j] = dist;
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        // Until now, we have calculated the minimum distance for all pairs of nodes
        // However, we haven't addressed negative cycles yet.
        // to address negative cycles, we need to run through floyd warshall once more
        // and check if we are still getting a better path,
        // if we do that node has to be involved in a negative cycle
        propagateNegativeCycles();

        solved = true;
        return dp;
    }

    private void propagateNegativeCycles() {
        int n = dp.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double dist = dp[i][k] + dp[k][j];
                    if (dist < dp[i][j]) {
                        dp[i][j] = Double.NEGATIVE_INFINITY;
                        next[i][j] = -1;
                    }
                }
            }
        }
    }

    public List<Integer> reconstructPath(int source, int destination) {
        assert source >= 0 && source < next.length && destination >= 0 && destination < next.length;
        if (!solved) {
            solveFloydWarshall();
        }
        List<Integer> path = new ArrayList<>();
        for (int current = source; current != destination; current = next[current][destination]) {
            if (current == -1) {
                return null;
            }
            path.add(current);
        }
        return path;
    }

}


public class FloydWarshallAlgorithmAllPairShortestPath {

    static void printMatrix(double[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : mat) {
            for (double val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        //creating a graph represented by adjacency matrix
        int nodes = 5;
        double[][] adjMatrix = new double[nodes][nodes];

        // Set initial weights
        for (int i = 0; i < nodes; i++) {
            Arrays.fill(adjMatrix[i], Double.POSITIVE_INFINITY);
            adjMatrix[i][i] = 0;
        }
        // constructing graph
        adjMatrix[0][1] = 5;
        adjMatrix[0][4] = 8;
        adjMatrix[1][2] = 10;
        adjMatrix[1][4] = 3;
        adjMatrix[2][3] = -8;
        adjMatrix[2][4] = 6;
        adjMatrix[4][3] = 2;
        adjMatrix[4][1] = 2;

        FloydWarshallAlgorithmSolver floydWarshallAlgorithm = new FloydWarshallAlgorithmSolver(adjMatrix);

        double[][] allPairShortestPaths = floydWarshallAlgorithm.solveFloydWarshall();
        printMatrix(allPairShortestPaths);

        List<Integer> path = floydWarshallAlgorithm.reconstructPath(0, 3);
        if (path == null) {
            System.out.println("No path present");
        } else {
            System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining("->")));
        }
    }
}
