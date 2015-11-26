public class Solution {
    public boolean containsDuplicate(int[] nums) {
     Set<Integer> set =  new HashSet<Integer>();
     boolean flag;
     for(int i : nums){
         flag = set.add(i);
         if(!flag){
             return true;
         }
     }
     return false;
    }
}
