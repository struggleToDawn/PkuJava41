public class Solution {
    public int lengthOfLastWord(String s) {
        if(s.equals(""))
             return 0;
    
        /*String[] tokens = s.split(" ");
        return tokens[tokens.length-1].length();*/
        String newStr=s.trim();
        int i=newStr.length()-1;
        int k=0;
        while(i>=0&&newStr.charAt(i)!=' '){
            i--;
            k++;
        }
        return k;
    }
}