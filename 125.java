public class Solution {
    public boolean isPalindrome(String s) {
        
        //ע���е����ַ�������
        //��Ҫԭ����spilt()�����Ứ�Ѵ�����ʱ�䣬����һ��forѭ�������ַ�������
        //�ټ��ϻ���һ��whileѭ���жϣ������ַ����ر���ʱ��ᳬʱ
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
    
    //�ڶ��ַ�������һ��filter���������ַ������й���
    //�������ַ������ֵ�Ԫ��
    //����whileѭ������һ���ж�
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