/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        //再设置一个指针，指向当前指针的的下一个节点
        ListNode nextNode = node.next;
        
       // nextNode = node.next;
        while(nextNode !=null){
            node.val = nextNode.val;
            
            //然后判nextNode指针的下一个是否为空
            //若为空，则将nextNode设置为空，然后退出循环
            if(nextNode.next==null){
                node.next=null;
                break;
            }
            
            //然后将两个指针都向后移动一个单位
            
            node = nextNode;
            nextNode = nextNode.next;
        }
    }
}