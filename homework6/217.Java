public class Solution {
    public boolean containsDuplicate(int[] nums) {
      if(nums.length==0||nums.length==1)
           return false;
           /*
        Set<Integer> setNums = new HashSet<Integer>();
        
        for(int i=0;i<nums.length;i++){
            setNums.add(nums[i]);
        }
        
        if(setNums.size()!=nums.length)
          return true;
        else
          return false;*/
     Arrays.sort(nums);
     for(int i=0;i<nums.length-1;i++)
     {
         if(nums[i]==nums[i+1]) 
           return true;

     }
     return false;
  }
}