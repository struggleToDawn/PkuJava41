public class Solution {
    public boolean isUgly(int num) {
        
        //ǰ������ַ����Ƚϱ���������ѭ�����������
        //˼·�ǽ����е�num��Լ��������һ��list���棬���ʱ���������2,3,5������
        //���ͨ���ж�list�Ƿ�Ϊ������������Ƿ�Ϊugly��
        //�˷�������ͨ������ʱ�临�ӶȽϸ�
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

       //�������ַ���ֱ�ӽ�num�����ϳ�2,3,5
       //ͨ���ж������󲻱�����Ƿ�Ϊ1���ж�
       //ʱ�临�Ӷ�ΪO(n)
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