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
         ListNode slow=head; ListNode fast=head;
         if(fast==null||fast.next==null)//0个节点或是1个节点
        return true;


    while(fast.next!=null&&fast.next.next!=null)
    {
        fast=fast.next.next;
        slow=slow.next;
    }
    //对链表后半段进行反转
    ListNode midNode=slow;
    ListNode firNode=slow.next;//后半段链表的第一个节点
    ListNode cur=firNode.next;//插入节点从第一个节点后面一个开始
    firNode.next=null;//第一个节点最后会变最后一个节点
    while(cur!=null)
    {
        ListNode nextNode=cur.next;//保存下次遍历的节点
        cur.next=midNode.next;
        midNode.next=cur;
        cur=nextNode;
    }

    //反转之后对前后半段进行比较
    slow=head;
    fast=midNode.next;
    while(fast!=null)
    {
        if(fast.val!=slow.val)
            return false;
        slow=slow.next;
        fast=fast.next;
    }
    return true;
   

    }
}
