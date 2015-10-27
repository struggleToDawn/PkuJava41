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
    public int minDepth(TreeNode root) {
        
        if(root == null) return 0;
        
        int depth=1;
        int currentLevel = 1;
        int nextLevel = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            currentLevel--;
            if(node.left == null && node.right ==null){
                return depth;
            }
            
            if(node.left != null){
                queue.add(node.left);
                nextLevel++;
            }
            if(node.right !=null){
                queue.add(node.right);
                nextLevel++;
            }
            
            if(currentLevel ==0){
                if(nextLevel !=0){
                    depth++;
                }
                currentLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return depth;
    }
}
