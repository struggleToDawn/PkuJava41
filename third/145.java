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
    public List<Integer> postorderTraversal(TreeNode root) {
     
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if(root == null ) return result;
        while(root !=null){
            stack.push(root);
            root = root.left;
        }
        
        TreeNode last = null;
        
        while(!stack.empty()){
            root = stack.pop();
            if(root.right == null || root.right == last){
                result.add(root.val);
                last = root;
            }else{
                stack.push(root);
                root = root.right;
                while(root !=null){
                    stack.push(root);
                    root = root.left;
                }
            }
            
        }
        
        return result;
    }
}
