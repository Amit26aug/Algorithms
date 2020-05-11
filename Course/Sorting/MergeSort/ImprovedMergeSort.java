// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        
        Integer[] a = {90, 30, 50, 40, 20, 60, 10, 80};
        MergeSort.sort(a);
        for(int i: a) System.out.print(i + "-");
        
    }
}

class MergeSort {
    
    public static final int CUTOFF = 7;
    
    private static boolean isSorted(Comparable[] a, int start, int end) {
        for(int i = start; i < end; i++) if (a[i + 1].compareTo(a[i]) < 0) return false;
        return true;
    }
    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void merge(Comparable[] a, Comparable[] aux, int left, int mid, int right) {
        assert isSorted(a, left, mid);
        assert isSorted(a, mid + 1, right);
        
        // No need of copying to aux
        // for(int i = left; i <= right; i++) aux[i] = a[i];
        
        int i = left, j = mid + 1;
        for(int k = left; k <= right; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > right) aux[k] = a[i++];
            else if (less(a[i], a[j])) aux[k] = a[i++];
            else aux[k] = a[j++];
        }
        
        assert isSorted(aux, left, right);
    }
    
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        for(int i = 0; i < a.length; i++) aux[i] = a[i];
        sort(a, aux, 0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int left, int right) {
        if (left + CUTOFF - 1 >= right) {
            InsertionSort.sort(a, left, right);
            return;
        }
        int mid = (left + right) / 2;
        sort(aux, a, left, mid);
        sort(aux, a, mid + 1, right);
        if(!less(aux[mid + 1], aux[mid])) return;
        merge(aux, a, left, mid, right);
    }
}
