package wuziqi;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
			//System.out.println(chessman);
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
			//System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
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
	public int[] computerDo(int posX1,int posY1) {
		/*
		 * int posX = posX1-5+(int) (Math.random() * 10); int posY =
		 * posY1-5+(int) (Math.random() * 10); String[][] board =
		 * chessboard.getBoard(); while (board[posX][posY] !=
		 * "十"||!isInTheBoard(posX, posY)) { posX = posX1-5+(int) (Math.random()
		 * * 10); posY = posY1-5+(int) (Math.random() * 10); }
		 */
		int[] judge = new int[2];
		int i = 4;// 记录已有的棋子连成的个数
		while (i > 0) {
			judge = judgyRoundWin(posX1, posY1, i);// 判断横向是否有i个棋子连接的情况
			if (judge[0] != -1) {
				return judge;
			}	
			judge = judgeRowWin(posX1, posY1, i);// 判断竖向是否有i个棋子连接的情况
			if (judge[0] != -1) {
				return judge;
			}	
			judge = judgeSlashWin(posX1, posY1, i);	// 判断二四象限斜线是否有i个棋子连接的情况
			if (judge[0] != -1) {
		     /*	result = judge;
				break;*/
				return judge;
			}
			judge = jedgeSlash2(posX1, posY1, i);// 判断一三象限斜线是否有i个棋子连接的情况
			if (judge[0] != -1) {
/*				result = judge;
				break;*/
				return judge;
			}
			i--;
		}
		if (judge[0] == -1) {
			judge[0] = posX1 - 5 + (int) (Math.random() * 10);
			judge[1] = posY1 - 5 + (int) (Math.random() * 10);
			String[][] board = chessboard.getBoard();
			while (board[judge[0]][judge[1]] != "十" || !isInTheBoard(judge[0], judge[1])) {
				judge[0] = posX1 - 5 + (int) (Math.random() * 10);
				judge[1] = posY1 - 5 + (int) (Math.random() * 10);
			}
		}

		return judge;
	}
	
	public int[] judgyRoundWin(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard();
		int[] result = new int[2];
		int[] midValue1 = new int[2];
		int[] midValue2 = new int[2];
		int pointer1;
		int pointer2;
	   if(nums==1){
			result[0]=posX;
			result[1]=posY-1;
			return result;
		}
		int count = 1;
		for(pointer1=posY+1;pointer1<posY+nums;pointer1++){
			  if(pointer1<Chessboard.BOARD_SIZE-1 && pointer1 > -1 && board[posX][pointer1].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue1[0]=posX;
				  midValue1[1]=pointer1+1;
			  }
			  else {
					break;
				}
		}
		for(pointer2=posY-1;pointer2>posY-nums;pointer2--){
			 if(pointer2<Chessboard.BOARD_SIZE-1 && pointer2 > -1 && board[posX][pointer2].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue2[0]=posX;
				  midValue2[1]=pointer2-1;
			  }
			  else {
					break;
				}
		}	
		if (count==nums) {
			if (isValidCompute(midValue2[0],midValue2[1])) {
				result[0]=midValue2[0];
				result[1]=midValue2[1];
				
			} else if(isValidCompute(midValue1[0], midValue1[1])){
                result[0]=midValue1[0];
                result[1]=midValue1[1];
			}
			   else {
				   result[0]=-1;
				   result[1]=-1;
			   }
		}
		else {
			   result[0]=-1;
			   result[1]=-1;
		}		
		return result;
		
	}
	
    public int[] judgeRowWin(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard();
		int[] result = new int[2];
		int[] midValue1 = new int[2];
		int[] midValue2 = new int[2];
		int pointer1;
		int pointer2;
		int count = 1;
		
		for(pointer1=posX+1;pointer1<posX+nums;pointer1++){
			  if(pointer1<Chessboard.BOARD_SIZE && pointer1 > -1 && board[pointer1][posY].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue1[0]= pointer1 +1;
				  midValue1[1]= posY;
			  }
			  else {
				break;
			}
		}
		for(pointer2=posX-1;pointer2>posX-nums;pointer2--){
			 if(pointer2<Chessboard.BOARD_SIZE-1 && pointer2 > -1 && board[pointer2][posY].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue2[0]=pointer2-1;
				  midValue2[1]=posY;
			  }
			  else {
					break;
				}
		}
		
		if (count==nums) {
			if (isValidCompute(midValue1[0],midValue1[1])) {
				result[0]=midValue1[0];
                result[1]=midValue1[1];
			} else if(isValidCompute(midValue2[0], midValue2[1])){
				result[0]=midValue2[0];
				result[1]=midValue2[1];
			}
			   else {
				   result[0]=-1;
				   result[1]=-1;
			   }
		}
		else {
			   result[0]=-1;
			   result[1]=-1;
		}		
		return result;
    }
	
	public int[] judgeSlashWin(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard();
		int[] result = new int[2];
		int[] midValue1 = new int[2];
		int[] midValue2 = new int[2];
		int pointer1;
		int pointer2;
		int count = 1;
		
		for(pointer1=posX-1,pointer2=posY+1;pointer1>posX-nums&&pointer2<posY+nums;pointer1--,pointer2++){
			  if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue1[0]=pointer1-1;
				  midValue1[1]=pointer2+1;
			  }
			  else {
					break;
				}
		}
		for(pointer1=posX+1,pointer2=posY-1;pointer1<posX+nums&&pointer1>posX-nums;pointer1++,pointer2--){
			 if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(Chessman.BLACK.getChessman())){
				 count++;
				 midValue2[0]=pointer1+1;
				 midValue2[1]=pointer2-1;
			  }
			  else {
					break;
				}
		}
		
		if (count==nums) {
			if (isValidCompute(midValue2[0],midValue2[1])) {
				result[0]=midValue2[0];
				result[1]=midValue2[1];
			} else if(isValidCompute(midValue1[0], midValue1[1])){
				result[0]=midValue1[0];
                result[1]=midValue1[1];
			}
			   else {
				   result[0]=-1;
				   result[1]=-1;
			   }
		}
		else {
			   result[0]=-1;
			   result[1]=-1;
		}		
		return result;
	}
	
	public int[] jedgeSlash2(int posX,int posY,int nums){
		String[][] board = chessboard.getBoard();
		int[] result = new int[2];
		int[] midValue1 = new int[2];
		int[] midValue2 = new int[2];
		int pointer1;
		int pointer2;
		int count = 1;
		
		for(pointer1=posX-1,pointer2=posY-1;pointer1>posX-nums&&pointer2>posY-nums;pointer1--,pointer2--){
			  if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue1[0]=pointer1-1;
				  midValue1[1]=pointer2-1;
			  }
			  else {
					break;
				}
		}
		for(pointer1=posX+1,pointer2=posY+1;pointer1<posX+nums&&pointer1<posX+nums;pointer1++,pointer2++){
			 if(isInTheBoard(pointer1, pointer2)&& board[pointer1][pointer2].equals(Chessman.BLACK.getChessman())){
				  count++;
				  midValue2[0]=pointer1+1;
				  midValue2[1]=pointer2+1;
			  }
			  else {
					break;
				}
		}
		
		
		if (count==nums) {
			if (isValidCompute(midValue2[0],midValue2[1])) {
				result[0]=midValue2[0];
				result[1]=midValue2[1];
			} else if(isValidCompute(midValue1[0], midValue1[1])){
				result[0]=midValue1[0];
                result[1]=midValue1[1];
			}
			   else {
				   result[0]=-1;
				   result[1]=-1;
			   }
		}
		else {
			   result[0]=-1;
			   result[1]=-1;
		}		
		return result;
	}
	
	
	
	
	
	//判断机器所要下的值是否是合法的
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
	}
	
	

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
	
/*	public boolean judegyWin(List<Integer> contained){
		if(contained.size()==WIN_COUNT){
			return true;
		}
		else	
		    return false;
	}*/
	
/*	public boolean judgyWon(List list){
		if(list.size()==WIN_COUNT){
			return true;
		}
		else{
			list.clear();
			return false;
		}		
	}*/

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
