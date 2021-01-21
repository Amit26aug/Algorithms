package com.august.algorithms.coursera.queue.resizable;

import java.util.Iterator;

// "static void main" must be defined in a public class.
public class ResizableQueueImpl {
    public static void main(String[] args) throws Exception {
        ResizableQueue<Integer> queue = new ResizableQueue<>();
        queue.enqueue(10);
        System.out.println(queue.dequeue());
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.dequeue());
        
        for(Integer i: queue) System.out.print(i + "->");
    }
}

class ResizableQueue<Item> implements Iterable<Item> {
    
    private Item[] arr;
    private int first, last;
    
    public ResizableQueue() {
        arr = (Item[]) new Object[1];
        first = last = 0;
    }
    
    private void resize(int size) {
        Item[] copy = (Item[]) new Object[size];
        for(int i = first, j = 0; i != last; i = (i + 1) % arr.length, j++) {
            copy[j] = arr[i];
        }
        arr = copy;
        int sizeOfQueue = getQueueSize();
        first = 0;
        last = sizeOfQueue;
    }
    
    private int getQueueSize() {
        if (first <= last) return last - first;
        else return last + arr.length - first;
    }
    
    public void enqueue(Item item) {
        if(first == (last + 1) % arr.length) resize(arr.length * 2);
        arr[last] = item;
        last = (last + 1) % arr.length;
        // if (first == last) resize(arr.length * 2);
        // else last = (last + 1) % arr.length;
        // System.out.println(arr.length + ":" + first + ":" + last);
    }
    
    public Item dequeue() throws Exception {
        if (first == last) throw new Exception("No more elements to dequeue.");
        Item item = arr[first];
        arr[first] = null;
        first = (first + 1) % arr.length;
        if (getQueueSize() == arr.length / 4) resize(arr.length / 2);
        // else first = (first + 1) % arr.length;
        // System.out.println(arr.length + ":" + first + ":" + last);
        return item;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int i = first;
            
            @Override
            public boolean hasNext() {
                return i != last;
            }
            
            @Override
            public void remove() {
                return;
            }
            
            @Override
            public Item next() {
                Item item = arr[i];
                i = (i + 1) % arr.length;
                return item;
            }
        };
    }
    
}
