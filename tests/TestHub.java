package tests;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TestHub.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class calls all the other tests.
 * This file is an OPTIONAL part of the game Othello.
 */
public class TestHub{

	/**
	 * Invokes introduction screen for user.
	 */
	public static void main(String[] args){

		//Amend as more tests become available in the future.
		String[] availableTests = {"1","2","3","4","5","6"};
		Scanner keyboard = new Scanner(System.in);
		boolean testing = true;

		introMessage();
		listMessage();

		while(testing){
			String input = keyboard.nextLine();

			if (input.equals("HELP")){
				helpMessage();
			}
			else if (input.equals("LIST")){
				listMessage();
			}
			else if (input.equals("RUNALL")){
				testFast();
			}
			else if (Arrays.asList(availableTests).contains(input)){
				int testNum = Integer.parseInt(input);
				testSlow(testNum);
			}
			else if (input.equals("CREDIT")){
				creditMessage();
			}
			else if (input.equals("QUIT")){
				testing = false;
			}
			else {
				System.out.print("Invalid input. Input \"HELP\" for quickstart guide: ");
			}
		}
	}

	/**
	 * Runs the fast version of every test class, displays the result for the user.
	 */
	private static void testFast(){

		System.out.println("Test Results:");

		// TEST 1 //
		THDirectional test1 = new THDirectional();
		boolean test1Bool = test1.fast();
		if (test1Bool == true){
			System.out.println("1) Passed");
		}
		else if (test1Bool == false){
			System.out.println("1) Failed");
		}
		else {
			System.out.println("1) ERROR!");
		}

		// TEST 2 //
		THAllX test2 = new THAllX();
		boolean test2Bool = test2.fast();
		if (test2Bool == true){
			System.out.println("2) Passed");
		}
		else if (test2Bool == false){
			System.out.println("2) Failed");
		}
		else {
			System.out.println("2) ERROR!");
		}

		// TEST 3 //
		THP1InvalidP2Invalid test3 = new THP1InvalidP2Invalid();
		boolean test3Bool = test3.fast();
		if (test3Bool == true){
			System.out.println("3) Passed");
		}
		else if (test3Bool == false){
			System.out.println("3) Failed");
		}
		else {
			System.out.println("3) ERROR!");
		}

		// TEST 4 //
		THP1InvalidP2Valid test4 = new THP1InvalidP2Valid();
		boolean test4Bool = test4.fast();
		if (test4Bool == true){
			System.out.println("4) Passed");
		}
		else if (test4Bool == false){
			System.out.println("4) Failed");
		}
		else {
			System.out.println("4) ERROR!");
		}

		// TEST 5 //
		THFlip test5 = new THFlip();
		boolean test5Bool = test5.fast();
		if (test5Bool == true){
			System.out.println("5) Passed");
		}
		else if (test5Bool == false){
			System.out.println("5) Failed");
		}
		else {
			System.out.println("5) ERROR!");
		}

		// TEST 6 //
		THBoard test6 = new THBoard();
		boolean test6Bool = test6.fast();
		if (test6Bool == true){
			System.out.println("6) Passed");
		}
		else if (test6Bool == false){
			System.out.println("6) Failed");
		}
		else {
			System.out.println("6) ERROR!");
		}
	}

	/**
	 * Runs the detailed version of the test of the users choice. Displays results.
	 * 
	 * @param testNum	An integer representing which test the user would like to run
	 */
	private static void testSlow(int testNum){
		switch (testNum){
		case 1:
			THDirectional test1 = new THDirectional();
			test1.slow();
			break;
		case 2:
			THAllX test2 = new THAllX();
			test2.slow();
			break;
		case 3:
			THP1InvalidP2Invalid test3 = new THP1InvalidP2Invalid();
			test3.slow();
			break;
		case 4:
			THP1InvalidP2Valid test4 = new THP1InvalidP2Valid();
			test4.slow();
			break;
		case 5:
			THFlip test5 = new THFlip();
			test5.slow();
			break;
		case 6:
			THBoard test6 = new THBoard();
			test6.slow();
			break;
		}
	}

	/**
	 * Displays a message in the console at the start of the program containing helpful information for the user.
	 */
	private static void introMessage(){
		System.out.println("TestHub v.1 - Team 4 Othello");
		System.out.println("Available Commands: HELP, LIST, RUNALL, 1-6, CREDIT, QUIT\n");
	}

	/** 
	 * Provides the user with a list of possible tests to run.
	 */			
	private static void listMessage(){
		// Test List - Add more according to format shown below.
		System.out.println("\n----------------------------------------------");
		System.out.println("1) P1 8-Direction Valid Moves");
		System.out.println("2) Full-Board Game Over");
		System.out.println("3) P1,P2 No Valid Moves");
		System.out.println("4) P1-No Valid, P2-Valid Moves");
		System.out.println("5) Piece Flipper Test");
		System.out.println("6) Board Boundaries Test");
		System.out.println("----------------------------------------------");
	}

	/**
	 * When called, it will provide the user with a complete list of usable commands.
	 */		
	private static void helpMessage(){
		System.out.println("\n----");
		System.out.println("TestHub allows for the automation of all tests.");
		System.out.println("Or the testing of specific situations, labelled");
		System.out.println("by what they check respectively.");

		System.out.println("-----------------------------------");
		System.out.println("LIST  : Lists all available tests.");
		System.out.println("RUNALL: Automates through all available tests.");
		System.out.println("NUMBER: Activates indepth test of specified number. (ex. 1)");
		System.out.println("CREDIT: List authors, co-authors, and borrowed code.");
		System.out.println("QUIT  : Closes TestHub.");
		System.out.println("-----------------------------------");
	}

	/**
	 * Displays the credits.
	 */
	private static void creditMessage(){
		System.out.println("\nTestHub and compatible tests were made by Team 4.\n");
	}

}
