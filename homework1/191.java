public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {

/*        String binaryStr = "";
        int sum=0;
        while(n!=0){
            if(n%2==1)
              binaryStr +='1';
             else
              binaryStr +='0';
            n=n/2;
        }
        
        for(int i = 0;i < binaryStr.length();i++){
            if(binaryStr.charAt(i)=='1')
              sum++;
        }
        return sum;
   */     

        String binaryStr =Integer.toBinaryString(n);
       int sum=0;
       for(int i=0;i<binaryStr.length();i++){
           if(binaryStr.charAt(i)=='1')
             sum++;
       }
       
        return sum;
        
    }
}