/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
/*        if(head==null)
           return head;
        if(head.next==null)
           return head;
           
        ListNode pointer1 = head;
        ListNode pointer2 = head.next;
        while(pointer1.next!=null){
            while(pointer1.val==pointer2.val){
                if(pointer2.next!=null)
                    pointer2=pointer2.next;
                else
                    pointer2=null;
                   break;
            }
            if(pointer2==null){
                pointer1.next=pointer2;
            }
            else{
            pointer1.next=pointer2;
            pointer1=pointer2;
            pointer2=pointer2.next;
            }
        }*/
        ListNode pointer = head;
        while(pointer != null) {
            if(pointer.next != null && pointer.next.val == pointer.val)
                pointer.next = pointer.next.next;
            else
                pointer = pointer.next;
        }
        return head;
    }
}