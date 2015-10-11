public class Solution {
    public int removeElement(int[] nums, int val) {
        
		  int i = 0, j = 0, count=0;
     
            while (j < nums.length) {
                if (nums[j] != val) {
                    nums[i++] = nums[j];
                }else{
                    count++;
                }
                j++;
            }
    
         return nums.length-count;

    }
}
