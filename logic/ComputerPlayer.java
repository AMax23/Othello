package logic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * ComputerPlayer.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class handles all actions made by the CPU player.
 * This file is an ADDITION to the game Othello.
 */
public class ComputerPlayer extends Player{

	/**
	 * Gets valid moves for CPU, stores them in a list, and at the end randomly plays a valid move for the CPU.
	 * 
	 * @param board		A String[][] that is pulled to check for valid moves for the CPU.
	 * @return			A modified board with the "O" piece played by the CPU.
	 */
	public String [][] easyAI (String [][] board) {
		board = getValidMoves("O",board);
	
		// Makes a list for possible moves.
		List <String> possibleMoves = new ArrayList<String>();
		possibleMoves = findValidMoves(board);
	
		/* If there are valid moves available then a random number is generated and used to get the
		 * index of possible moves. From the randomly chosen index, the two numbers (row and col)
		 * are converted to int, and that move is played for CPU, flipping any pieces that we can.
		 */
		if (possibleMoves.size() > 0){
			Random generator = new Random();
			int randomPosition = generator.nextInt(possibleMoves.size());
			String stringPosition = possibleMoves.get(randomPosition);
			placePiece(stringPosition, board);
		}
		return board;
	}

	/**
	 * Gets valid moves for CPU, stores them in a list, then makes a "smart" play.
	 * 
	 * @param board		A String[][] that is pulled to check for valid moves for the CPU.
	 * @return 			A modified board with the "O" piece played by the "smart" CPU.	
	 */
	public String [][] hardAI(String [][] board){
		board = getValidMoves("O",board);

		// Makes a list for possible moves.
		List <String> possibleMoves = new ArrayList<String>();
		possibleMoves = findValidMoves(board);

		if (possibleMoves.size() > 0){
			// First move, try to go for one of the four corners.
			String goodMove = findCornerMoves(possibleMoves,board);

			if (!goodMove.equals(" ")){
				placePiece(goodMove,board);

			/* If corner move is not open, then their it will go through the statements according to their
			 * priority and play that move if it is possible. If no "good move" is possible then
			 * it will just go for the move that flips the most pieces.
			 */
			}
			else if (goodMove.equals(" ")){

				goodMove = findCenterCorners(possibleMoves,board);
				if (!goodMove.equals(" "))
					placePiece(goodMove,board);

				else if (goodMove.equals(" ")){

					goodMove = selectBestMove(possibleMoves,board);
					if (!goodMove.equals(" "))
						placePiece(goodMove,board);

					else if (goodMove.equals(" ")){

						goodMove = dontGiveOpponentCorners(possibleMoves,board);
						if (!goodMove.equals(" "))
							placePiece(goodMove,board);

						else if (goodMove.equals(" ")){
							
							goodMove = canSkipTurn(possibleMoves,board);
							
							if (!goodMove.equals(" ")){
								placePiece(goodMove,board);
							}
							else if (goodMove.equals(" ")){
								
								goodMove = findCloseCorners(possibleMoves,board);
								if (!goodMove.equals(" ")){
									placePiece(goodMove,board);
								}
								else {
									goodMove = maxPiecesFlipped(possibleMoves,board);
									placePiece(goodMove,board);
								}
							}
						}
					}
				}
			}
		}
		return board;
	}

	/**
	 * Makes a copy of the String[][] copy that can be tested with.
	 * 
	 * Taken from: http://stackoverflow.com/questions/1686425/copy-a-2d-array-in-java
	 * 
	 * @param board		A String[][] to be "cloned"
	 * @return			The cloned copy ofthe board.
	 */
	private String [][] cloneBoard(String [][] board){

		String [][] testBoard = new String[board.length][];
		for(int i = 0; i < board.length; i++)
		    testBoard[i] = board[i].clone();
		return testBoard;
	}

	/**
	 * Loops through the valid moves available from the list and plays the move that gains the most points.
	 * 
	 * @param possibleMoves		A list representing all the possible moves a CPU can play.
	 * @param board				A String[][] that is evaluated for the highest-scoring move.
	 * @return 					A string representing a move that flips the most pieces.
	 */
	private String maxPiecesFlipped(List <String> possibleMoves, String [][] board){
		int score = -1;
		String goodMove = " ";
		String checkMove = " ";

		for (int move = 0; move < possibleMoves.size(); move++){

			String [][] testBoard = cloneBoard(board);
			checkMove = possibleMoves.get(move);
			placePiece(checkMove,testBoard);

			// Counts the score to see if more pieces are flipped.
			if (gameBoard.scoreCount(testBoard,"O") > score){
				goodMove = checkMove;
				score = gameBoard.scoreCount(testBoard, "O");
			}
		}
		return goodMove;
	}

	/**
	 * Takes a string of two-length, splits them into integers, and plays a move based on the integers.
	 * 
	 * @param move		A string representing a move that flips the most pieces.
	 * @param board		A String[][] representing the game board.
	 */
	private void placePiece(String move,String [][] board){
		
		int row = Integer.parseInt(move.substring(0,1));
		int col = Integer.parseInt(move.substring(1));
		board[row][col] = " O ";

		// Flips the pieces after placing computer piece
		gameBoard.flipPieces("O",row,col,board,false);

	}

	/**
	 * Plays a corner move that produces the best results.
	 * <p>
	 * The first move that is checked when making AI move. loops through the list of moves
	 * and sees if any of them are one the four corners. If multiple corners are available then checks
	 * which one flips the most pieces.
	 * 
	 * @param possibleMoves		A list containing all the possible moves the CPU can make.
	 * @param board				A String[][] representing the gameboard.
	 * @return 					A string containing the values for the best corner move.
	 */
	private String findCornerMoves(List <String> possibleMoves,String[][] board){

		String goodMove = " ";
		// Will store the corner move if any are found.
		List <String> cornerMoves = new ArrayList<String>();

		for (int move = 0; move < possibleMoves.size(); move++){
			if (possibleMoves.get(move).equals("00") || possibleMoves.get(move).equals("07") ||
				possibleMoves.get(move).equals("70") || possibleMoves.get(move).equals("77")){
				cornerMoves.add(possibleMoves.get(move));
			}
		}
		goodMove = maxPiecesFlipped(cornerMoves,board);
		return goodMove;
	}

	/**
	 * Scans the board for *s and adds the row and col position to the list of possible moves as a string.
	 * 
	 * @param board		A gameboard that is scanned for valid moves. (*s)
	 * @return 			A list containing all the valid moves the CPU can make.
	 */
	private List <String> findValidMoves(String [][] board){
		List <String> possibleMoves =  new ArrayList<String>();

		// Loops through the lists and it there's a (valid move) the row and col positions are converted to String and added to the list.
		for (int row = 0; row < 8; row++){

			for (int col = 0; col < 8; col++){

				if (board[row][col] == " * "){
					String stringRow = Integer.toString(row);
					String stringCol = Integer.toString(col);
					possibleMoves.add(stringRow+stringCol);
				}
			}
		}
		return possibleMoves;
	}

	/**
	 * Tries to find moves so that when placed, the opponent's turn will be skipped.
	 * 
	 * @param possibleMoves		A list of possible moves the CPU can play.
	 * @param board				A String[][] representing the gameboard.
	 * @return 					The best move the CPU can make to cripple its opponent.
	 */
	private String canSkipTurn(List <String> possibleMoves,String [][] board){
		// Initialized to empty string just in case no valid move is found during the check
		String goodMove = " ";

		/* Loops through the list of possible moves, and places pieces on the test board
		 * then checks the board with valid moves for opponent and sees if opponent turn can
		 * be skipped. If possible, stores the move in the variable called goodMove.
		 */
		for (int move = 0; move < possibleMoves.size(); move++){

			String testBoard [][] = cloneBoard(board);
			placePiece(possibleMoves.get(move),testBoard);
			testBoard = getValidMoves("X",testBoard);

			if (findValidMoves(testBoard).size() == 0){
				goodMove = possibleMoves.get(move);
			}
		}
		return goodMove;
	}

	/**
	 * Goes through the possible moves list and sees which of those are better moves and picks a good one if possible.
	 * 
	 * @param possibleMoves		A list containing the possible moves the CPU can make.
	 * @param board				A String[][] representing the gameboard.
	 * @return 					A string with either a good move to play, or nothing.
	 */
	private String selectBestMove(List <String> possibleMoves,String [][] board){

		String[] movesToAvoid = {"10", "01", "11","06", "16","17", "60", "61","71", "66", "67","76"};
		String goodMove = " ";
		List <String> goodMoves = new ArrayList<String>();

		for (int move = 0; move < possibleMoves.size(); move++){

			if ( !(Arrays.asList(movesToAvoid).contains(possibleMoves.get(move))) )
				goodMoves.add(possibleMoves.get(move));

		}
		goodMove = maxPiecesFlipped(goodMoves,board);
		return goodMove;
	}

	/**
	 * Finds and plays a center move if possible.
	 * 
	 * @param possibleMoves		A list representing all the possible moves the CPU can play.
	 * @param board				A String[][] representing the gameboard.
	 * @return 					A string with either a good move to play, or nothing.
	 */
	private String findCenterCorners(List <String> possibleMoves, String[][] board){

		String[] centerMoves = {"52", "22", "25","55"};
		String goodMove = " ";
		List <String> goodMoves = new ArrayList<String>();

		for (int move = 0; move < possibleMoves.size(); move++){
			if ((Arrays.asList(centerMoves).contains(possibleMoves.get(move))))
				goodMoves.add(possibleMoves.get(move));
		}
		goodMove = maxPiecesFlipped(goodMoves,board);
		return goodMove;
	}

	/**
	 * Predefined moves. Should be played when possible.
	 * 
	 * @param possibleMoves		A list containing all the possible moves the CPU can play.
	 * @param board				A String[][] representing the gameboard.
	 * @return					A string with either a good move to play, or nothing.
	 */
	private String findCloseCorners(List <String> possibleMoves,String [][] board){
		String[] centerCornerMoves = {"02", "05", "20","27","50", "57", "72","75"};
		String goodMove = " ";
		List <String> goodMoves = new ArrayList<String>();

		for (int move = 0; move < possibleMoves.size(); move++){
			if ((Arrays.asList(centerCornerMoves).contains(possibleMoves.get(move))))
				goodMoves.add(possibleMoves.get(move));
		}
		goodMove = maxPiecesFlipped(goodMoves,board);
		return goodMove;
	}

	/**
	 * Avoid moves that would give the human player a corner piece.
	 * 
	 * @param possibleMoves		A list containing all the possible moves the CPU can play.
	 * @param board				A String[][] representing the gameboard.
	 * @return					A string with either a good move to play, or nothing.
	 */
	private String dontGiveOpponentCorners(List <String> possibleMoves,String [][] board){
		String goodMove = " ";

		/* Loops through the possible moves, makes a copy of the board, places the piece and checks whether
		 * it would give player one of the four corners.
		 */
		for (int move = 0; move < possibleMoves.size(); move++){

			String copyBoard [][] = cloneBoard(board);
			placePiece(possibleMoves.get(move),copyBoard);
			copyBoard = getValidMoves("X",copyBoard);

			if ( (copyBoard[0][0] != " * ") && (copyBoard[0][7] != " * ") && (copyBoard[7][0] != " * ") && (copyBoard[7][7] != " * ") )
				goodMove = possibleMoves.get(move);

		}
		return goodMove;
	}
}
