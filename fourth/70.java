public class Solution {
    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        
        int a =1;
        int b =2;
        int sum = 0;
        
        for(int i =2; i< n; i++){
            sum = a +b;
            a = b;
            b = sum;
        }
           return sum;
    }
}
