package tests;

import logic.Player;

/**
 * THP1InvalidP2Valid.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * This is a test class to ensure that X can't make a move.
 * This file is an OPTIONAL part of the game Othello.
 */
public class THP1InvalidP2Valid{

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
	 * A manually created board designed to have no moves availiable for player "X".
	 */
	private String [][] testBoard = {
	{ " X ", " O ", " O ", " O ", " O ", " X ", "   ", "   "},
	{ " X ", " X ", " O ", " X ", " X ", " X ", "   ", "   "},
	{ "   ", " X ", " X ", " X ", "   ", " X ", " X ", "   "},
	{ "   ", "   ", " X ", " X ", " X ", " X ", " X ", " X "},
	{ "   ", " X ", " X ", " X ", " X ", " X ", " X ", "   "},
	{ " X ", "   ", "   ", " X ", " X ", " X ", "   ", "   "},
	{ "   ", "   ", "   ", " X ", " X ", "   ", "   ", " X "},
	{ "   ", "   ", " O ", " O ", " O ", "   ", "   ", " O "}
	};

	/** 
	 * Confirms game logic is able to check that only Player 2 has moves.
	 * <p>
	 * Runs the test, providing visual representation of the test
	 * for the user. Useful in the event that the fast method fails,
	 * so that the user can see where on the board bugs are occurring.
	 */		
	public void slow(){	   
		System.out.println("|THP1InvalidP2Valid - Version 1");
		System.out.println("|Confirms game logic is able to check that only Player 2 has moves.");
		
  	    System.out.println("\nOriginal Test Board: ");    
	    board.printBoard(testBoard);
	    
  	    System.out.println("\nValid Moves For P1: ");   	    
	    board.printBoard(testPlayer.getValidMoves("X",testBoard));
	    System.out.printf("\nP1 Has Moves? Expecting 'false' : %s\n",testPlayer.hasMoves("X",testBoard));
	    
  	    System.out.println("\nValid Moves For P2 ");   	    
	    board.printBoard(testPlayer.getValidMoves("O",testBoard));
	    System.out.printf("\nP2 Has Moves? Expecting 'true'  : %s",testPlayer.hasMoves("O",testBoard));
	    
	    if(testPlayer.hasMoves("O",testBoard) && !testPlayer.hasMoves("X",testBoard)){
			System.out.println("\n------------------------");
			System.out.println("THP1InvalidP2Valid: PASS");
			System.out.println("------------------------");
		}
		else {
			System.out.println("\n------------------------");
			System.out.println("THP1InvalidP2Valid: FAIL");
			System.out.println("------------------------");
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
		if(testPlayer.hasMoves("O",testBoard) && !testPlayer.hasMoves("X",testBoard)){
			testPassed = true;
		}
		return testPassed;
	}		

}
