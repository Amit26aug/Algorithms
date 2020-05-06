// "static void main" must be defined in a public class.
public class Main {
    public static void main(String[] args) {
        StackWithMax<Integer> stack = new StackWithMax<>();
        stack.push(10);
        stack.push(30);
        stack.push(30);
        System.out.println(stack.getMax());
        System.out.println(stack.pop());
        System.out.println(stack.getMax());
    }
}

class StackWithMax<Item extends Comparable<Item>> {
    private class Node {
        Item item;
        Node next;
    }
    
    private Node top;
    private Node maxTop;
    
    public void push(Item item) {
        Node oldTop = top;
        top = new Node();
        top.item = item;
        top.next = oldTop;
        
        Node oldMaxTop = maxTop;
        maxTop = new Node();
        maxTop.item = oldMaxTop == null || oldMaxTop.item.compareTo(item) < 0 ? item : oldMaxTop.item;
        maxTop.next = oldMaxTop;
    }
    
    public Item pop() {
        Node oldTop = top;
        top = top.next;
        
        maxTop = maxTop.next;
        
        return oldTop.item;
    }
    
    public Item peek() {
        return top.item;
    }
    
    public Item getMax() {
        return maxTop.item;
    }
    
}
