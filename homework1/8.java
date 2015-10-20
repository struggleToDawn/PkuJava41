public class Solution {
 public int myAtoi(String str) {
        String finalStr = "";
        long t;
        int numOfFu=0;
        int sign =1;
        boolean signer =true;
        str=str.trim();
        if(str.equals(null))
             return 0;
        for(int i=0;i<str.length()&&signer;i++){
            signer =false;
            char ch= str.charAt(i);
            //Character character = new Character(ch);
            if(ch=='+') {
                sign =1;
                numOfFu++;
                signer=true;
            }
            else if(ch=='-'){
                sign =-1;
                numOfFu++;
                signer=true;
            } 
            else if(Character.isDigit(ch)){
                finalStr +=ch;
                signer =true;
             }
            else signer=false;
            
            if(finalStr!=""){
            t=Long.parseLong(finalStr);
            if(t>Integer.MAX_VALUE) {
            	t=Integer.MAX_VALUE;
            	break;
              }
             }
        }
        if(finalStr.equals("")) 
        	return 0;
        if(numOfFu>=2)
           return 0;
        
        t =sign * Long.parseLong(finalStr);
        if(t<Integer.MIN_VALUE) t=Integer.MIN_VALUE;
        if(t>Integer.MAX_VALUE) t=Integer.MAX_VALUE;
        
        int num=(int)t;
        return num;
        
    }
}