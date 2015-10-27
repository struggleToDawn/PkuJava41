package wuziqi1;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import wuziqi.Chessboard;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo(posX,posY);
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * ������������
	 */
	public int[] computerDo(int posX,int posY) {
		
		int[] judge = new int[2];
		int[] finalResult= new int[2];
		int i = 4;// ��¼���е��������ɵĸ���
		while(i>0){
			
			//���жϺ����Ƿ����Ѿ���i����������
			judge=judegeRow(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			//���ж������Ƿ��г�����������
			judge=judegeRound(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			//Ȼ���ж϶�������б�߷����Ƿ�������i��������
			judge=judegeSlash(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			//����ж�һ������б�߷����Ƿ�������i��������
			judge=judegeSlash2(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			i--;
		}
		
		return finalResult;
		
		
/*		int posX1 = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY1 = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "ʮ") {
			posX1 = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY1 = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX1, posY1 };
		return result;*/
	}
	
	//�жϺ����Ƿ����Ѿ���i����������
	public int[] judegeRow(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
//		int[] ind1 = new int[2];
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "ʮ")){
    		   result[0] =posX;
    		   result[1] =posY-1;
    	   }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "ʮ")){
    		   result[0] =posX;
    		   result[1] =posY+1;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals( "ʮ")){
       		   result[0] =posX;
       		   result[1] =posY-2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "ʮ")){
          	   result[0] =posX;
           	   result[1] =posY+1;
        	  }
        	  else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals( "ʮ")){
          		   result[0] =posX;
           		   result[1] =posY+2;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "ʮ")){
        		   result[0] =posX;
          		   result[1] =posY-1;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  }
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("��")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals( "ʮ")){
        		  result[0] =posX;
          		  result[1] =posY-3;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "ʮ")){
        		  result[0] =posX;
          		  result[1] =posY+1;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals( "ʮ")){
           		  result[0] =posX;
          		  result[1] =posY-2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals( "ʮ")){
        		  result[0] =posX;
          		  result[1] =posY+2;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("��")&&isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "ʮ")){
        		  result[0] =posX;
          		  result[1] =posY-1;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("��")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals( "ʮ")){
        		  result[0] =posX;
          		  result[1] =posY+3;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("��")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals("��")&&isInTheBoard(posX, posY-4)&&board[posX][posY-4].equals( "ʮ")){
        		  result[0] =posX;
          		  result[1] =posY-4;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("��")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "ʮ")){
        		  result[0]= posX;
        		  result[1]= posY+1;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals( "ʮ")){
        		  result[0]=posX;
        		  result[1]=posY-3;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals( "ʮ")){
        		  result[0]=posX;
        		  result[1]=posY+2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("��")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals( "ʮ")){
        		  result[0]=posX;
        		  result[1]=posY-2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("��")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("��")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals( "ʮ")){
        		  result[0]=posX;
        		  result[1]=posY+3;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("��")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals("��")&&isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "ʮ")){
        		  result[0]=posX;
        		  result[1]=posY-1;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("��")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("��")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals("��")&&isInTheBoard(posX, posY+4)&&board[posX][posY+4].equals( "ʮ")){
        		  result[0]=posX;
        		  result[1]=posY+4;
        	  }else{
         		   result[0] =-1;
           		   result[1] =-1;
        	  }     	  
          };
          break;  	   
	 }
		return result;
	}	
	//�ж������Ƿ��г�����������
	public int[] judegeRound(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
//		int[] ind1 = new int[2];
//		int[] ind2 = new int[2];
//		int count=1;
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "ʮ")){
    		   result[0] =posX-1;
    		   result[1] =posY;
    	   }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "ʮ")){
    		   result[0] =posX+1;
    		   result[1] =posY;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals( "ʮ")){
       		   result[0] =posX-2;
       		   result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "ʮ")){
          	   result[0] =posX+1;
           	   result[1] =posY;
        	  }
        	  else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals( "ʮ")){
          		   result[0] =posX+2;
           		   result[1] =posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "ʮ")){
        		   result[0] =posX-1;
          		   result[1] =posY;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  }
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("��")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals( "ʮ")){
        		  result[0] =posX-3;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "ʮ")){
        		  result[0] =posX+1;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals( "ʮ")){
           		  result[0] =posX-2;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals( "ʮ")){
        		  result[0] =posX+2;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("��")&&isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "ʮ")){
        		  result[0] =posX-1;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("��")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals( "ʮ")){
        		  result[0] =posX+3;
          		  result[1] =posY;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("��")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals("��")&&isInTheBoard(posX-4, posY)&&board[posX-4][posY].equals( "ʮ")){
        		  result[0] =posX-4;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("��")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "ʮ")){
        		  result[0]= posX+1;
        		  result[1]= posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals( "ʮ")){
        		  result[0]=posX-3;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals( "ʮ")){
        		  result[0]=posX+2;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("��")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals( "ʮ")){
        		  result[0]=posX-2;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("��")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("��")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals( "ʮ")){
        		  result[0]=posX+3;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("��")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals("��")&&isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "ʮ")){
        		  result[0]=posX-1;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("��")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("��")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals("��")&&isInTheBoard(posX+4, posY)&&board[posX+4][posY].equals( "ʮ")){
        		  result[0]=posX+4;
        		  result[1]=posY;
        	  }else{
         		   result[0] =-1;
           		   result[1] =-1;
        	  }     	  
          };
          break;  	   
	 }
		return result;
	}	
	//�ж϶�������б�߷����Ƿ�������i��������
	public int[] judegeSlash(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "ʮ")){
    		   result[0] =posX-1;
    		   result[1] =posY-1;
    	   }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "ʮ")){
    		   result[0] =posX+1;
    		   result[1] =posY+1;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals( "ʮ")){
       		   result[0] =posX-2;
       		   result[1] =posY-2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "ʮ")){
          	   result[0] =posX+1;
           	   result[1] =posY+1;
        	  }
        	  else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals( "ʮ")){
          		   result[0] =posX+2;
           		   result[1] =posY+2;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "ʮ")){
        		   result[0] =posX-1;
          		   result[1] =posY-1;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  }
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("��")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals( "ʮ")){
        		  result[0] =posX-3;
          		  result[1] =posY-3;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "ʮ")){
        		  result[0] =posX+1;
          		  result[1] =posY+1;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals( "ʮ")){
           		  result[0] =posX-2;
          		  result[1] =posY-2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals( "ʮ")){
        		  result[0] =posX+2;
          		  result[1] =posY+2;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("��")&&isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "ʮ")){
        		  result[0] =posX-1;
          		  result[1] =posY-1;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("��")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals( "ʮ")){
        		  result[0] =posX+3;
          		  result[1] =posY+3;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("��")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals("��")&&isInTheBoard(posX-4, posY-4)&&board[posX-4][posY-4].equals( "ʮ")){
        		  result[0] =posX-4;
          		  result[1] =posY-4;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("��")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "ʮ")){
        		  result[0]= posX+1;
        		  result[1]= posY+1;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals( "ʮ")){
        		  result[0]=posX-3;
        		  result[1]=posY-3;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals( "ʮ")){
        		  result[0]=posX+2;
        		  result[1]=posY+2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("��")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals( "ʮ")){
        		  result[0]=posX-2;
        		  result[1]=posY-2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("��")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("��")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals( "ʮ")){
        		  result[0]=posX+3;
        		  result[1]=posY+3;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("��")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals("��")&&isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "ʮ")){
        		  result[0]=posX-1;
        		  result[1]=posY-1;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("��")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("��")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals("��")&&isInTheBoard(posX+4, posY+4)&&board[posX+4][posY+4].equals( "ʮ")){
        		  result[0]=posX+4;
        		  result[1]=posY+4;
        	  }else{
         		   result[0] =-1;
           		   result[1] =-1;
        	  }     	  
          };
          break;  	   
	 }
		return result;
	}	
	//�ж�һ������б�߷����Ƿ�������i��������
	public int[] judegeSlash2(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "ʮ")){
    		   result[0] =posX+1;
    		   result[1] =posY-1;
    	   }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "ʮ")){
    		   result[0] =posX-1;
    		   result[1] =posY+1;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals( "ʮ")){
       		   result[0] =posX+2;
       		   result[1] =posY-2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "ʮ")){
          	   result[0] =posX-1;
           	   result[1] =posY+1;
        	  }
        	  else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "ʮ")){
          		   result[0] =posX+1;
          		   result[1] =posY-1;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals( "ʮ")){
          		   result[0] =posX-2;
           		   result[1] =posY+2;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  } 
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("��")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals( "ʮ")){
        		  result[0] =posX+3;
          		  result[1] =posY-3;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "ʮ")){
        		  result[0] =posX-1;
          		  result[1] =posY+1;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals( "ʮ")){
           		  result[0] =posX+2;
          		  result[1] =posY-2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals( "ʮ")){
        		  result[0] =posX-2;
          		  result[1] =posY+2;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("��")&&isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "ʮ")){
        		  result[0] =posX+1;
          		  result[1] =posY-1;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("��")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals( "ʮ")){
        		  result[0] =posX-3;
          		  result[1] =posY+3;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("��")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals("��")&&isInTheBoard(posX+4, posY-4)&&board[posX+4][posY-4].equals( "ʮ")){
        		  result[0] =posX+4;
          		  result[1] =posY-4;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("��")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "ʮ")){
        		  result[0]= posX-1;
        		  result[1]= posY+1;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals( "ʮ")){
        		  result[0]=posX+3;
        		  result[1]=posY-3;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals( "ʮ")){
        		  result[0]=posX-2;
        		  result[1]=posY+2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("��")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals( "ʮ")){
        		  result[0]=posX+2;
        		  result[1]=posY-2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("��")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("��")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals( "ʮ")){
        		  result[0]=posX-3;
        		  result[1]=posY+3;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("��")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals("��")&&isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "ʮ")){
        		  result[0]=posX+1;
        		  result[1]=posY-1;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("��")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("��")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals("��")&&isInTheBoard(posX-4, posY+4)&&board[posX-4][posY+4].equals( "ʮ")){
        		  result[0]=posX-4;
        		  result[1]=posY+4;
        	  }else{
         		   result[0] =-1;
           		   result[1] =-1;
        	  }     	  
          };
          break;  	   
	 }
		return result;
	}
	
/*	//�жϻ�����Ҫ�µ�ֵ�Ƿ��ǺϷ���
	public boolean isValidCompute(int posX,int posY){
		// �����ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			return false;
		}
		// ���λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			return false;
		}
		return true;
	}*/

	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();
		int pointer1;
		int pointer2;
		int count=1;	
		//�жϺ����Ƿ���������
		for(pointer1=posX+1;pointer1<posX+5;pointer1++){
			  if(pointer1<Chessboard.BOARD_SIZE && pointer1 > -1 && board[pointer1][posY].equals(ico)){
				  count++;
			  }
			  else {
				break;
			}
		}
		for(pointer2=posX-1;pointer2>posX-5;pointer2--){
			 if(pointer2<Chessboard.BOARD_SIZE-1 && pointer2 > -1 && board[pointer2][posY].equals(ico)){
				  count++;
			  }
			  else {
					break;
				}
		}
		
		if(count==WIN_COUNT){
			return true;
		}
		else{
			count=1;
		}
		//�ж������Ƿ���������
		for(pointer1=posY+1;pointer1<posY+5;pointer1++){
			  if(pointer1<Chessboard.BOARD_SIZE-1 && pointer1 > -1 && board[posX][pointer1].equals(ico)){
				  count++;
			  }
			  else {
					break;
				}
		}
		for(pointer2=posY-1;pointer2>posY-5;pointer2--){
			 if(pointer2<Chessboard.BOARD_SIZE-1 && pointer2 > -1 && board[posX][pointer2].equals(ico)){
				  count++;
			  }
			  else {
					break;
				}
		}
		
		if(count==WIN_COUNT){
			return true;
		}
		else{
			count=1;
		}
		
		//�ж϶�������б���Ƿ���������

		for(pointer1=posX-1,pointer2=posY+1;pointer1>posX-5&&pointer2<posY+5;pointer1--,pointer2++){
			  if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(ico)){
				  count++;
			  }
			  else {
					break;
				}
		}
		for(pointer1=posX+1,pointer2=posY-1;pointer1<posX+5&&pointer1>posX-5;pointer1++,pointer2--){
			 if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(ico)){
				 count++;
			  }
			  else {
					break;
				}
		}
		if(count==WIN_COUNT){
			return true;
		}
		else{
			count=1;
		}
		
		//�ж�һ������б���Ƿ���������
		for(pointer1=posX-1,pointer2=posY-1;pointer1>posX-5&&pointer2>posY-5;pointer1--,pointer2--){
			  if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(ico)){
				  count++;
			  }
			  else {
					break;
				}
		}
		for(pointer1=posX+1,pointer2=posY+1;pointer1<posX+5&&pointer1<posX+5;pointer1++,pointer2++){
			 if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(ico)){
				  count++;
			  }
			  else {
					break;
				}
		}
		
		if(count==WIN_COUNT){
			return true;
		}
		else{
			return false;
		}
	}
	
	//�ж��Ƿ���������
	public boolean isInTheBoard(int posX,int posY){
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			return false;
		}
		else {
			return true;
		}
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
