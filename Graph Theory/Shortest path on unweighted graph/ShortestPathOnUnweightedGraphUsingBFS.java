import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/*
* This class defines the methods to find the shortest path in a directed unweighted graph.
*/
class ShortestPathUtil {
    static String findShortestPath(Map<Integer, List<Integer>> adjacencyList, int start, int end) {
        int[] pathParent = breadthFirstSearch(adjacencyList, start, end);
        return constructPath(pathParent, start, end);
    }

    private static int[] breadthFirstSearch(Map<Integer, List<Integer>> adjacencyList, int start, int end) {
        int[] pathParent = new int[adjacencyList.size()];
        Arrays.fill(pathParent, -1);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (queue.size() > 0) {
            int current = queue.poll();
            for (Integer neighbor: adjacencyList.get(current)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                pathParent[neighbor] = current;
                queue.add(neighbor);
                visited.add(neighbor);
                if (neighbor == end) {
                    return pathParent;
                }
            }
        }

        return pathParent;
    }

    private static String constructPath(int[] pathParent, int start, int end) {
        List<Integer> path = new ArrayList<>();
        while (end != -1) {
            path.add(end);
            if (end == start) {
                Collections.reverse(path);
                return path.stream().map(s -> String.valueOf(s)).collect(Collectors.joining("->"));
            }
            end = pathParent[end];
        }
        return null;
    }
}

class ShortestPathOnUnweightedGraphUsingBFS {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        adjacencyList.put(0, Arrays.asList(1, 4));
        adjacencyList.put(1, Arrays.asList(2));
        adjacencyList.put(2, Arrays.asList(3));
        adjacencyList.put(3, Arrays.asList());
        adjacencyList.put(4, Arrays.asList(1, 2));

        System.out.println(ShortestPathUtil.findShortestPath(adjacencyList, 4, 3));
    }
    
}