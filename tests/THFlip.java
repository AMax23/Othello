package tests;
import logic.Board;

/**
 * THFlip.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * A test class designed to ensure that pieces on the board are actually flipped when a valid move is made.
 * This file is an OPTIONAL part of the game Othello.
 */

public class THFlip{

	/**
	 * Creates the objects necessary to run the tests.
	 **/
  	private TestHubBoardPrinter board = new TestHubBoardPrinter();
	private Board game = new Board();
		
	/**
	 * A manually created board that allows pieces to be flipped in all directions
	 * after a single move. 
	 */
	private String[][] testBoard = {
	{" X ", " X ", " O ", " X ", " X ", "   ", "   ", " X "},
	{"   ", " O ", "   ", " O ", " O ", "   ", " O ", " O "},
	{"   ", "   ", " O ", "   ", " O ", " O ", " X ", " O "},
	{"   ", "   ", "   ", " O ", " O ", " O ", " O ", " X "},
	{" X ", " O ", " X ", " O ", "   ", " O ", " O ", "   "},
	{"   ", "   ", " O ", " O ", " O ", " O ", "   ", "   "},
	{" O ", " O ", " O ", " O ", " X ", "   ", " O ", "   "},
	{" X ", " X ", "   ", " X ", "   ", "   ", "   ", " X "},
	};
	
	/**
	 * Another manually created board which represents what the previous
	 * board should look like after the move was made.
	 */
	private String[][] expectedBoard = {
	{" X ", " X ", " O ", " X ", " X ", "   ", "   ", " X "},
	{"   ", " X ", "   ", " O ", " X ", "   ", " O ", " O "},
	{"   ", "   ", " X ", "   ", " X ", " O ", " X ", " O "},
	{"   ", "   ", "   ", " X ", " X ", " X ", " O ", " X "},
	{" X ", " O ", " X ", " X ", " X ", " O ", " O ", "   "},
	{"   ", "   ", " O ", " X ", " X ", " X ", "   ", "   "},
	{" O ", " O ", " X ", " O ", " X ", "   ", " X ", "   "},
	{" X ", " X ", "   ", " X ", "   ", "   ", "   ", " X "},
	};

	/**
	 * Ensures the piece-flipping system works.
	 * <p>
	 * A detailed version of the test which prints all pertinent information to the screen for the user.
	 * If the expectedBoard is identical to the testBoard after the move was made on it, the test is 
	 * passed.
	 */
  	public void slow(){
		System.out.println("|THFlip - Version 1");
		System.out.println("|Ensures piece-flipping in all directions matches expected outcome. ");

  	    System.out.println("\nOriginal Test Board: ");   
		board.printBoard(testBoard);
		
		System.out.println("\nModified Test Board: ");
		game.flipPieces("X",4,4,testBoard,false);
		board.printBoard(testBoard);
		
		System.out.println("\nExpected Modified Test Board: ");
		board.printBoard(expectedBoard);

		boolean testPassed = true;

		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if(testBoard[row][col] != expectedBoard[row][col]){
					System.out.println("\n------------");
					System.out.println("THFlip: FAIL");
					System.out.println("------------");
					testPassed = false;
				}
			}
		}
		if (testPassed){
			System.out.println("\n------------");
			System.out.println("THFlip: PASS");
			System.out.println("------------");
		}
	}

	/**
	 * The fast version for the test. Functionally identical to the slow version,
	 * 
	 * @return 	A boolean representing whether the test passed or not.
	 */	
	public boolean fast(){
		boolean testPassed = true;
		game.flipPieces("X",4,4,testBoard,false);
		
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if(testBoard[row][col] != expectedBoard[row][col]){
					testPassed = false;
				}
			}
		}
		return testPassed;
	}		
}
