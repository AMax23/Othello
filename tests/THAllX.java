package tests;
import logic.Player;

/**
 * THAllX.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Test class designed to ensure that the game ending conditions are recognized.
 * This file is an OPTIONAL part of the game Othello.
 */

public class THAllX{
	
	/**
	 * Creates a printer to display the board in the slow version. Test player to modify the board.
	 */
  	private TestHubBoardPrinter board = new TestHubBoardPrinter();
	private Player testPlayer = new Player();
	
	/**
	 * A manually created board containing no valid moves for either player.
	 */
  	private String[][] testBoard = {
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{" X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "}
	};

	/**
	 * Ensures game ends properly upon full board.
	 * <p>
	 * The detailed version of the test. Passes the test board through the hasMoves function, expecting
	 * the result to be false. Provides detailed information to the user about the results of the test.
	 */
  	public void slow(){
		System.out.println("|THAllX - Version 1");
		System.out.println("|Ensures game ends properly upon full board.");

  	    System.out.println("\nOriginal Test Board: ");
  	    board.printBoard(testBoard);
		System.out.printf("\nGame Still Running? Expecting 'false' : %s\n",testPlayer.hasMoves("X",testBoard));
  	    boolean testResult = testPlayer.hasMoves("X",testBoard);

  	    if(!testResult){
			System.out.println("------------");
			System.out.println("THAllX: PASS");
			System.out.println("------------");
  	    }
  	    else {
			System.out.println("------------");
			System.out.println("THAllX: FAIL");
			System.out.println("------------");
  	    }
  	}

	/**
	 * The fast version for the test. Functionally identical to the slow version,
	 * 
	 * @return 	A boolean representing whether the test passed or not.
	 */		 	
  	public boolean fast(){
		boolean testPassed = false;
		testPassed = !testPlayer.hasMoves("X",testBoard);
		return testPassed;
	}
}
