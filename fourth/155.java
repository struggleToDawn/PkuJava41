class MinStack {
    
    Stack<Integer> minStack = new Stack<Integer>();
    Stack<Integer> Stack = new Stack<Integer>();
    public void push(int x) {
        Stack.push(x);
        if(minStack.empty() || x <= minStack.peek()){
            minStack.push(x);
        }
    }

    public void pop() {
        int tmp =  Stack.pop();
        if(tmp == minStack.peek()){
            minStack.pop();
        }
    }

    public int top() {
        return Stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

