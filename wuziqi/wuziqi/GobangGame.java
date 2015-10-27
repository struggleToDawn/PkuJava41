package wuziqi1;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import wuziqi.Chessboard;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo(posX,posY);
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}

	/**
	 * 计算机随机下棋
	 */
	public int[] computerDo(int posX,int posY) {
		
		int[] judge = new int[2];
		int[] finalResult= new int[2];
		int i = 4;// 记录已有的棋子连成的个数
		while(i>0){
			
			//先判断横向是否有已经成i连续的序列
			judge=judegeRow(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			//再判断竖向是否有成连续的序列
			judge=judegeRound(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			//然后判断二四象限斜线方向是否有连续i个的序列
			judge=judegeSlash(posX, posY, i);
			if(judge[0]!=-1){
				finalResult[0]=judge[0];
				finalResult[1]=judge[1];
				break;
			}
			//最后判断一三象限斜线方向是否有连续i个的序列
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
		while (board[posX][posY] != "十") {
			posX1 = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY1 = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		int[] result = { posX1, posY1 };
		return result;*/
	}
	
	//判断横向是否有已经成i连续的序列
	public int[] judegeRow(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
//		int[] ind1 = new int[2];
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "十")){
    		   result[0] =posX;
    		   result[1] =posY-1;
    	   }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "十")){
    		   result[0] =posX;
    		   result[1] =posY+1;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals( "十")){
       		   result[0] =posX;
       		   result[1] =posY-2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "十")){
          	   result[0] =posX;
           	   result[1] =posY+1;
        	  }
        	  else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals( "十")){
          		   result[0] =posX;
           		   result[1] =posY+2;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "十")){
        		   result[0] =posX;
          		   result[1] =posY-1;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  }
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("●")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals( "十")){
        		  result[0] =posX;
          		  result[1] =posY-3;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "十")){
        		  result[0] =posX;
          		  result[1] =posY+1;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals( "十")){
           		  result[0] =posX;
          		  result[1] =posY-2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals( "十")){
        		  result[0] =posX;
          		  result[1] =posY+2;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("●")&&isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "十")){
        		  result[0] =posX;
          		  result[1] =posY-1;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("●")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals( "十")){
        		  result[0] =posX;
          		  result[1] =posY+3;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("●")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals("●")&&isInTheBoard(posX, posY-4)&&board[posX][posY-4].equals( "十")){
        		  result[0] =posX;
          		  result[1] =posY-4;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("●")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals( "十")){
        		  result[0]= posX;
        		  result[1]= posY+1;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY-3)&&board[posX][posY-3].equals( "十")){
        		  result[0]=posX;
        		  result[1]=posY-3;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals( "十")){
        		  result[0]=posX;
        		  result[1]=posY+2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("●")&&isInTheBoard(posX, posY-2)&&board[posX][posY-2].equals( "十")){
        		  result[0]=posX;
        		  result[1]=posY-2;
        	  }else if(isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals("●")&&isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("●")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals( "十")){
        		  result[0]=posX;
        		  result[1]=posY+3;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("●")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals("●")&&isInTheBoard(posX, posY-1)&&board[posX][posY-1].equals( "十")){
        		  result[0]=posX;
        		  result[1]=posY-1;
        	  }else if(isInTheBoard(posX, posY+1)&&board[posX][posY+1].equals("●")&&isInTheBoard(posX, posY+2)&&board[posX][posY+2].equals("●")&&isInTheBoard(posX, posY+3)&&board[posX][posY+3].equals("●")&&isInTheBoard(posX, posY+4)&&board[posX][posY+4].equals( "十")){
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
	//判断竖向是否有成连续的序列
	public int[] judegeRound(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
//		int[] ind1 = new int[2];
//		int[] ind2 = new int[2];
//		int count=1;
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "十")){
    		   result[0] =posX-1;
    		   result[1] =posY;
    	   }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "十")){
    		   result[0] =posX+1;
    		   result[1] =posY;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals( "十")){
       		   result[0] =posX-2;
       		   result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "十")){
          	   result[0] =posX+1;
           	   result[1] =posY;
        	  }
        	  else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals( "十")){
          		   result[0] =posX+2;
           		   result[1] =posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "十")){
        		   result[0] =posX-1;
          		   result[1] =posY;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  }
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("●")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals( "十")){
        		  result[0] =posX-3;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "十")){
        		  result[0] =posX+1;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals( "十")){
           		  result[0] =posX-2;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals( "十")){
        		  result[0] =posX+2;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("●")&&isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "十")){
        		  result[0] =posX-1;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("●")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals( "十")){
        		  result[0] =posX+3;
          		  result[1] =posY;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("●")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals("●")&&isInTheBoard(posX-4, posY)&&board[posX-4][posY].equals( "十")){
        		  result[0] =posX-4;
          		  result[1] =posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("●")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals( "十")){
        		  result[0]= posX+1;
        		  result[1]= posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX-3, posY)&&board[posX-3][posY].equals( "十")){
        		  result[0]=posX-3;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals( "十")){
        		  result[0]=posX+2;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("●")&&isInTheBoard(posX-2, posY)&&board[posX-2][posY].equals( "十")){
        		  result[0]=posX-2;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals("●")&&isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("●")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals( "十")){
        		  result[0]=posX+3;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("●")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals("●")&&isInTheBoard(posX-1, posY)&&board[posX-1][posY].equals( "十")){
        		  result[0]=posX-1;
        		  result[1]=posY;
        	  }else if(isInTheBoard(posX+1, posY)&&board[posX+1][posY].equals("●")&&isInTheBoard(posX+2, posY)&&board[posX+2][posY].equals("●")&&isInTheBoard(posX+3, posY)&&board[posX+3][posY].equals("●")&&isInTheBoard(posX+4, posY)&&board[posX+4][posY].equals( "十")){
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
	//判断二四象限斜线方向是否有连续i个的序列
	public int[] judegeSlash(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "十")){
    		   result[0] =posX-1;
    		   result[1] =posY-1;
    	   }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "十")){
    		   result[0] =posX+1;
    		   result[1] =posY+1;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals( "十")){
       		   result[0] =posX-2;
       		   result[1] =posY-2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "十")){
          	   result[0] =posX+1;
           	   result[1] =posY+1;
        	  }
        	  else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals( "十")){
          		   result[0] =posX+2;
           		   result[1] =posY+2;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "十")){
        		   result[0] =posX-1;
          		   result[1] =posY-1;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  }
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("●")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals( "十")){
        		  result[0] =posX-3;
          		  result[1] =posY-3;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "十")){
        		  result[0] =posX+1;
          		  result[1] =posY+1;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals( "十")){
           		  result[0] =posX-2;
          		  result[1] =posY-2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals( "十")){
        		  result[0] =posX+2;
          		  result[1] =posY+2;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("●")&&isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "十")){
        		  result[0] =posX-1;
          		  result[1] =posY-1;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("●")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals( "十")){
        		  result[0] =posX+3;
          		  result[1] =posY+3;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("●")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals("●")&&isInTheBoard(posX-4, posY-4)&&board[posX-4][posY-4].equals( "十")){
        		  result[0] =posX-4;
          		  result[1] =posY-4;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("●")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals( "十")){
        		  result[0]= posX+1;
        		  result[1]= posY+1;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX-3, posY-3)&&board[posX-3][posY-3].equals( "十")){
        		  result[0]=posX-3;
        		  result[1]=posY-3;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals( "十")){
        		  result[0]=posX+2;
        		  result[1]=posY+2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("●")&&isInTheBoard(posX-2, posY-2)&&board[posX-2][posY-2].equals( "十")){
        		  result[0]=posX-2;
        		  result[1]=posY-2;
        	  }else if(isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals("●")&&isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("●")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals( "十")){
        		  result[0]=posX+3;
        		  result[1]=posY+3;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("●")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals("●")&&isInTheBoard(posX-1, posY-1)&&board[posX-1][posY-1].equals( "十")){
        		  result[0]=posX-1;
        		  result[1]=posY-1;
        	  }else if(isInTheBoard(posX+1, posY+1)&&board[posX+1][posY+1].equals("●")&&isInTheBoard(posX+2, posY+2)&&board[posX+2][posY+2].equals("●")&&isInTheBoard(posX+3, posY+3)&&board[posX+3][posY+3].equals("●")&&isInTheBoard(posX+4, posY+4)&&board[posX+4][posY+4].equals( "十")){
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
	//判断一三象限斜线方向是否有连续i个的序列
	public int[] judegeSlash2(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard(); 
		
		int[] result = new int[2];

       switch(nums){
          case 1:{
    	   if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "十")){
    		   result[0] =posX+1;
    		   result[1] =posY-1;
    	   }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "十")){
    		   result[0] =posX-1;
    		   result[1] =posY+1;
    	   }else{
    		   result[0] =-1;
    		   result[1] =-1;
    	   }
          };
          break;
          
          case 2:{
        	  if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals( "十")){
       		   result[0] =posX+2;
       		   result[1] =posY-2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "十")){
          	   result[0] =posX-1;
           	   result[1] =posY+1;
        	  }
        	  else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "十")){
          		   result[0] =posX+1;
          		   result[1] =posY-1;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals( "十")){
          		   result[0] =posX-2;
           		   result[1] =posY+2;
        	  }else{
       		   result[0] =-1;
       		   result[1] =-1;
        	  } 
          };
          break;
          
          case 3:{
        	  if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("●")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals( "十")){
        		  result[0] =posX+3;
          		  result[1] =posY-3;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "十")){
        		  result[0] =posX-1;
          		  result[1] =posY+1;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals( "十")){
           		  result[0] =posX+2;
          		  result[1] =posY-2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals( "十")){
        		  result[0] =posX-2;
          		  result[1] =posY+2;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("●")&&isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "十")){
        		  result[0] =posX+1;
          		  result[1] =posY-1;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("●")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals( "十")){
        		  result[0] =posX-3;
          		  result[1] =posY+3;
        	  }else{
          		   result[0] =-1;
           		   result[1] =-1;
            	  }    	  
          };
          break;
          
          
          case 4:{
        	  if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("●")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals("●")&&isInTheBoard(posX+4, posY-4)&&board[posX+4][posY-4].equals( "十")){
        		  result[0] =posX+4;
          		  result[1] =posY-4;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("●")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals( "十")){
        		  result[0]= posX-1;
        		  result[1]= posY+1;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX+3, posY-3)&&board[posX+3][posY-3].equals( "十")){
        		  result[0]=posX+3;
        		  result[1]=posY-3;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals( "十")){
        		  result[0]=posX-2;
        		  result[1]=posY+2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("●")&&isInTheBoard(posX+2, posY-2)&&board[posX+2][posY-2].equals( "十")){
        		  result[0]=posX+2;
        		  result[1]=posY-2;
        	  }else if(isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals("●")&&isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("●")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals( "十")){
        		  result[0]=posX-3;
        		  result[1]=posY+3;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("●")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals("●")&&isInTheBoard(posX+1, posY-1)&&board[posX+1][posY-1].equals( "十")){
        		  result[0]=posX+1;
        		  result[1]=posY-1;
        	  }else if(isInTheBoard(posX-1, posY+1)&&board[posX-1][posY+1].equals("●")&&isInTheBoard(posX-2, posY+2)&&board[posX-2][posY+2].equals("●")&&isInTheBoard(posX-3, posY+3)&&board[posX-3][posY+3].equals("●")&&isInTheBoard(posX-4, posY+4)&&board[posX-4][posY+4].equals( "十")){
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
	
/*	//判断机器所要下的值是否是合法的
	public boolean isValidCompute(int posX,int posY){
		// 检查数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			return false;
		}
		// 检查位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			return false;
		}
		return true;
	}*/

	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) {
		String[][] board = chessboard.getBoard();
		int pointer1;
		int pointer2;
		int count=1;	
		//判断横向是否连成五子
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
		//判断竖向是否连成五子
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
		
		//判断二四象限斜向是否连成五子

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
		
		//判断一三象限斜向是否连成五子
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
	
	//判断是否在棋盘内
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
