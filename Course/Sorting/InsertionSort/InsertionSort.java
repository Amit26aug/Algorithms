
public class Main {
    public static void main(String[] args) {
        Integer[] a = {90, 30, 40, 20, 60, 80, 70, 10};
        InsertionSort.sortAnotherVersion(a);
        for(int i: a) System.out.print(i + "-");
    }
}

class InsertionSort {
    
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
        for(int i = 1; i < n; i++) {
            for(int j = i; j > 0; j--)
                if(less(a[j], a[j - 1])) exchange(a, j, j - 1);
                else break;
        }
    }
    
    public static void sortAnotherVersion(Comparable[] a) {
        int n = a.length;
        for(int i = 1; i < n; i++) {
            Comparable key = a[i];
            int j = i - 1;
            while(j >= 0 && less(key, a[j])) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
    
}
