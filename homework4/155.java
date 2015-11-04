class MinStack {
    //private  final int DEF_length=16;
    //此方法主要的思想是用一个List类对象表示栈
    //一个list对象存储最小值的
    private List<Integer> stack = new ArrayList<Integer>();
    private List<Integer> min = new ArrayList<Integer>();
    private int top=stack.size()-1;
    private int top1=min.size()-1;
    private int minValue= 0;
    
    // public MinStack(){
    //     min.add(minValue);
    // }
    
   //进栈的时候就判断进栈的的值是否小于当前最小值栈的栈顶元素，小于则入栈
   //最小值栈中是每次入栈后的一次最小值的顺序排列
    public void push(int x) {
          if(min.size()==0){
              min.add(x);
              top1++;
          }
          else if(x<= min.get(top1)){
               min.add(x);
               top1++;
           }
           stack.add(x);
           top++;
    }

    //出栈的时候判断出栈的元素是否等于最小值栈的栈顶元素
    //如果相等，则将最小值栈元素也出栈
    public void pop() {
        
        if(top()==min.get(top1)){
            min.remove(top1);
            top1--;
        }
          stack.remove(top);
          top--;
    }

    public int top() {
        
        return stack.get(top);
    }

    public int getMin() {
        
        return min.get(top1);
    }
}