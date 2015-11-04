class MyQueue {
    //主要思想是用两个栈来模拟队列
    
    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> newStack = new Stack<Integer>();
    private int top;
    // Push element x to the back of queue.
    public void push(int x) {
        if(stack.empty())
           top = x;
        stack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        while(!stack.empty()){
            newStack.push(stack.pop());
        }
        int temp = newStack.pop();
        
        if(!newStack.empty()){
        top=newStack.peek();
        }
        else
          top=0;
        
        while(!newStack.empty()){
            stack.push(newStack.pop());
        }
    }

    // Get the front element.
    public int peek() {
        return top;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        if(stack.empty())
            return true;
        else
           return false;
    }
}