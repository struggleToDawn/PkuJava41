public class Solution {
    public String reverseWords(String s) {
     if(s.equals(""))
     return s;
     if(s.equals(" "))
     return "";
    String newStr = s.trim();
    if(newStr.equals(" ")||newStr.equals(""))
    return "";
    String finalStr="";
    //newStr.replaceAll( "\\s+ ", "| ");
    String[] tokens =newStr.split(" ");

    for(int i =tokens.length-1;i>=0;i--){
        if(tokens[i].length()>0)
        finalStr += tokens[i]+" ";
    }
/*    
    for(int i=0; i<tokens.length;i++){
        finalStr += newTokens[i] + " ";
    }*/
    return finalStr.trim();
    }
}