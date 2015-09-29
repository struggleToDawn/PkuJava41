
public class Solution {
    public int myAtoi(String str) {
        
	//    int sum = 0;
		double sum = 0;
		int sign = 1;     //正负标记
		int SymbolNum =0; //+-符号统计
		int numFlag =0; //有效符号统计
		int length = str.length();
		for(int i = 0; i < length; i++){
			char c = str.charAt(i);
			if(c >='0' && c<='9'){  //对每个0-9进行运算
				if( (sum * 10 + c - '0') *sign >=Integer.MAX_VALUE )  //溢出返回
			    	return Integer.MAX_VALUE;
				if( (sum * 10 + c - '0') *sign <=Integer.MIN_VALUE )
					return Integer.MIN_VALUE;
				sum = sum * 10 + c - '0';
				numFlag++;
			}else if(c =='-' || c =='+' ){
			    sign = c =='-'  ? -1 : 1 ;
			    numFlag++;
			    SymbolNum++;
			}else{
			    if(c ==' ' && numFlag ==0){
			        continue;
			    }
				break;
			}
			  if(SymbolNum >1) return 0;
		}
	
	  
	    return (int)sum * sign;
	//	System.out.println(sum);
    }
}
