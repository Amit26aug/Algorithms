// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        bst.put("E", 5);
        bst.put("A", 1);
        bst.put("C", 3);
        bst.put("F", 6);
        bst.put("B", 2);
        bst.put("D", 4);
        
        bst.delete("A");
        
        for(String key: bst.keys()) System.out.print(key + "-");
        
        String min = bst.minimum();
        String max = bst.maximum();
        String floor = bst.floor("C");
        String ceil = bst.ceil("A");
        
        System.out.println("\nMinimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Floor of C: " + floor);
        System.out.println("Ceil of A: " + ceil);
        System.out.println("Size of BST: " + bst.size());
        System.out.println("Rank of D: " + bst.rank("D"));
        
    }
}

class BinarySearchTree<Key extends Comparable<Key>, Value> {
    
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int count;
        
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
    
    private Node root;
    
    public void put(Key key, Value value) {
        root = put(key, value, root);
    }
    
    private Node put(Key key, Value value, Node current) {
        if (current == null) return new Node(key, value);
        int cmp = key.compareTo(current.key);
        if (cmp < 0) current.left = put(key, value, current.left);
        else if (cmp > 0) current.right = put(key, value, current.right);
        else current.value = value;
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }
    
    private Value get(Key key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current.value;
        }
        return null;
    }
    
    public Iterable<Key> keys() {
        List<Key> keysList = new ArrayList<Key>();
        inorder(root, keysList);
        return keysList;
    }
    
    private void inorder(Node node, List<Key> keys) {
        if (node == null) return;
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }
    
    public Key minimum() {
        Node min = minimum(root);
        return min == null ? null : min.key;
    }
    
    private Node minimum(Node current) {
        if (current == null) return null;
        Node node = current.left;
        Node minimum = current;
        while (node != null) {
            minimum = node;
            node = node.left;
        }
        return minimum;
    }
    
    public Key maximum() {
        if (root == null) return null;
        Node current = root.right;
        Node maximum = root;
        while (current != null) {
            maximum = current;
            current = current.right;
        }
        return maximum.key;
    }
    
    public Key floor(Key key) {
        if (key == null) return null;
        Node f = floor(key, root);
        return f == null ? null : f.key;
    }
    
    private Node floor(Key key, Node current) {
        if (current == null) return null;
        int cmp = key.compareTo(current.key);
        if (cmp < 0) return floor(key, current.left);
        if (cmp == 0) return current;
        
        Node t = floor(key, current.right);
        if (t == null) return current;
        return t;
    }
    
    public Key ceil(Key key) {
        if (key == null) return null;
        Node c = ceil(key, root);
        return c == null ? null : c.key;
    }
    
    private Node ceil(Key key, Node current) {
        if (current == null) return null;
        int cmp = key.compareTo(current.key);
        if (cmp == 0) return current;
        if (cmp > 0) return ceil(key, current.right);
        
        Node t = ceil(key, current.left);
        if (t == null) return current;
        return t;
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }
    
    public int rank(Key key) {
        return rank(key, root);
    }
    
    private int rank(Key key, Node current) {
        if (current == null) return 0;
        int cmp = key.compareTo(current.key);
        if (cmp < 0) return rank(key, current.left);
        if (cmp > 0) return 1 + size(current.left) + rank(key, current.right);
        return size(current);
    }
    
    public void delete(Key key) {
        root = delete(key, root);
    }
    
    private Node delete(Key key, Node current) {
        if (current == null) return null;
        int cmp = key.compareTo(current.key);
        if (cmp < 0) current.left = delete(key, current.left);
        else if (cmp > 0) current.right = delete(key, current.right);
        else {
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            
            Node x = current;
            current = minimum(x.right);
            current.right = deleteMin(x.right);
            current.left = x.left;
        }
        current.count = 1 + size(current.left) + size(current.right);
        return current;
    }
    
    private Node deleteMin(Node current) {
        if (current == null) return null;
        if (current.left == null) return current.right;
        current = deleteMin(current.left);
        return current;
    }
    
}

