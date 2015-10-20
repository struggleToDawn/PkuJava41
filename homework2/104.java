/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        //���õݹ�ķ��������ĸ߶�
        if(root !=null){
            int lDeep = maxDepth(root.left);//�������ĸ߶�
            int rDeep = maxDepth(root.right);//�������ĸ߶�
            int maxDeep;
            if(lDeep > rDeep)
               maxDeep = lDeep;
            else
               maxDeep = rDeep;
        return maxDeep + 1;
        }
        else
          return 0;
    }
}