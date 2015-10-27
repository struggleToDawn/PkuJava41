package wuziqi;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
			//System.out.println(chessman);
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
			//System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
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
	public int[] computerDo(int posX1,int posY1) {
		/*
		 * int posX = posX1-5+(int) (Math.random() * 10); int posY =
		 * posY1-5+(int) (Math.random() * 10); String[][] board =
		 * chessboard.getBoard(); while (board[posX][posY] !=
		 * "ʮ"||!isInTheBoard(posX, posY)) { posX = posX1-5+(int) (Math.random()
		 * * 10); posY = posY1-5+(int) (Math.random() * 10); }
		 */
		int[] judge = new int[2];
		int i = 4;// ��¼���е��������ɵĸ���
		while (i > 0) {
			judge = judgyRoundWin(posX1, posY1, i);// �жϺ����Ƿ���i���������ӵ����
			if (judge[0] != -1) {
				return judge;
			}	
			judge = judgeRowWin(posX1, posY1, i);// �ж������Ƿ���i���������ӵ����
			if (judge[0] != -1) {
				return judge;
			}	
			judge = judgeSlashWin(posX1, posY1, i);	// �ж϶�������б���Ƿ���i���������ӵ����
			if (judge[0] != -1) {
		     /*	result = judge;
				break;*/
				return judge;
			}
			judge = jedgeSlash2(posX1, posY1, i);// �ж�һ������б���Ƿ���i���������ӵ����
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
			while (board[judge[0]][judge[1]] != "ʮ" || !isInTheBoard(judge[0], judge[1])) {
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
	
	
	
	
	
	//�жϻ�����Ҫ�µ�ֵ�Ƿ��ǺϷ���
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
	}
	
	

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
