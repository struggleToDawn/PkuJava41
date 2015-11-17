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
		// TODO �Զ����ɵķ������
		//FileReader file1 = new FileReader(new File("file1.txt"));
		
/*		File directory = new File(file1.txt);//�趨Ϊ��ǰ�ļ���
		try{
		    System.out.println(directory.getCanonicalPath());//��ȡ��׼��·��
		    System.out.println(directory.getAbsolutePath());//��ȡ����·��
		}*/
		
			FileReader file1 = new FileReader("F:\\Workspace\\xuexi\\src\\lianxi\\file1.txt");
			FileReader file2 = new FileReader("F:\\Workspace\\xuexi\\src\\lianxi\\file2.txt");


		BufferedReader file1Buffer = new BufferedReader(file1);
		BufferedReader file2Buffer = new BufferedReader(file2);
		
		Set<String> fileSet1 = readFile(file1Buffer);
		Set<String> fileSet2 = readFile(file2Buffer);
		
		//����
		Set<String> resultSet = new HashSet<String>();
		
		//����
		Set<String> resultSet1 = new HashSet<String>();
		
		System.out.println("file1.txt�еĵ��ʱ�");
		printSet(fileSet1);
		
		System.out.println("file2.txt�еĵ��ʱ�");
		printSet(fileSet2);
		
		//�󲢼�
		resultSet.addAll(fileSet1);
		resultSet.addAll(fileSet2);
		System.out.println("file1.txt��file2.txt�е��ʵĲ���Ϊ��");
		printSet(resultSet);
		
		//�󽻼�
		resultSet1=resultSet;
		resultSet1.retainAll(fileSet1);
		resultSet1.retainAll(fileSet2);
		System.out.println("file1.txt��file2.txt�е��ʵĽ���Ϊ��");
		printSet(resultSet1);

	}
	
	
	public static Set<String> readFile(BufferedReader br) throws Exception{
		Set<String> fianlSet = new HashSet<String>();
		
		String strLine;
		strLine=br.readLine();
		if(strLine==null)
			throw new Exception("�ļ�Ϊ�գ�");
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
		System.out.println("�ü�������"+ number +"������");	
	}

}
