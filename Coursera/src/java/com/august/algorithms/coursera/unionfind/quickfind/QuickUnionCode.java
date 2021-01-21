package com.august.algorithms.coursera.unionfind.quickfind;

// "static void main" must be defined in a public class.
public class QuickUnionCode {
    public static void main(String[] args) {
        
        long startTime = System.currentTimeMillis();
        
        QuickUnion qu = new QuickUnion(8);
        qu.union(1, 5);
        qu.union(2, 5);
        System.out.println(qu.find(1, 5));
        qu.union(1, 6);
        qu.union(6, 7);
        System.out.println(qu.find(5, 7));
        qu.union(3, 4);
        System.out.println(qu.find(0, 3));
        
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime) + "ms");
        
    }
}

class QuickUnion {
    
    private int[] id;
    
    public QuickUnion(int n) {
        id = new int[n];
        for(int i = 0; i < n; i++) id[i] = i;
    }
    
    private int root(int i) {
        while(i != id[i]) i = id[i];
        return i;
    }
    
    public void union(int ob1, int ob2) {
        int root1 = root(ob1);
        int root2 = root(ob2);
        
        id[root1] = root2;
    }
    
    public boolean find(int ob1, int ob2) {
        return root(ob1) == root(ob2);
    }
    
}
