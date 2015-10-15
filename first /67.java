public class Solution {
    public String addBinary(String a, String b) {
        int lengthOfa = a.length();
        int lengthOfb = b.length();
      //  String endSring = lengthOfa > lengthOfb ? lengthOfa : lengthOfb;
        String endString = "";
         if(lengthOfa>lengthOfb){
	        	for(int i=0 ;i< lengthOfa -lengthOfb;i++){
	        		b = "0"+b;
	        	}
	        }else{
	        	for(int i=0 ;i< lengthOfb -lengthOfa;i++){
	        		a = "0"+a;
	        	}    	
	        }
        // int end = lengthOfa < lengthOfb ? lengthOfa : lengthOfb;
        int end = lengthOfa > lengthOfb ? lengthOfa : lengthOfb;
        // int flag[end+1];
        // int flag[] = new int[end+1];
        int flag =0;
        int tmp;
        for(int i =end-1; i >= 0 ; i--){
            int numA = Byte.parseByte( a.charAt(i)+"" );
            int numB = Byte.parseByte( b.charAt(i)+"" );
       //     tmp = numA +numB +flag[i];
            tmp = numA +numB +flag;
            if(tmp >=2){
                tmp = tmp -2;
                flag =1;
            }else{
                flag =0;
            }
             String cc = String.valueOf(tmp);
	     //    System.out.println(cc);
	         endString = cc + endString;
	    //     System.out.println(endString);
            // String cc = String.valueOf(tmp);
            // endString.charAt(i) = cc.charAt(0);
        }
        if( flag ==1){
            endString = "1" + endString;
        }
        return endString;
    }
}
