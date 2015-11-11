public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
	
	
	     List<List<Integer>> list = new ArrayList<List<Integer>>();
	     	if(nums.length <4)
		    return list;
			Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i-1])
                continue;
 
            for (int j = i + 1; j <= nums.length - 2; j++) {              
                if (j != i + 1 && nums[j] == nums[j-1])
                    continue;
             
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    if (left != j + 1 && nums[left] == nums[left-1]) {
                        left++;
                        continue;
                    }
                    if (right != nums.length - 1 && nums[right] == nums[right+1]) {
                        right--;
                        continue;
                    }
                     
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        List<Integer> item = new ArrayList<Integer>();
                        item.add(nums[i]);
                        item.add(nums[j]);
                        item.add(nums[left]);
                        item.add(nums[right]);
 
                        list.add(item);
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                } // while
            } // for:j
        } // for: i
 
        return list;
    }
}
