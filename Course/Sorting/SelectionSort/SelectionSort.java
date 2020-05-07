
public class Main {
    public static void main(String[] args) {
        Integer[] a = {90, 30, 40, 20, 60, 80, 70, 10};
        SelectionSort.sort(a);
        for(int i: a) System.out.print(i + "-");
    }
}

class SelectionSort {
    
    private static boolean less(Comparable c1, Comparable c2) {
        return c1.compareTo(c2) < 0;
    }
    
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    // The method sorts in-place
    public static void sort(Comparable[] a) {
        int n = a.length;
        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = i + 1; j < n; j++)
                if(less(a[j], a[i])) min = j;
            exchange(a, i, min);
        }
    }
    
}
