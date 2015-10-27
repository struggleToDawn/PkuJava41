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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        TreeNode p = root;

        while(!stack.empty() || p != null){
 
            if(p != null){
                
                result.add(p.val);
                stack.push(p);
                p = p.left;

            }else{
                TreeNode t = stack.pop();
                p = t.right;
            }
        }
        
        return result;
    }
}
