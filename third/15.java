public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>(); 
		if(nums.length <3)
		    return list;
		
		Arrays.sort(nums);
	
		for(int i=0;i<nums.length-2;i++){
		    if(i!=0&&nums[i]==nums[i-1])
		        continue;
		  int  left = i+1;
		  int  right = nums.length -1;
		    
		    while(left <right){
		        
		        if( left > i+1 && nums[left] ==nums[left-1]){ // 不能写left>0  测试 ［0，0，0］
		            left++;
		         	continue;   
		        }
		    	
		    	if( right < nums.length-2 && nums[right] ==nums[right+1]){ // 必须写right判断
		    	 	right--;
		    	 	continue;   
		    	}
		    	
		     int   sum = nums[left] +nums[right] + nums[i];
		        if(sum ==0){
		           
		            List<Integer> item = new ArrayList<>();
		            item.add(nums[i]);
		            item.add(nums[left]);
		            item.add(nums[right]);
		            list.add(item);
		            
		           
		            right--;
		             left++;
		        }else if(sum >0){
		             right--;
		        }else {
		            left++;
		        }
		          
		         
		    }
		}
		
		return list;
    }
}
