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
        //运用递归的方法求树的高度
        if(root !=null){
            int lDeep = maxDepth(root.left);//左子树的高度
            int rDeep = maxDepth(root.right);//右子树的高度
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