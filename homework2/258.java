public class Solution {
    public int addDigits(int num) {
        //看了维基百上面的那篇介绍文章
        int k = num-9*((num-1)/9);
        return k;
    }
}