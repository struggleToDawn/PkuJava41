
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ReadFile {

	public static void main(String arg[]) throws IOException{

		FileReader file1 = new FileReader("A.txt");
		FileReader file2 = new FileReader("B.txt");
		BufferedReader br1 = new BufferedReader(file1);
		BufferedReader br2 = new BufferedReader(file2);
		Set<String> file1Set =  new HashSet<String>();
		Set<String> file2Set =  new HashSet<String>();
		Set<String> result =  new HashSet<String>();
		String row;
		System.out.println("文件A：");
		while((row = br1.readLine())!=null){
			String[] bb = row.split(" ");
			for(String a:bb){
				file1Set.add(a);
				System.out.println(a);
			}
		}
		System.out.println("文件B：");
		while((row = br2.readLine())!=null){
			String[] bb = row.split(" ");
			for(String a:bb){
				file2Set.add(a);
				System.out.println(a);
			}
		}
		//交集
		result.addAll(file1Set);
		result.retainAll(file2Set);
		System.out.println("交集："+result);
		//并集
		result.addAll(file1Set);
		result.addAll(file2Set);
		System.out.println("并集："+result);
		//差集
		
		result.addAll(file1Set);
		result.removeAll(file2Set);
		System.out.println("差集1："+result +"A有但B没有的单词占A "+100*(float)result.size()/file1Set.size() +"%");	
		//差集
		result.addAll(file2Set);
		result.removeAll(file1Set);
		System.out.println("交集2："+result +"B有但A没有的单词占B "+100*(float)result.size()/file2Set.size() +"%");
	}
}

