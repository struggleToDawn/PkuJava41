public class Solution {
    public void moveZeroes(int[] nums) {
         int start=0;
         int end=nums.length-1;
         //int target =1;
        
         while(start<end){
             if(nums[start]==0&&nums[end]!=0){
                  for(int i=start;i<nums.length-1;i++){
                      nums[i]=nums[i+1];
                  }
                  nums[end]=0;
                  //start++;
                  end--;
             }
            
             if(nums[start]==0&&nums[end]==0){
                 end--;
             }
            
             if(nums[start]!=0&&nums[end]!=0){
                 start++;
                 //target++;
             }
             
             if(nums[start]!=0&&nums[end]==0){
                 start++;
                 end--;
             }
            
         }
        
    }
}