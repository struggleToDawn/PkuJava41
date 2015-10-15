<?php 

public class Solution {
    public void rotate(int[] nums, int k) {
           int n = nums.length;
		    k = k%n;   //获取右移相对位置
		   int nnum[] = new int[n];
		   for(int i=0; i<n ; i++){
		    nnum[i] = nums[i];   //创建另一同值数组
		   }
		   for(int j =0; j <n; j++){
			   nums[j] = nnum[(j+n-k)%n];  //向右移动k位
		   }
    }
}
?>
