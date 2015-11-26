c class Solution {
    public int removeDuplicates(int[] nums) {
        Set<Integer> set=new HashSet<>();
        int[] nnums = nums;
        int j = 0;
        boolean flag ;
        for(int i : nums){
          flag =  set.add(i);
          if(flag){
              nnums[j++] = i;
          }
        }
        nums = nnums;
        return set.size();
    }
}
