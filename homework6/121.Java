public class Solution {
    public int maxProfit(int[] prices) {
        //此方法是对题目理解错误
/*        if(prices.length==0)
           return 0;
        Arrays.sort(prices);
        return prices[prices.length-1]-prices[0];*/
      int sum=0;
      int maxProfit=0;
      
      for(int i=1;i<prices.length;i++){
          sum+=prices[i]-prices[i-1];
          
          if(sum<0)
               sum=0;
               
          if(sum>maxProfit)
               maxProfit=sum;
      }
      
      return maxProfit;
    }
}