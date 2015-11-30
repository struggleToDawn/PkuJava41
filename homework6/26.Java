public class Solution {
    public int removeDuplicates(int[] nums) {
        
     int repeat = 0;
     for(int i = 1;i<nums.length;i++){
         if(nums[i]==nums[i-1]){
             repeat++;
         }
         nums[i-repeat]=nums[i];
     }
      return nums.length-repeat;
 
    }
}