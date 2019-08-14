package tests;
/**
 * TestHubBoardPrinter.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class is frequently used to print a tamed version of the gameboard.
 * This file is an OPTIONAL part of the game Othello.
 */
public class TestHubBoardPrinter{

	/**
	 * Prints a simplified board for testing.
	 * 
	 * @param board		A String[][] representing the gameboard.
	 * @return board	A String[][] representing the gameboard.
	 */		
	public String[][] printBoard(String[][] board){		
		System.out.println("      | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |");
		String line =      "     -+---+---+---+---+---+---+---+---+-";
		
		System.out.println(line);
		for ( int row = 0; row < 8; row++) {
			System.out.printf("    %d",row+1);
			System.out.print(" |");
			for (int column = 0; column < 8; column++) {
				System.out.print(board[row][column] + "|");
			}
			System.out.println("\n"+line);
		}
		return board;
	}
}
