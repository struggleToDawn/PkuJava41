package xuexi;

public class Solution1 {
	//��ʼ��ʱ��˼·�ǽ��������ַ���ת��Ϊʮ���Ƶ�����������������֮����ת��Ϊ�ַ���
	//���Ե�ʱ���ִ�·��ͨ��ԭ���������ı�ʾ��Χ���ޣ����������ַ���̫��ʱ�������������뷨����
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
		//target[]��Ҫ�Ǽ�¼ǰ��λ�ļ����Ƿ��λ
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
	//�˷�����Ҫ�������ַ������ͬ�ȳ���
	public static String changeToEqualLength(String shortStr,String longStr){
		String prefixStr = "";
		int num = longStr.length() - shortStr.length();
		for (int i = 0; i < num; i++) {
			prefixStr = "0" + prefixStr;
		}
		return prefixStr+shortStr;		
	}

}
