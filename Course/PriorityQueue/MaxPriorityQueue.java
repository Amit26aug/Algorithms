
public class Main {
    public static void main(String[] args) {
        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>();
        pq.insert(10);
        pq.insert(30);
        pq.insert(50);
        pq.insert(40);
        pq.insert(20);
        System.out.println(pq.deleteMax());
        System.out.println(pq.deleteMax());
        System.out.println(pq.getMax());
        System.out.println(pq.deleteMax());
        System.out.println(pq.deleteMax());
        System.out.println(pq.deleteMax());
        System.out.println(pq.deleteMax());
    }
}

class MaxPriorityQueue<Key extends Comparable<Key>> {
    
    private Comparable[] a;
    private int n;
    
    private static final int INITIAL_SIZE = 4;
    
    public MaxPriorityQueue() {
        a = new Comparable[INITIAL_SIZE];
        n = 0;
    }
    
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private void resize(int size) {
        assert size > n;
        if(size < 2) return;
        Comparable[] copy = new Comparable[size];
        for(int i = 1; i <= n; i++) copy[i] = a[i];
        a = copy;
    }
    
    private void swim(int k) {
        int j = k / 2;
        while(k > 1 && less(a[j], a[k])) {
            exchange(a, k, j);
            k = j;
            j = k / 2;
        }
    }
    
    private void sink(int k) {
        while(2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a[j], a[j + 1])) j++;
            if(!less(a[k], a[j])) break;
            exchange(a, k, j);
            k = j;
        }
    }
    
    private void print() {
        for(Comparable c: a) System.out.print(c + "-");
    }
    
    public void insert(Key key) {
        if (n == a.length - 1) resize(2 * a.length);
        a[++n] = key;
        swim(n);
        // print();
    }
    
    public Key deleteMax() {
        if (n == 0) return null;
        Key key = (Key) a[1];
        exchange(a, 1, n);
        a[n--] = null;
        sink(1);
        if (n == a.length / 4) resize(a.length / 2);
        // print();
        return key;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public Key getMax() {
        return n == 0 ? null : (Key) a[1];
    }
    
    public int size() {
        return n;
    }
    
}
