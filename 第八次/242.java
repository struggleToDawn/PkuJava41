
public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] flag1 = new int[200];
        int[] flag2 = new int[200];
        int tmp=0;
        int length = t.length();
        for(int i=0; i< length; i++){
            tmp = (int)t.charAt(i);
            flag1[tmp]++;
            tmp = (int)s.charAt(i);
            flag2[tmp]++;
        }
        for(int i=0;i<200;i++){
            if(flag1[i]!=flag2[i]){
                return false;
            }
        }
   
            return true;
      
    }
}
