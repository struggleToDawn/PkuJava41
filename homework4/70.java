public class Solution {
    public int climbStairs(int n) {
       
        //ì³²¨ÄÇÆõÊıÁĞ
        int[] results = new int[n+2];
        
          results[1]=1;
          results[2]=2;
        for(int i=3;i<=n;i++){
            results[i]=results[i-1]+results[i-2];
        }
        return results[n];
        
    }
}