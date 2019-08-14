package tests;

import logic.Player;

/**
 * THDirectional.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * A test class designed to ensure that the valid move checker functions properly.
 * This file is an OPTIONAL part of the game Othello.
 */
public class THDirectional{


	/**
	 * Creates the objects necessary to run the test.
	 */
	private TestHubBoardPrinter board = new TestHubBoardPrinter();
	private Player testPlayer = new Player();
	

	/**
	 * A manually created board which contains 8 valid moves (one in every direction)
	 */
	private String[][] testBoard = {
	{" X ", "   ", "   ", " X ", "   ", "   ", "   ", " X "},
	{"   ", " O ", "   ", " O ", "   ", "   ", " O ", "   "},
	{"   ", "   ", "   ", "   ", "   ", " O ", "   ", "   "},
	{"   ", "   ", "   ", "   ", "   ", "   ", " O ", " X "},
	{" X ", " O ", "   ", "   ", "   ", "   ", "   ", "   "},
	{"   ", "   ", " O ", " O ", "   ", " O ", "   ", "   "},
	{" O ", " O ", "   ", " O ", "   ", "   ", " O ", "   "},
	{" X ", "   ", "   ", " X ", "   ", "   ", "   ", " X "},
	};

	/**
	 * Another manually created board representing what the testBoard should look like after
	 * being passed through the valid move checker.
	 */
	private String[][] expectedBoard = {
	{" X ", "   ", "   ", " X ", "   ", "   ", "   ", " X "},
	{"   ", " O ", "   ", " O ", "   ", "   ", " O ", "   "},
	{"   ", "   ", " * ", " * ", "   ", " O ", "   ", "   "},
	{"   ", "   ", "   ", "   ", " * ", " * ", " O ", " X "},
	{" X ", " O ", " * ", " * ", " * ", "   ", "   ", "   "},
	{" * ", "   ", " O ", " O ", "   ", " O ", "   ", "   "},
	{" O ", " O ", "   ", " O ", "   ", "   ", " O ", "   "},
	{" X ", "   ", "   ", " X ", "   ", "   ", "   ", " X "},
	};

	/**
	 * Checks to see if game correctly lists valid moves.
	 * <p>
	 * The detailed version of the test. Compares the testBoard to the expectedBoard after
	 * the former has been passed through the valid move checker. If the boards are identical,
	 * the test is passed. The boards are printed to make error identification easier.
	 */	
  	public void slow(){
		System.out.println("|THDirectional - Version 1");
		System.out.println("|Checks if game logic correctly lists valid moves.");
		System.out.println("|Each valid move corresponds with one direction.");
		
		// Uses game logic to produce a board using testBoard containing valid moves.
		String[][] testResult = testPlayer.getValidMoves("X", testBoard);
		
		// Prints the test board, modified test board, and expected modified test board.
  	    System.out.println("\nOriginal Test Board        :");
  	    board.printBoard(testBoard);
  	    System.out.println("\nModified Test Board (Valid):");
  	    board.printBoard(testResult);
  	    System.out.println("\nExpected Test Board (Valid):");
  	    board.printBoard(expectedBoard);
  	    System.out.print("\n");

  	    // Compares the modified test board to the expected modified test board.
  	    boolean loopControl = true;
  	    while (loopControl){
  	    	for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 8; c++) {
					if(testResult[r][c] != expectedBoard[r][c]){
						System.out.println("-------------------");
						System.out.println("THDirectional: FAIL");
						System.out.println("-------------------");
					}
				}
			loopControl = false;
			}
			System.out.println("-------------------");
			System.out.println("THDirectional: PASS");
			System.out.println("-------------------");
			loopControl = false;
  	    }
  	}

	/**
	 * The fast version for the test. Functionally identical to the slow version,
	 * 
	 * @return 	A boolean representing whether the test passed or not.
	 */	
	public boolean fast(){
		String[][] testResult = testPlayer.getValidMoves("X", testBoard);
		boolean testPassed = true;
		boolean loopControl = true;
  	    while (loopControl){
  	    	for(int r = 0; r < 8; r++) {
				for(int c = 0; c < 8; c++) {
					if(testResult[r][c] != expectedBoard[r][c]){
						testPassed = false;
					}
				}
			loopControl = false;
			}
			loopControl = false;
  	    }
		return testPassed;
  	}
}
