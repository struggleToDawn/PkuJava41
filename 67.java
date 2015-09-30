package xuexi;

public class Solution1 {
	//开始的时候思路是将二进制字符串转化为十进制的整数，将两个整数之和再转换为字符串
	//测试的时候发现此路不通，原因是整数的表示范围有限，当二进制字符串太长时出错，所以这种想法不对
	public  String addBinary(String a,String b){		
		String fianlStr="";
		String str1,str2;
		if(a.length()>b.length()){
			str1 = a;
			str2 =changeToEqualLength(b,a);
		}
		else {
			str1=changeToEqualLength(a,b);
			str2=b;
		}
		//target[]主要是记录前两位的计算是否进位
	  boolean[] target = new boolean[str1.length()+1];
	for (int i = str1.length()-1; i >=0; i--) {
		if(target[i+1]){
			if(str1.charAt(i)=='0'&&str2.charAt(i)=='0'){
				fianlStr = "1" + fianlStr;
				target[i]=false;
			}
			else if (str1.charAt(i)=='1'&&str2.charAt(i)=='0'||str1.charAt(i)=='0'&&str2.charAt(i)=='1') {
				fianlStr = "0" + fianlStr;
				target[i]=true;
			}
			else {
				fianlStr = "1" + fianlStr;
				target[i]=true;
			}
		}
		else {
			if (str1.charAt(i)=='0'&&str2.charAt(i)=='0') {
				fianlStr = "0" + fianlStr;
				target[i]=false;
			} else if (str1.charAt(i)=='1'&&str2.charAt(i)=='1') {
				fianlStr = "0" + fianlStr;
				target[i]=true;
			}
			else {
				fianlStr = "1" + fianlStr;
				target[i]=false;
			}
		}
			
		
	}		
	  if(target[0])
		  fianlStr = "1"+fianlStr;
		return fianlStr;
	}
	//此方法主要是两个字符串变成同等长度
	public static String changeToEqualLength(String shortStr,String longStr){
		String prefixStr = "";
		int num = longStr.length() - shortStr.length();
		for (int i = 0; i < num; i++) {
			prefixStr = "0" + prefixStr;
		}
		return prefixStr+shortStr;		
	}

}
