public class Solution {
//������Լ�Ϊһ����������
    public void sortColors(int[] nums) {
        //��������
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