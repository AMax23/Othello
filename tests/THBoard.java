package tests;

import logic.Board;

/**
 * THBoard.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Test class designed to ensure the isOnBoard function is operating properly.
 * This file is an OPTIONAL part of the game Othello.
 */
public class THBoard{
	
	private Board test = new Board();

	/**
	 * Checks if the board can recognize whether something is on the board or not.
	 * <p>
	 * The detailed version of the test. Provides information about the test cases to the user.
	 * It passes both valid and invalid ranges into the isOnBoard function, and if the results
	 * differ from the expected values, the test is failed.
	 */
	public void slow(){
		System.out.println("|THBoard - Version 1");
		System.out.println("|Checks two ranges on, off the board. ");
			
		test.printBoard(test.getBoard());
		boolean inRange = test.isOnBoard(0,0);
		boolean inRangeTwo = test.isOnBoard(7,7);
		boolean outRange = test.isOnBoard(-1,-1);
		boolean outRangeTwo = test.isOnBoard(8,8);
		System.out.print("\n1,1   on Board? Expecting 'true' : ");
		System.out.println(inRange);
		System.out.print("8,8   on Board? Expecting 'true' : ");
		System.out.println(inRangeTwo);
		System.out.print("-1,-1 on Board? Expecting 'false': ");
		System.out.println(outRange);
		System.out.print("9,9   on Board? Expecting 'false': ");
		System.out.println(outRangeTwo);
		
		if(inRange && inRangeTwo && !outRange && !outRangeTwo){
			System.out.println("-------------");
			System.out.println("THBoard: PASS");
			System.out.println("-------------");
		}
		else {
			System.out.println("-------------");
			System.out.println("THBoard: FAIL");
			System.out.println("-------------");
		}			
	}

	/**
	 * The fast version for the test. Functionally identical to the slow version,
	 * 
	 * @return 	A boolean representing whether the test passed or not.
	 */	
	public boolean fast(){
		boolean testPassed = false;
		boolean inRange = test.isOnBoard(0,0);
		boolean inRangeTwo = test.isOnBoard(7,7);
		boolean outRange = test.isOnBoard(-1,-1);
		boolean outRangeTwo = test.isOnBoard(8,8);
		if(inRange && inRangeTwo && !outRange && !outRangeTwo){
			testPassed = true;
		}
		return testPassed;
	}
}
