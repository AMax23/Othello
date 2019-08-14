package terminal;
import java.util.Scanner;
import logic.Board;
import logic.ComputerPlayer;
import logic.Player;

/**
 * PlayerVsComputer.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class represents game logic for a Human Vs. Computer game.
 * This file is an ADDITION to the game Othello.
 */
public class PlayerVsComputer extends Player{

	private Board gameBoard = new Board(true);
	private String [][] newBoard = gameBoard.getBoard();
	private boolean stillPlaying = true;
	private boolean guiEnabled;
	private Scanner keyboard = new Scanner (System.in);
	//private boolean hardMode;

	/** 
	 * Places a piece on the board.
	 * <p>
	 * This method works for both TERMINAL and GUI versions of the Othello game.
	 * The GUI submits two integers for the piece to be directly played.
	 * The TERMINAL version submits to dummy integers (0,0) which will be overriden
	 * in a different method belonging to Player.java
	 * Finally, it also tells the GUI version to ignore any keyboard input needed, since this
	 * is handled in GuiBoard itself.
	 * 
	 * @param game		A copy of GameConfiguration which handles game logic.
	 * @param gui		Determines whether the GUI is enabled or not. The GUI version always passes
	 * 					a true boolean. The TERMINAL version always passes a false.	
	 * @param row		An integer representing the row on a String[][] in descending order.
	 * @param col		An integer representing the column on a String[][] from left-to-right.
	 * @param player	A string representing the player's piece.
	 */
	public void playTurn(boolean gui, int row, int col, String player){
		guiEnabled = gui;
		newBoard = getValidMoves(player,newBoard);
		gameBoard.printBoard(gameBoard.getBoard());
		
		if (hasMoves(player,newBoard)){
			if(!guiEnabled){
				newBoard = playerTurn(player,newBoard);
			}
			else{
				System.out.println("\nPlayer 1's turn.");
				newBoard = placePiece(player,newBoard,row,col);
			}
		}
		else {
			// GUI-based version of the game has its own dialog box handler.
			if(!guiEnabled){
				System.out.println("P1 has no valid moves! Trying to pass turn to P2. Enter anything to continue...\n");
				keyboard.next();
			}	
		}
	}

	/** 
	 * Asks the CPU to play a turn.
	 * 
	 * @param game			A copy of GameConfiguration which handles game logic.
	 * @param cpu			A copy of ComputerPlayer which handles game logic.
	 * @param guiEnabled	A boolean determining whether the player is running the game via
	 * 						TERMINAL or GUI.
	 * @param hardMode		A boolean determining whether the player is playing against the easy or the hard CPU.
	 */
	public void cpuTurn(ComputerPlayer cpu, boolean guiEnabled, boolean hardMode){
		newBoard = getValidMoves("O",newBoard);
	
		if (hasMoves("O",newBoard)){
			
			// Determines how the CPU should play.
			if (hardMode)
				newBoard = cpu.hardAI(newBoard);
			
			else if (!hardMode)
				newBoard = cpu.easyAI(newBoard);
			
			else
				hardMode = difficulty();
		}
		else {
			System.out.println("CPU has no valid moves! Trying to pass turn to P1.\n");
		}
	}

	/**
	 * Runs the game until no valid moves are available for both players. Used in the TERMINAL version.
	 * @param game			A copy of GameConfiguration which handles game logic.
	 * @param cpu			A copy of ComputerPlayer which handles game logic.
	 * @param inhardMode	A boolean determining whether the player is playing against the easy or the hard CPU.
	 * @param gui			A boolean determining whether the player is running the game via
	 * 						TERMINAL or GUI.
	 */	
	public void play(ComputerPlayer cpu, boolean inHardMode, boolean gui){
		
		// Primary game loop. Plays out three phases.
		while (stillPlaying){
			String player = "X";
			playTurn(gui, 0, 0, player);
			cpuTurn(cpu, inHardMode, gui);
			
			if (!hasMoves("O",newBoard) && !hasMoves("X",newBoard)){
				stillPlaying = false;
				gameBoard.printBoard(gameBoard.getBoard());
				gameBoard.appointWinner(newBoard);			 	
			}
		}
	}
	
	/**
	 * Prompts user on whether they want to play against a hard or easy CPU player.
	 * <p>
	 * The method exists purely in the case that the program is unable to determine
	 * the selected difficulty. No GUI version exists- but can still receive terminal input
	 * while the GUI version is running.
	 * 
	 * @return		A boolean representing whether hard difficult was enabled (true) or not (false).
	 */	
	private boolean difficulty(){
		boolean inputNeeded = true;
		boolean hardMode = false;
		Scanner keyboard = new Scanner (System.in);
		
		while (inputNeeded == true){
			System.out.print("Play against hard AI? (Y/N) ");
			String input = keyboard.nextLine().toUpperCase();
			
			if (input.equals("Y")){
				inputNeeded = false;
				hardMode = true;
			
			}
			else if (input.equals("N")){
				inputNeeded = false;
				hardMode = false;
			
			}
			else {
				System.out.println("Input is invalid.");
			}
	
		}
		return hardMode;
	}
}
