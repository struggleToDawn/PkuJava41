public class Solution {
    public int maxProfit(int[] prices) {
        int max =0;
        int sum =0;
        for(int i=0;i<prices.length -1;i++){
            sum += prices[i+1] -prices[i];
            if(sum>0){
                if(sum>max){
                    max = sum;
                }
            }else{
            sum=0;     
            }
           
        }
        return max;
    }
}
