public class Solution {
    public boolean isPalindrome(String s) {
        
        //注释中的这种方超市了
        //主要原因是spilt()函数会花费大量的时间，还有一个for循环来组字符串数组
        //再加上还有一个while循环判断，所当字符串特别大的时候会超时
/*     if(s.equals(""))
       return true;
    String[] tokens = s.split("[.,:;!]");
    String newStr="";
    for(int i = 0;i<tokens.length;i++){
        newStr +=tokens[i];
    }
    //String newStr = new String(tokens);
    newStr = newStr.toLowerCase();
    
    
    int low = 0 ;
    int high = newStr.length()-1;
    while(low < high){
        if(newStr.charAt(low)!=newStr.charAt(high))
          return false;
          
        low++;
        high--;
    }
    
    return true;*/
    
    //第二种方法设置一个filter方法来对字符串进行过滤
    //除掉非字符和数字的元素
    //再用while循环进行一个判断
    String newStr = filter(s).toLowerCase();
    int low = 0 ;
    int high = newStr.length()-1;
    while(low < high){
        if(newStr.charAt(low)!=newStr.charAt(high))
          return false;
          
        low++;
        high--;
    }
    return true;
    }
    
    
    public static String filter(String s){
        StringBuilder stringBuilder = new StringBuilder();
        
        for(int i =0;i<s.length();i++){
            if(Character.isLetterOrDigit(s.charAt(i)))
                stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }
}