// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        Integer[] a = {80, 60, 90, 30, 50, 40, 70, 10, 20};
        HeapSort.sort(a);
        for(int i: a) System.out.print(i + "-");
    }
}

class HeapSort {
    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static void sink(Comparable[] a, int n, int k) {
        while(2 * k + 1 <= n) {
            int j = 2 * k + 1;
            if (j < n && less(a[j], a[j + 1])) j++;
            if(!less(a[k], a[j])) break;
            exchange(a, k, j);
            k = j;
        }
    }
    
    private static void print(Comparable[] a) {
        for(Comparable i: a) System.out.print(i + "-");
        System.out.println();
    }
    
    private static void heapify(Comparable[] a) {
        int n = a.length - 1;
        for(int i = (n - 1) / 2; i >= 0; i--) sink(a, n, i);
    }
    
    public static void sort(Comparable[] a) {
        heapify(a);
        // print(a);
        int n = a.length - 1;
        while(n > 0) {
            exchange(a, n, 0);
            sink(a, --n, 0);
        }
    }
        
}
