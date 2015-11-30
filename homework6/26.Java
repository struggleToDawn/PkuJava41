public class Solution {
    public int removeDuplicates(int[] nums) {
   /*   //通过一个set集合还过滤nums数组的元素
      
       Set<Integer> newnums = new HashSet<Integer>();
       
       for(int i=0;i<nums.length;i++){
           newnums.add(nums[i]);
       }
       
       return newnums.size();
    }  */
    
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