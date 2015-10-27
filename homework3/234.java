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
        //声明一个整形的栈
        Stack<Integer> stack = new Stack<Integer>();
        
        //将链表的前半部分数值入栈
        for(int i=0;i<(size+1)/2;i++){
            stack.push(head.val);
            head = head.next;
        }
        //如果链表的长度为奇数则将栈头元出栈
        if(size%2==1)
           stack.pop();
        //依次将链表的后半段与栈
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
        // //将链表的后半段数值一次入栈
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
    //获取链表的长度
    public int getSize(ListNode head){
        int size=0;
        while(head!=null){
            size++;
            head = head.next;
        }
        return size;
    }
}
  //自定义栈不能用，那就直接调用函数库里面的栈
/*
//定义一个栈，实现进栈和出栈方法
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