public class Solution {
    public boolean isHappy(int n) {
     
    //  	int n = 19;
		int nums[] = new int[10];
		int intMap[] = new int[1000];
		intMap[0] =n;
		
		while(true){
			int j = 0;
			while(n>0) 
			{ 
			//	System.out.println(n);
			nums[j] = n%10 ; 
			n/=10; 
		//	System.out.println(nums[j]);
			j++;
			} 
			for(int m =0; m<j;m++){
			//	System.out.println(nums[m]);
				n += nums[m] * nums[m];	
			}
			if(n ==1) {
			    return true;
				// System.out.println(1);	
				// break;
			}
	    	int i = 0;
			for(int k =0; k < 1000; k++){
			    if(intMap[k] == n){
			        return false;
			    }
			  //  System.out.println(intMap[k]);
			    if(intMap[k] == 0){
			    	break;
			    }
			    i++;  
			}
			intMap[i] =n;
		}
    }
}
