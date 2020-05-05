
public class Main {
    public static void main(String[] args) throws Exception {
        ResizableArrayStack stack = new ResizableArrayStack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class ResizableArrayStack {
    
    private Object[] arr;
    private int top;
    
    private class StackUnderflowException extends Exception {
        public StackUnderflowException(String message) {
            super(message);
        }
    }
    
    public ResizableArrayStack() {
        arr = new Object[1];
        top = 0;
    }
    
    private void resize(int size) {
        Object[] copy = new Object[size];
        for(int i = 0; i < top; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }
    
    public void push(Object object) {
        if(top == arr.length) resize(arr.length * 2);
        arr[top++] = object;
    }
    
    public Object pop() throws StackUnderflowException {
        if(top == 0) throw new StackUnderflowException("Stack Underflow: Cannot perform pop.");
        Object item = arr[--top];
        arr[top] = null;
        if(top == arr.length/4) resize(arr.length / 2);
        return item;
    }
    
    public boolean isEmpty() {
        return top == 0;
    }
    
    public int size() {
        return top;
    }
    
}
