/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null)
            return true;
            
        int size = getSize(head);
        //����һ�����ε�ջ
        Stack<Integer> stack = new Stack<Integer>();
        
        //�������ǰ�벿����ֵ��ջ
        for(int i=0;i<(size+1)/2;i++){
            stack.push(head.val);
            head = head.next;
        }
        //�������ĳ���Ϊ������ջͷԪ��ջ
        if(size%2==1)
           stack.pop();
        //���ν�����ĺ�����ջ
        while(head.next!=null){
            if(head.val!=stack.pop()){
                return false;
            }
            head= head.next;
        }
        
        return true;
        // ListNode fastPoint = head;
        // ListNode slowPoint = head;
        // while(fastPoint.next!=null&&fastPoint.next.next!=null){
        //     fastPoint = fastPoint.next.next;
        //     slowPoint = slowPoint.next;
        //     count++;
        // }
        
        // Stack<Integer> stack = new Stack<Integer>();
        // slowPoint = slowPoint.next;
        // //������ĺ�����ֵһ����ջ
        // while(slowPoint.next!=null){
        //     stack.push(slowPoint.val);
        //     slowPoint = slowPoint.next;
        // }
        // ListNode point = head.next;
        // while(point.next!=null||stack.empty()){
        //     if(point.val==stack.pop()){
        //         point=point.next;
        //     }
        //     else
        //       break;
        // }
        
        // if(stack.empty())
        //     return true;
        // else
        //     return false;
        
        
    }
    //��ȡ����ĳ���
    public int getSize(ListNode head){
        int size=0;
        while(head!=null){
            size++;
            head = head.next;
        }
        return size;
    }
}
  //�Զ���ջ�����ã��Ǿ�ֱ�ӵ��ú����������ջ
/*
//����һ��ջ��ʵ�ֽ�ջ�ͳ�ջ����
public class StackInt{
    private int[] elements;
    private int size;
    
    public StackInt(int capacity){
        elements = new int[capacity];
    }
    
    public void push(int value){
        element[size++]=value;
    }
    
    public int pop(){
        return elements[--size];
    }
    public boolean empty(){
        return size==0;
    }
}*/