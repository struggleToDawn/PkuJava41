class MyStack {
    //用一个LinkedList对象模拟队列
    //top指向栈头
    private Queue<Integer> queue = new LinkedList<Integer>();
    private int top;
    
    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        top=x;
        
    }

    // Removes the element on top of the stack.
    //出栈主要思想试讲原来的队列倒转一下
    //然后出队列的那个值就相当于刚刚进的那个值
    //模拟了出栈
    public void pop() {
        Queue<Integer> newQueue = new LinkedList<Integer>();
        
        while(queue.size()>2){
            newQueue.add(queue.poll());
        }
        
        if(!queue.isEmpty()){
            top=queue.poll();
        }
        if(!queue.isEmpty()){
            newQueue.add(top);
        }
        
        queue = newQueue;
    }

    // Get the top element.
    public int top() {
        return top;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        if(queue.size()==0)
          return true;
        else
          return false;
    }
}