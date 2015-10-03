import java.util.ArrayList;
import java.util.List;

public class Solution {
	 public boolean isHappy(int n) {
		 boolean isHappyNum = false;
		 List<Integer> contained = new ArrayList<Integer>();
		 
		 int sum = computSum(n);
		 while(sum>0){
			 if(sum==1){
				 isHappyNum=true;
				 break;
			 }
			 if(contained.contains(sum)){
				 isHappyNum=false;
				 break;
			    }
			 else{
			      contained.add(sum);
			      sum=computSum(sum);
			    }
			
		 }
		 return isHappyNum;
		 
	 }
	 //计算所有位数字之和
	 public static int computSum(int num){
		 int sum=0;
		 while(num>0){
			 int numOfEach=num%10;
			 sum+=numOfEach*numOfEach;
			 num/=10;
		 }
		 return sum;
	 }
	 /*	       while(n>9){
	    	   computInt(n);
	       } 
	       if(n==1)
	    	   return true;
	       else 
			   return false;	  
	    }*/
}