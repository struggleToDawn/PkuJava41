public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //������Ŀ�ĵ�һ�뷨�������nums��Ԫ������ֻ��
        //������������������ӣ��ܷ��0
        //���Ƚ����������ܹ��Ƚ������ж�,�����Ŷ���ʱ��
        //Ȼ���ǵ�Array��ϵͳ�����򷽷�Ҳ��ʱ��
        //Ȼ��ֱ�����ͷβָ�룬�Ϳ��ԴﵽĿ��
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
       if(nums.length<3){
            return result;
        }
        
        Arrays.sort(nums);//j����ѡ������

    int len = nums.length;
    for(int i = 0; i < len; i++) {
        if(i > 0 && nums[i] == nums[i - 1]) continue;       
        int target = 0 - nums[i];
        int j = i + 1, k = len - 1;
        while(j < k) {
            if(nums[j] + nums[k] == target) {
                result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                while(j < k && nums[j] == nums[j + 1]) j++; 
                while(j < k && nums[k] == nums[k - 1]) k--; 
                j ++; k--;
            } else if(nums[j] + nums[k] < target) {
                j ++;
            } else {
                k --;
            }
        }
/*        for(int i = 0; i<nums.length;i++){
            //����ͷβָ��
            int startIndex = i+1;
            int endIndex = nums.length -1;
            while(startIndex < endIndex){
                if(nums[startIndex] + nums[endIndex] < nums[i]*(-1)){
                    startIndex++;
                }else if(nums[startIndex] + nums[endIndex] > nums[i]*(-1)){
                    endIndex--;
                }else{
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[startIndex]);
                    list.add(nums[endIndex]);
                    //�����listResult�в�����list����list��ӵ�����
                    if(!list.contains(list)){
                        listResult.add(list);
                    }
                }
            }
           
        }*/
    }
         return result;
        
    }
}
    
/*    //��ѡ������ʱ
    public void selectSort(int[] nums){
        for(int i=0;i<nums.length;i++){
            int currentMin= nums[i];
            int currentMinIndex = i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<currentMin){
                    currentMin = nums[j];
                    currentMinIndex = j;
                }
            }
            if(currentMinIndex !=i){
                nums[currentMinIndex] = nums[i];
                nums[i]=currentMin;
            }
        }
    }*/
    
/*    public void selectSort(int[] nums){
        for(int i=1;i<nums.length;i++){
            int currentValue = nums[i];
            int j;
            for(j=i-1;j>-1&&nums[j]>currentValue;j--){
                nums[j+1]= nums[j];
            }
            
            nums[j+1]=currentValue;
        }
    }*/
    
    // public void selectSort(int[] nums){
    //     int pivot = nums[0];
    //     int start=0;
    //     int end=nums.length -1;
        
    //     while(start <end){
    //         while(start<end&& pivot<nums[end]){
    //             end--;
    //         }
            
    //         if(start<end){
    //             nums[start]= nums[end];
    //             start++;
    //         }
            
    //         while(start<end&&pivot>nums[start]){
    //             start++;
    //         }
    //         }
            
    //         if(start<end){
    //             nums[end]=nums[start];
    //             end--;
    //     }
    //     nums[start]=pivot;
//     }
// }