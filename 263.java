public class Solution {
    public boolean isUgly(int num) {
        
        //前面的那种方法比较笨，是两个循环来解决问题
        //思路是将所有的num的约数都存在一个list里面，存的时候如果遇到2,3,5则跳过
        //最后通过判断list是否为空来分这个数是否为ugly数
        //此方法可以通过但是时间复杂度较高
/*        List<Integer> list = new ArrayList<Integer>();
        
        if(num<0)
           return false;
        if(num ==1)
           return true;
        
        while(num>0){
            for(int i=2;inum/2;i++){
                if(num % i==0){
                    list.add(i);
                    num /= i;
                    if(i==2||i==3||i==5)
                      list.remove(i);
                    break;
                }
            }
        }
        if(list.isEmpty())
          return true;
         else
           return false;*/

       //下面这种方法直接将num数不断除2,3,5
       //通过判断最后最后不变的数是否为1来判断
       //时间复杂度为O(n)
        if(num<0)
           return false;
        if(num ==1)
           return true;
         
        boolean target = true;
        while(num>=1&&target){
            if(num%2==0){
                target = true;
                num /=2;
            }
            else if(num % 3==0){
                target = true;
                num/=3;
            }
            else if(num%5==0){
                target = true;
                num/=5;
            }
            else
               target = false;
        }
         if(num==1)
            return true;
         else
           return false;
    }
}