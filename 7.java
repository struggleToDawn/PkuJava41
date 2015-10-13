public class Solution {
    public int reverse(int x) {
        int finalNum=0;
        if(x==0)
           return 0;
        int num =Math.abs(x);
        while(num > 0){
            int temp = num%10;
            num/=10;
            if(finalNum>Integer.MAX_VALUE/10){
                return 0;
            }
            else
               finalNum = finalNum*10+temp;
        }
        if(x>0)
             return finalNum;
        else
            return -finalNum;
    }
}