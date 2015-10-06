public class Solution {
    public List<Integer> getRow(int rowIndex) {
          List<List<Integer>> list = new ArrayList<List<Integer>>();
          //申明一个二维list表来存放所有的list
         /* if(rowIndex<0)
             return ;*/
           
           //行数为1的时候初始化第一行  
          List<Integer> row0 = new ArrayList<Integer>();
          row0.add(1);
          list.add(row0);
          if(rowIndex==0)
             return row0;
        
         //初始化list的第二行 
          List<Integer> row1 = new ArrayList<Integer>();
          row1.add(1);
          row1.add(1);
          list.add(row1);
          if(rowIndex==1)
              return row1;
          //通过第一行和第二行来获取其他的所有行    
         for(int i = 2;i<rowIndex+1;i++){
             List<Integer> rown = new ArrayList<Integer>();
             rown.add(1);
             for(int j =1;j<i;j++){
                 rown.add(list.get(i-1).get(j-1) + list.get(i-1).get(j));
             }
            rown.add(1);
            list.add(rown);
         }
         //返回需要的那一行
         return list.get(rowIndex);
             
    }
}