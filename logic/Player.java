package logic;
import java.util.Scanner;

/**
 * Player.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class represents player-related logic.
 * This file is a CORE part of the game Othello.
 */
public class Player {
	
	protected Board gameBoard = new Board();

	/** 
	 * When called, lets player play their turn, by placing a piece on the board.
	 * <p>
	 * playerTurn is used by the TERMINAL version of Othello. It only receives keyboard
	 * input, and does its own error checking.
	 * 
	 * @param player	A string representing which players turn it is.
	 * @param board		A String[][] representing the gameboard.
	 * @return			A String[][] representing the gameboard after a piece is played.
	 */
	public String [][] playerTurn(String player,String [][] board) {

		// Determines the current player's piece.
		String opponent = "";
		String playerPiece = "";
		int playerNum;

		if (player.equals("X")){
			opponent = " O ";
			playerPiece = " X ";
			playerNum = 1;

		}
		else {
			opponent = " X ";
			playerPiece = " O ";
			playerNum = 2;
		}

		boolean notPlayed = true;
		Scanner keyboard = new Scanner (System.in);

		// Prompts for valid user input.
		while (notPlayed) {
			System.out.printf("\nP%d: Enter ROW position to place '%s' piece: ",playerNum,player);
			String row = keyboard.nextLine();
			System.out.printf("P%d: Enter COLUMN position to place '%s' piece: ",playerNum,player);
			String col = keyboard.nextLine();
			
			if(row.equals("SAVE") && col.equals("SAVE")){
				gameBoard.saveBoard(player);
			}

			else if(row.equals("LOAD") && col.equals("LOAD")){
				player = gameBoard.loadBoard(player, this);
				System.out.printf(player);
				if (player.equals("X")){
					opponent = " O ";
					playerPiece = " X ";
					playerNum = 1;
				}
				else {
					opponent = " X ";
					playerPiece = " O ";
					playerNum = 2;
				}
				getValidMoves(player, gameBoard.getBoard());
				gameBoard.printBoard(gameBoard.getBoard());
			}
			
			else if (gameBoard.isOnBoard(Integer.parseInt(row)-1,Integer.parseInt(col)-1)){
				int intRow = Integer.parseInt(row);
				int intCol = Integer.parseInt(col);
				if (board[intRow-1][intCol-1].equals(opponent)){
					System.out.println("\nERROR: Cannot override opponent's piece! Try again.");
				}
				else if (board[intRow-1][intCol-1] != " * "){
					System.out.println("\nERROR: Invalid move. Only '*' positions are valid. Try again.");
				}
				else {
					board[intRow-1][intCol-1] = playerPiece;
					// Flips the pieces in all directions
					gameBoard.flipPieces(player,intRow-1,intCol-1,board,false);
					notPlayed = false;

				}

			}
			else {
				System.out.println("\nERROR: Input must be within range 1-8.");
			}
		}
		return board;
	}

	/**
	 * Places a piece on the game board.
	 * <p>
	 * This method is a simplified version that requires no keyboard input for the
	 * GUI version of Othello. Preliminary piece-checking is already done.
	 * 
	 * @param player	A string representing the player's piece.
	 * @param board		A String[][] representing the gameboard.
	 * @param inRow		int input row value for the String[][] in descending order.
	 * @param inCol		int input column value for the String[][] from left-to-right.
	 * @return			Returns the gameboard after the turn has been played.
	 **/
	public String[][] placePiece(String player, String[][] board, int inRow, int inCol){
		
		//String opponent = "";
		String playerPiece = "";
		
		if (player == "X"){
			//opponent = " O ";
			playerPiece = " X ";
		}
		else {
			//opponent = " X ";
			playerPiece = " O ";
		}
		
		board[inRow][inCol] = playerPiece;
		gameBoard.flipPieces(player, inRow, inCol, board, false);
		return(board);
	}
	
	/**
	 * Checks the grid and puts *'s indicating valid moves for each player.
	 * 
	 * @param player	A string representing the player's piece.
	 * @param board		A String[][] representing the gameboard.
	 * @return board	A String[][] representing the gameboard with new valid moves dependent on the player.
	 */
	public String[][] getValidMoves(String player,String[][]board){
	
		// Scans the whole board for all the possible valid moves the player can make.
		for (int row = 0; row<8; row++){
			for (int col = 0; col<8; col++){
				// Clears the previous Stars (*) first
				if (board[row][col].equals(" * ")){
					board[row][col] = "   ";
				}
				if (board[row][col].equals("   ")){
					// Won't actually flip pieces, but just see if that could potentially flip pieces
					boolean validMove = gameBoard.flipPieces(player,row,col,board,true);
					if (validMove == true){
						board[row][col] = " * ";
					}
				}
			}
		}
		return board;
	}

	/**
	 * Checks the game if player turn is skipped.
	 * @param player	A string representing the player's piece.
	 * @param board		A String[][] representing the gameboard.
	 * @return			A boolean representing whether the player's turn is skipped or not.
	 */
	public boolean hasMoves(String player,String [][] board){

		// Initialized to false so if both players don't have valid moves the game ends
		boolean turn = false;
		board = getValidMoves(player,board);

		/* This loop checks the board for * for current player and if there are any *
		 * that means that the player has a turn and "player turn" is returned.
		 * Otherwise the turn is skipped.
		 */
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if(board[row][col] == " * "){
					turn = true;
				}
			}
		}
		return turn;
	}
}
