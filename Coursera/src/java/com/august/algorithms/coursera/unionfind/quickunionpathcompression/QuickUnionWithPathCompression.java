package com.august.algorithms.coursera.unionfind.quickunionpathcompression;

public class QuickUnionWithPathCompression {
    public static void main(String[] args) {
        
        long startTime = System.currentTimeMillis();
        
        WeightedQuickUnion wqu = new WeightedQuickUnion(8);
        wqu.union(1, 2);
        wqu.union(2, 3);
        System.out.println(wqu.find(1, 3));
        System.out.println(wqu.find(0, 1));
        wqu.union(0, 3);
        System.out.println(wqu.find(0, 1));
        
        long endTime = System.currentTimeMillis();
        
        System.out.println((endTime - startTime) + "ms");
        
    }
}

class WeightedQuickUnion {
    
    private int[] id;
    private int[] size;
    
    public WeightedQuickUnion(int n) {
        id = new int[n];
        size = new int[n];
        
        for(int i =0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }
    
    private int root(int i) {
        while(i != id[i]) {
            // Make every node point to it's grandparent.
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public void union(int ob1, int ob2) {
        int root1 = root(ob1);
        int root2 = root(ob2);
        
        if(size[root1] < size[root2]) {
            id[root1] = root2;
            size[root2] += size[root1];
        } else {
            id[root2] = root1;
            size[root1] += size[root2];
        }
    }
    
    public boolean find(int ob1, int ob2) {
        return root(ob1) == root(ob2);
    }
    
}
