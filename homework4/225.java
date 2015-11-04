class MyStack {
    //��һ��LinkedList����ģ�����
    //topָ��ջͷ
    private Queue<Integer> queue = new LinkedList<Integer>();
    private int top;
    
    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        top=x;
        
    }

    // Removes the element on top of the stack.
    //��ջ��Ҫ˼���Խ�ԭ���Ķ��е�תһ��
    //Ȼ������е��Ǹ�ֵ���൱�ڸոս����Ǹ�ֵ
    //ģ���˳�ջ
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