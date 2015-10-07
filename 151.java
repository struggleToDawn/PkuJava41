public class Solution {
    public String reverseWords(String s) {
        // String aa = "hello world  wei";
		String cc ="";
		String[] bb = s.split(" ");   //按空格把字符串分割。
		for(int i =bb.length -1; i>=0;i--){
// 			System.out.println(bb[i]);
			if(bb[i].length() > 0){   // 倒序将字符拼装
				// System.out.println(2222);
				cc += bb[i] + " ";  
			}
		}
		
		return cc.trim() ;
    }
}
