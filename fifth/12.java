public class Solution {
    public String intToRoman(int num) {
        String result = "";
        String c[][]={{"0","I","II","III","IV","V","VI","VII","VIII","IX"},{"0","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},{"0","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},{"0","M","MM","MMM"}};
        int tmp = 0;
        tmp = num / 1000;
        if(tmp>0){
            result += c[3][tmp];
        }
        num = num - tmp *1000;
        tmp = num / 100;
        
        if(tmp>0){
            result += c[2][tmp];
        }
        
        num = num - tmp *100;
        tmp = num / 10;
        
         if(tmp>0){
            result += c[1][tmp];
        }
        
        tmp =  num - tmp *10;
        if(tmp>0){
            result += c[0][tmp];
        }
        
        return result;
    }
}
