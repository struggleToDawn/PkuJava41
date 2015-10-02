public class Solution {
    public void sortColors(int[] nums) {
        	
		int numOne = 0;  //纪录数据中值为0的个数
		int numTwo = 0;  //纪录数据中值为1的个数
		int numThree = 0; //纪录数据中值为2的个数
		for(int i=0; i<nums.length; i++){
			if(nums[i] ==0){
				numOne++;
			}else if(nums[i] ==1){
				numTwo++;
			}else if(nums[i] ==2){
				numThree++;
			}
		}
	
		for(int i=0;i<numOne;i++){
			nums[i] = 0;
		}
		
		for(int i=0; i<numTwo;i++){
			nums[i+numOne]=1;
		}
		for(int i=0; i<numThree;i++){
			nums[i+numOne +numTwo] = 2;
		}
	
    }
}
