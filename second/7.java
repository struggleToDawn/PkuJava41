
public class Solution {
    public int reverse(int x) {
          int res = 0;
        boolean flag = x > 0;
        while(x!=0){
            if(Math.abs(res)>214748364||Math.abs(res)==214748364&&Math.abs(x%10)>((flag)?7:8))   return 0;
            res = res*10+x%10;  //倒向输出
            x/=10;
        }
        return res;
    }
}
