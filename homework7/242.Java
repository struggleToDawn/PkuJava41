public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        
        //将字符串转换为字符数组
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        
        //字数组排个序
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        
        //将两个字数组中的元素进行比较，得出是否是回文串
        for(int i =0; i<sChar.length;i++){
            if(sChar[i]!=tChar[i]){
                return false;
            }
        }
        
        return true;
    }
}