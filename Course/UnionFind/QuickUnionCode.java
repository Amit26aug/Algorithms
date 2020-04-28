/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

class QuickFind {
    
    private int[] id;
    
    public QuickFind(int n) {
        id = new int[n];
        for(int i = 0; i < n; ++i) {
            id[i] = i;
        }
    }
    
    public void union(int obj1, int obj2) {
        int ob1Id = id[obj1];
        int ob2Id = id[obj2];
        
        for(int i = 0; i < id.length; ++i) {
            if(id[i] == ob1Id) id[i] = ob2Id;
        }
    }
    
    public boolean find(int ob1, int ob2) {
        return id[ob1] == id[ob2];
    }
}

class QuickFindCode {
	public static void main (String[] args) throws java.lang.Exception {
	    
	    long startTime = System.currentTimeMillis();
	    
	    Scanner sc = new Scanner(System.in);
	    int n = Integer.parseInt(sc.nextLine());
	    
	    QuickFind qu = new QuickFind(n);
	    
	    while(sc.hasNext()) {
            // input is of form op obj1 obj2
            // op can be 0 for union or 1 for find
            // for op 0, perform union of ob1 and ob2
            // for op 1, perform find of ob1 and ob2
            
            String[] input = sc.nextLine().trim().split(" ");
            int op = Integer.parseInt(input[0]);
            int obj1 = Integer.parseInt(input[1]);
            int obj2 = Integer.parseInt(input[2]);
            
            if(op == 0) {
                qu.union(obj1, obj2);
                System.out.println("Union: " + obj1 + " and " + obj2);
            }
            else if(op == 1) {
                boolean connected = qu.find(obj1, obj2);
                System.out.println("Connected: " + obj1 + " and " + obj2 + "-> " + connected);
            } 
            else {
                throw new IllegalArgumentException();
            }
	    }
	    
	    long endTime = System.currentTimeMillis();
	    
	    System.out.println("Time: " + (endTime - startTime) + "ms");
		
	}
}
