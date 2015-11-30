public class Solution {
    public int titleToNumber(String s) {
        if(s==null)
           return 0;
        char[] chars = s.toCharArray();
        int nums=0;
        for(int i=0;i<chars.length;i++){
            nums=26*nums+swithToNum(chars[i]);
        }
        
        return nums;
    }
    
    //将字母转换为数字
    public static int swithToNum(char c){
        int num=0;
        switch(c){
            case 'A':num=1;
             break;
            case 'B':num=2;
             break;
            case 'C':num=3;
             break;
            case 'D':num=4;
             break;
            case 'E':num=5;
             break;
            case 'F':num=6;
             break;
            case 'G':num=7;
             break;
            case 'H':num=8;
             break;
            case 'I':num=9;
             break;
            case 'J':num=10;
             break;
            case 'K':num=11;
             break;
            case 'L':num=12;
             break;
            case 'M':num=13;
             break;
            case 'N':num=14;
             break;
            case 'O':num=15;
             break;
            case 'P':num=16;
             break;
            case 'Q':num=17;
             break;
            case 'R':num=18;
             break;
            case 'S':num=19;
             break;
            case 'T':num=20;
             break;
            case 'U':num=21;
             break;
            case 'V':num=22;
             break;
            case 'W':num=23;
             break;
            case 'X':num=24;
             break;
            case 'Y':num=25;
             break;
            case 'Z':num=26;
             break;
        }
        return num;
    }
}