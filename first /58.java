public class Solution {
    public int lengthOfLastWord(String s) {
     
    //  String hello = "Hello World";
		
		s = s.trim();
		int hlength = s.length();
// 		System.out.println(hlength);
		
		for(int i=hlength-1; i>=0;i--){
		//	System.out.println(hello.charAt(i));
		if(s.charAt(i) ==' '){
			return ( hlength-i-1);
		}   
		
    }
        return  hlength;
        
    }
}
