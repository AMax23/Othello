package tests;

import logic.Player;

/**
 * THP1InvalidP2Invalid.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * This is a test class to ensure that no invalid moves are displayed as valid for either player.
 * This file is an OPTIONAL part of the game Othello.
 */
public class THP1InvalidP2Invalid{

	/**
	 * Creates a new TestHubBoardPrinter object for use 
	 * by the slow() method.
	 */
  	private TestHubBoardPrinter board = new TestHubBoardPrinter();
  	
  	/**
  	 * Creates a new player object for use by both methods.
  	 */
	private Player testPlayer = new Player();	
	
	/**
	 * A manually created board designed to have no moves availiable for player "X", "O".
	 */
	private String [][] testBoard = 	{
	{ " X ", " X ", " O ", " X ", " O ", " X ", " O ", " X "},
	{ " X ", " X ", "   ", " X ", " X ", "   ", "   ", "   "},
	{ " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{ " X ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{ " O ", " X ", " X ", " X ", " X ", " X ", " X ", " X "},
	{ " X ", "   ", "   ", " X ", " X ", " X ", "   ", " X "},
	{ " O ", "   ", "   ", "   ", " X ", "   ", "   ", " X "},
	{ " X ", "   ", "   ", " O ", " O ", "   ", "   ", "   "}
	};

	/** 
	 * Confirms that neither play can make a move.
	 * <p>
	 * Runs the test, providing visual representation of the test
	 * for the user. Useful in the event that the fast method fails,
	 * so that the user can see where on the board errors are occurring.
	 */	
  	public void slow(){
		System.out.println("|THP1InvalidP2Invalid - Version 1");
		System.out.println("|Confirms game logic is able to check that neither player has moves.");
		
  	    System.out.println("\nOriginal Test Board: ");
		board.printBoard(testBoard);
		
		testBoard = testPlayer.getValidMoves("X",testBoard);
		System.out.printf("\nP1 Has Moves? Expecting 'false' : %s",testPlayer.hasMoves("X",testBoard));
		System.out.printf("\nP2 Has Moves? Expecting 'false' : %s\n",testPlayer.hasMoves("O",testBoard));
		
		if(!testPlayer.hasMoves("X",testBoard) && !testPlayer.hasMoves("O",testBoard)){
			System.out.println("---------------------------");
			System.out.println("THBP1InvalidP2Invalid: PASS");
			System.out.println("---------------------------");
		}
		else {
			System.out.println("---------------------------");
			System.out.println("THBP1InvalidP2Invalid: FAIL");
			System.out.println("---------------------------");
  	    }
	}

	/**
	 * The fast version for the test. Functionally identical to the slow version,
	 * 
	 * @return 	A boolean representing whether the test passed or not.
	 */	
	public boolean fast(){
		boolean testPassed = false;
		testBoard = testPlayer.getValidMoves("X",testBoard);
		if(!testPlayer.hasMoves("X",testBoard) && !testPlayer.hasMoves("O",testBoard)){
			testPassed = true;
		}
		return testPassed;
	}		
}
