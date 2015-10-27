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
        //������һ��ָ�룬ָ��ǰָ��ĵ���һ���ڵ�
        ListNode nextNode = node.next;
        
       // nextNode = node.next;
        while(nextNode !=null){
            node.val = nextNode.val;
            
            //Ȼ����nextNodeָ�����һ���Ƿ�Ϊ��
            //��Ϊ�գ���nextNode����Ϊ�գ�Ȼ���˳�ѭ��
            if(nextNode.next==null){
                node.next=null;
                break;
            }
            
            //Ȼ������ָ�붼����ƶ�һ����λ
            
            node = nextNode;
            nextNode = nextNode.next;
        }
    }
}