public class Solution {
    public int titleToNumber(String s) {
         int sum = 0;
    for(int k = 0; k < s.length(); k++) {
        sum *= 26;
        sum += (s.charAt(k) - 'A' + 1);
    }
    return sum;
    }
}
