public class Solution {
    public List<Integer> getRow(int rowIndex) {
          List<List<Integer>> list = new ArrayList<List<Integer>>();
          //����һ����άlist����������е�list
         /* if(rowIndex<0)
             return ;*/
           
           //����Ϊ1��ʱ���ʼ����һ��  
          List<Integer> row0 = new ArrayList<Integer>();
          row0.add(1);
          list.add(row0);
          if(rowIndex==0)
             return row0;
        
         //��ʼ��list�ĵڶ��� 
          List<Integer> row1 = new ArrayList<Integer>();
          row1.add(1);
          row1.add(1);
          list.add(row1);
          if(rowIndex==1)
              return row1;
          //ͨ����һ�к͵ڶ�������ȡ������������    
         for(int i = 2;i<rowIndex+1;i++){
             List<Integer> rown = new ArrayList<Integer>();
             rown.add(1);
             for(int j =1;j<i;j++){
                 rown.add(list.get(i-1).get(j-1) + list.get(i-1).get(j));
             }
            rown.add(1);
            list.add(rown);
         }
         //������Ҫ����һ��
         return list.get(rowIndex);
             
    }
}