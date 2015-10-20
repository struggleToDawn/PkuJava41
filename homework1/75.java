public class Solution {
//此题可以简化为一个排序问题
    public void sortColors(int[] nums) {
        //插入排序
        for(int i=1;i<nums.length;i++){
            int currentElement = nums[i];
            int k;
            for(k=i-1;k>=0&&nums[k]>currentElement;k--){
                nums[k+1]=nums[k];
            }
            nums[k+1] = currentElement;
            
        }
        
    }
}