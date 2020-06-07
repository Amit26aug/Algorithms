class Main {
    public static void main(String[] args) {
        int[] weights = {1, 3, 5, 6};
        int[] values = {4, 6, 3, 7};
        int capacity = 8;
        System.out.println(Knapsack.maxProfit(weights, values, capacity));
    }
}

class Knapsack {
    public static int maxProfit(int[] weights, int[] values, int capacity) {
        return recursiveKnapsack(weights, values, capacity, values.length);
    }
    
    private static int recursiveKnapsack(int[] weights, int[] values, int capacity, int current) {
        if (current == 0) return 0;
        
        if (weights[current - 1] > capacity) return recursiveKnapsack(weights, values, capacity, current - 1);
        
        int chosen = values[current - 1] + recursiveKnapsack(weights, values, capacity - weights[current - 1], current - 1);
        int notChosen = recursiveKnapsack(weights, values, capacity, current - 1);
        
        return Math.max(chosen, notChosen);
    }
}
