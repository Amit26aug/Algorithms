
public class Main {
    public static void main(String[] args) {
        Integer[] a = {90, 30, 40, 20, 60, 80, 70, 10};
        ShellSort.sort(a);
        for(int i: a) System.out.print(i + "-");
    }
}

class ShellSort {
    
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
        int h = 1;
        
        while(h < n/3) h = 3 * h + 1;
        while(h >= 1) {
            for(int i = h; i < n; i++) {
                for(int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exchange(a, j, j - h);
            }
            h /= 3;
        }
    }
    
}
