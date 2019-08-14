package logic;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Board.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Purpose of this class is to handle the logic behind the game board.
 * This file is a CORE part of the game Othello.
 */
public class Board {
	
	/**
	 * An 8x8 2D array consisting of 3 characters. Each index represents a cell in the game.
	 */
	private static String [][] board = 	{
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "}
	};

	/**
	 * Constructor method. Allows other classes to refer to board.
	 */
	public Board(){
	}

	/**
	 * Constructor method. Initializes the pieces.
	 */
	public Board(boolean inBool){
		
		if(inBool){
			board[3][3] = " O ";
			board[3][4] = " X ";
			board[4][3] = " X ";
			board[4][4] = " O ";
		}
	}
	
	/**
	 * Wipes the String[][] representing the game, and places pieces in default Othello position.
	 */
	public void resetBoard(){
		for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++){
				board[row][col] = "   ";
			}
		}
		board[3][3] = " O ";
		board[3][4] = " X ";
		board[4][3] = " X ";
		board[4][4] = " O ";
	}
		
	/**
	 * When called, returns the gameboard.
	 * 
	 * @return	String[][] representing the game board.
	 */
	public String [][] getBoard(){
		return board;
	}

	/**
	 * When called, it overrides the game board with a different 8x8 board.
	 * 
	 * @param inBoard	A String[][] representing the desired replacement board.
	 */
	public void setBoard(String[][] inBoard){
		for (int loadRow = 0; loadRow < 8; loadRow++){
			for (int loadCol = 0; loadCol < 8; loadCol++){
				board[loadRow][loadCol] = inBoard[loadRow][loadCol];
			}
		}
	}

	/**
	 * When called, displays a user-side game screen containing score and gameboard.
	 * 
	 * @param board		The String[][] that should be printed into a presentable format.
	 */
	public void printBoard(String [][] board) {

		int pointsX = scoreCount(board,"X");
		int pointsO = scoreCount(board,"O");
		String line = "     -+---+---+---+---+---+---+---+---+-";
		System.out.println("\n  |-------------OTHELLO SCORE-------------|");
		System.out.printf( "       Player 1: [%d] | Player 2: [%d]\n\n",pointsX,pointsO);
		System.out.println("      | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |");
		System.out.println(line);

		for (int row = 0; row < 8; row++){
			System.out.printf("    %d",row+1);
			System.out.print(" |");
			
			for (int column = 0; column < 8; column++) 
				System.out.print(board[row][column] + "|");
			System.out.println("\n"+line);
		}
	}

	/**
	 * Determines the score value of an individual player.
	 * 
	 * @param board		The String[][] to be scanned for the player's piece.
	 * @param player	A String representing the character to scan.
	 * @return 			int representing the number of player pieces found on the board.
	 */
	public int scoreCount(String [][] board,String player){

		int points = 0;

		if (player.equals("O")){
			player = " O ";
		}
		else {
			player = " X ";
		}
		
		for (int row = 0; row < 8; row++){
			for (int col = 0; col<8; col++){
				if (board[row][col].equals(player)){
					points++;
				}
			}
		}
		return points;
	}

	/**
	 * Determines whether the two values exist on the board.
	 * 
	 * @param row	int representing the row value of String[][] in descending order.
	 * @param col	int representing the column value of String[][] from left-to-right.
	 * @return		true if the value is on the board. false otherwise.
	 */
	public boolean isOnBoard(int row, int col) {
		boolean onBoard = ((row >= 0 && col >= 0) && (row <= 7 && col <= 7)) ;
		return onBoard;
	}
	
	/**
	 * Checks the grid for pieces to flip if possible. Total of 8 directions that need to be checked.
	 * 
	 * @param yourPiece		A string representing either Player 1 (X) or Player 2 (O).
	 * @param row			The row position on a String[][] in descending order.
	 * @param col			The column position on a String[][] from left-to-right.
	 * @param board			A String[][] representing the gameboard.
	 * @param check			Determines whether the method is checking or placing pieces.
	 * @return 				A boolean used to show if valid moves are available for the player.
	 */
	public boolean flipPieces(String yourPiece,int row, int col,String [][] board,boolean check) {
		boolean validMove = false;
		String opponent;

		if (yourPiece.equals("X")){
			opponent = " O ";
			yourPiece = " X ";

		}
		else{
			opponent = " X ";
			yourPiece = " O ";
		}

		int rowStart = row;
		int colStart = col;

		// Main Loop variable. The only way this turns false is when the loop goes through each direction in the list. This way, no directions are skipped.
		boolean stillChecking = true;

		// No other significance than to store the iteration number. Used in the if statement to calculate which direction to check.
		int i = 0;

		/* Increments for X and increments for Y. Default value set to 0 (no specific reason because it will get changed anyways).
		 * These values are changed when different parts of the board is being checked.
		 */
		int incX = 0;
		int incY = 0;

		// Main loop. After checking and flipping pieces in one direction this makes sure that the rest of the possibilities (horizontal,vertical,...etc) are convered.
		while (stillChecking) {

			/* This while loops makes sure that all the if statements get executed. This is necessary to check all 8 directions.
			 * The only way the main while loop ends is when the boolean variable stillChecking turns false and that is in the last if statement.
			 */
			while (i<8) {

				row = rowStart;
				col = colStart;

				// Vertical Checks up
				if (i==0){
					i++;
					incX = -1;
					incY = 0;

				// Horizontal Checks right
				}else if (i == 1){
					i++;
					incX = 0;
					incY = 1;

				// Diagonal-Right Check upwards
				}else if (i == 2){
					i++;
					incX = -1;
					incY = 1;

				// Diagonal-Left Checks upwards
				}else if (i ==3){
					i++;
					incX = -1;
					incY = -1;

				// Vertical Check. Goes down
				}else if (i==4){
					i++;
					incX = +1;
					incY = 0;

				// Horizontal Check. Goes left
				}else if (i == 5){
					i++;
					incX = 0;
					incY = -1;

				// Diagonal-Right check. Goes downwards diagonally
				}else if (i == 6){
					i++;
					incX = +1;
					incY = -1;

				// Diagonal-Left Check. Goes down.
				}else if (i ==7){
					i++;
					incX = +1;
					incY = +1;
					stillChecking = false;
					}


			if (isOnBoard(row + incX,col + incY) && (board[row + incX][col + incY].equals(opponent))) {

				while (true) {

					col = col + incY;
					row = row + incX;

					// This is just in case the piece doesn't go out of bounds. If it does the loop breaks. And there is no need to continue further.
					if (!isOnBoard(row,col))
						break;

					// If the next piece is not the opponent's piece then there is no need to keep incrementing and checking the grid.
					if (!board[row][col].equals(opponent))
						break;

					// Will break out of loop if player's piece is found. And will then start flipping
					if (board[row][col].equals(yourPiece))
						break;
				}

				//This is used to actually flip the pieces. This time we go in reverse because we already know that the pieces are oppenent's pieces.
				if (isOnBoard(row,col) && board[row][col].equals(yourPiece)) {

					while (true) {

						// Starts going in reverse.
						row = row + (-1)*incX;
						col = col + (-1)*incY;

						// This boolean variable is only used when getting valid moves. Used for placing * on game board.
						validMove = true;

						// Will only flip the pieces if player or computer make the move (check = false). Not when placing *'s on board (check = true)
						if (check == false)
							board[row][col] = yourPiece;


						// If it reaches back to the original position, then it will exit the loop.
						if (col == colStart && row == rowStart)
							break;

					}
				}
			}
		}
	}
		return validMove;
	}
	
	/**
	 * Compares the two player's scores. Declares winner to the person with more points.
	 * 
	 * @param board		A String[][] representing the gameboard.
	 */
	public void appointWinner(String [][] board){
		
		Player player = new Player();

		if (!player.hasMoves("O",board) && !player.hasMoves("X",board)){

			// Player 1 wins.
		 	if (scoreCount(board,"X") > scoreCount(board,"O"))
		 		System.out.printf("\nGAME OVER: Player 1 wins with %d points!\n",scoreCount(board,"X"));

		 	// CPU wins.
		 	else if (scoreCount(board,"X") < scoreCount(board,"O"))
		 		System.out.printf("\nGAME OVER: Player 2 wins with %d points!\n",scoreCount(board,"O"));

		 	// Draw game.
		 	else
		 		System.out.printf("\nGAME OVER: Tie! Score of %d points!\n",scoreCount(board,"X"));

		}
	}

	/**
	 * Saves the board and player's turn to an external text file.
	 * 
	 * @param playerTurn	String representing the player's turn. "X" or "O".
	 */
	public void saveBoard(String playerTurn){
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter (new FileOutputStream("SAVESTATE.txt"));
		}
		catch (IOException e){
			System.out.println("There was an error opening the file.");
		}

		for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++){
				outputStream.printf(board[row][col]);
				outputStream.println("");
			}
		}
		outputStream.printf(playerTurn);
		outputStream.println("");
		System.out.println("Successfully wrote to SAVESTATE.txt !");
		outputStream.close();
	}
	
	/**
	 * Loads the board and player's turn from an external text file.
	 * 
	 * @param inPlayer	String representing the player's turn. "X" or "O".
	 * @param player	A logical component that determines possible actions for the player. Used to determine valid moves.
	 */
	public String loadBoard(String inPlayer, Player player){
		Scanner inputStream = null;
		
		try {
			inputStream = new Scanner (new File("SAVESTATE.txt"));
		}
		catch (FileNotFoundException exception){
			System.out.println("There was an error opening the file.");
		}
		
		String[][] loadedBoard = {
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
		{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "}};
		
		String playerTurn = "";
		while (inputStream.hasNextLine()){
			
			for (int loadRow = 0; loadRow < 8; loadRow++){
				for (int loadCol = 0; loadCol < 8; loadCol++){
					loadedBoard[loadRow][loadCol] = inputStream.nextLine();
				}
			}
		playerTurn = inputStream.nextLine();	
		}
		
		// Prepares terminal version for updated board.
		printBoard(loadedBoard);
		setBoard(loadedBoard);
		player.getValidMoves(inPlayer,getBoard());
		printBoard(getBoard());
		
		System.out.println("Successfully loaded state from SAVESTATE.txt !");
		inputStream.close();
		
		return playerTurn;
	}
	
}
