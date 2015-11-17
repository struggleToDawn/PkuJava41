package lianxi;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class ReadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		//FileReader file1 = new FileReader(new File("file1.txt"));
		
/*		File directory = new File(file1.txt);//设定为当前文件夹
		try{
		    System.out.println(directory.getCanonicalPath());//获取标准的路径
		    System.out.println(directory.getAbsolutePath());//获取绝对路径
		}*/
		
			FileReader file1 = new FileReader("F:\\Workspace\\xuexi\\src\\lianxi\\file1.txt");
			FileReader file2 = new FileReader("F:\\Workspace\\xuexi\\src\\lianxi\\file2.txt");


		BufferedReader file1Buffer = new BufferedReader(file1);
		BufferedReader file2Buffer = new BufferedReader(file2);
		
		Set<String> fileSet1 = readFile(file1Buffer);
		Set<String> fileSet2 = readFile(file2Buffer);
		
		//并集
		Set<String> resultSet = new HashSet<String>();
		
		//交集
		Set<String> resultSet1 = new HashSet<String>();
		
		System.out.println("file1.txt中的单词表：");
		printSet(fileSet1);
		
		System.out.println("file2.txt中的单词表：");
		printSet(fileSet2);
		
		//求并集
		resultSet.addAll(fileSet1);
		resultSet.addAll(fileSet2);
		System.out.println("file1.txt和file2.txt中单词的并集为：");
		printSet(resultSet);
		
		//求交集
		resultSet1=resultSet;
		resultSet1.retainAll(fileSet1);
		resultSet1.retainAll(fileSet2);
		System.out.println("file1.txt和file2.txt中单词的交集为：");
		printSet(resultSet1);

	}
	
	
	public static Set<String> readFile(BufferedReader br) throws Exception{
		Set<String> fianlSet = new HashSet<String>();
		
		String strLine;
		strLine=br.readLine();
		if(strLine==null)
			throw new Exception("文件为空！");
		while(strLine!=null){
			String[] strs = strLine.split(" ");
			for(int i=0;i<strs.length;i++){
				fianlSet.add(strs[i]);
			}
			strLine = br.readLine();
		}
		return fianlSet;
	}
	
	public static void printSet (Set<String> setStr){
		int number =0;
		Iterator<String> iterator = setStr.iterator();
		
		while(iterator.hasNext()){
			String str= iterator.next();
			number++;
			System.out.print(" " + str);
		}
		System.out.println("该集合中有"+ number +"个单词");	
	}

}
