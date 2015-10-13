public class Solution {
    public int removeElement(int[] nums, int val) {
        //StringBuilder stringBuilder = new StringBuilder(nums.length);
/*        List<Integer> list = new ArrayList<Integer>();
        
        for(int i = 0; i<nums.length;i++){
            if(nums[i]!=val)
               list.add(i);
        }
        
        int[] numbers = new int[list.size()];
        for(int i= 0;i < list.size();i++)
              numbers[i]=list.get(i);
        
        
        return numbers.length;*/
        
        //下面这种方法超时
/*         int keyLength = nums.length;
         for(int i = 0; i<keyLength;i++){
             if(nums[i]==val){
                 for(int j = i+1;j<keyLength;j++){
                     nums[j-1] = nums[j];
                     keyLength--;
                 }
                 i--;
             }
         }
         return keyLength;*/
        int count =0;
        int low=0;
        int high = nums.length-1;
        while(low <=high){
            if(nums[low]==val&&nums[high]!=val){
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high]=temp;
            low++;
            high--;    
            count ++;
            }
           else if(nums[low]==val&&nums[high]==val){
                high--;
                count++;
           }
           else{
               low++;
           }
        }
        
        return nums.length-count;
         
    }
}