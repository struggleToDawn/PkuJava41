public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //借鉴3sum的思想使用两个指针来解决问题
        //分别是头尾指针start 和 end
         ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();       
    Arrays.sort(nums);
    for (int j = 0; j < nums.length; j++) {
        for (int i= j + 1; i < nums.length; i++) {
            int start = i + 1, end = nums.length-1;
            while (start < end) {//Two pointers
                int sum = nums[j] + nums[i] + nums[start] + nums[end];

                if (sum == target) {     
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(nums[j]);                      
                    list.add(nums[i]);
                    list.add(nums[start]);
                    list.add(nums[end]);    
                    result.add(list);

                    start++;
                    end--;
                    while((start < end) && nums[start] == nums[start-1]) start++;     
                    while((start < end) && nums[end] == nums[end+1]) end--;
                }
                else if (sum < target) {
                    start++;       
                    while((start < end) && nums[start] == nums[start-1]) start++;   
                } else {            
                    end--;
                    while((start < end) && nums[end] == nums[end+1]) end--;                       
                }      
            }

            while (i+1 < nums.length && nums[i+1] == nums[i])                        
                i++;            
        }

        while (j+1 < nums.length && nums[j+1] == nums[j])                        
            j++;    
    }

    return result;
    }
}