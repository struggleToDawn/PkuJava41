/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        if(head ==null || head.next == null) return false;
        ListNode p = head;
        ListNode q = head;
        while(q!=null && q!=null){
            q = q.next;
            p = p.next;
            if(p == null) return false;
            p = p.next ;
            if(p == null) return false;
            if(p ==q) return true;
        }
        return false;
    }
}
