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
    public TreeNode invertTree(TreeNode root) {
       
       
       
       
        if(root==null) return root;
        TreeNode p ;
        TreeNode q;
        Queue<TreeNode> queue =new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()){
            p = queue.poll();
            q = p.left;
            p.left = p.right;
            p.right =q;
            if(p.left!=null){
                queue.offer(p.left);
            }
            if(p.right!=null){
                queue.offer(p.right);
            }
            
        }
     return root;   
    }
}
