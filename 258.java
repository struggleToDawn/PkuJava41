public class Solution {
    public int addDigits(int num) {
        //����ά�����������ƪ��������
        int k = num-9*((num-1)/9);
        return k;
    }
}