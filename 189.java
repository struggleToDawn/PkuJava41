public class Solution {
    public void rotate(int[] nums, int k) {
        int[] temp = new int[nums.length];
        //System.arraycopy(nums,0,temp,0,nums.length);
        if(k>0){
        int k1=k%(nums.length);
        /*if(nums.length>1){*/
        //将数组后面往前移
        for(int i = 0;i<nums.length-k1;i++){
            temp[k1+i]=nums[i];
        }
        //将前面的往后移
        for(int i=0;i<k1;i++){
            temp[i]=nums[nums.length-k1+i];
        }
        for(int i=0;i<nums.length;i++){
            nums[i]=temp[i];
        }
        }
        /*
        }
        else
           nums=temp;*/
    }
}