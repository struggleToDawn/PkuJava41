class MinStack {
    //private  final int DEF_length=16;
    //�˷�����Ҫ��˼������һ��List������ʾջ
    //һ��list����洢��Сֵ��
    private List<Integer> stack = new ArrayList<Integer>();
    private List<Integer> min = new ArrayList<Integer>();
    private int top=stack.size()-1;
    private int top1=min.size()-1;
    private int minValue= 0;
    
    // public MinStack(){
    //     min.add(minValue);
    // }
    
   //��ջ��ʱ����жϽ�ջ�ĵ�ֵ�Ƿ�С�ڵ�ǰ��Сֵջ��ջ��Ԫ�أ�С������ջ
   //��Сֵջ����ÿ����ջ���һ����Сֵ��˳������
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

    //��ջ��ʱ���жϳ�ջ��Ԫ���Ƿ������Сֵջ��ջ��Ԫ��
    //�����ȣ�����СֵջԪ��Ҳ��ջ
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