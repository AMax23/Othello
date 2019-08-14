package terminal;
import java.util.Scanner;
import logic.Board;
import logic.Player;

/**
 * PlayerVsPlayer.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class represents game logic for a Human Vs. Human game.
 * This file is a CORE part of the game Othello.
 */
public class PlayerVsPlayer extends Player {

	private Board gameBoard = new Board(true);
	// Creates all necessary objects to run the method.
	private String [][] newBoard = gameBoard.getBoard();
	private boolean stillPlaying = true;
	private boolean guiEnabled;
	private Scanner keyboard = new Scanner (System.in);

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

		// Plays the turn out.
		if (hasMoves(player,newBoard)){

			if(!guiEnabled){
				newBoard = playerTurn(player,newBoard);
			}
			else{
				if(player.equals("X")){
					System.out.println("\nPlayer 1's turn.");
				}
				else{
					System.out.println("\nPlayer 2's turn.");
				}
				newBoard = placePiece(player,newBoard,row,col);
			}
		}

		else {
			// GUI-based version of the game has its own dialog box handler.
			if(!guiEnabled){
				if(player.equals("X")){
					System.out.println("P1 has no valid moves! Trying to pass turn to P2. Enter anything to continue...\n");
				}
				else {
					System.out.println("P2 has no valid moves! Trying to pass turn to P1. Enter anything to continue...\n");
					keyboard.next();
				}
			}
		}
	}	

	/**
	 * Runs the game until no valid moves are available for both players. Used in the TERMINAL version.
	 * @param game			A copy of GameConfiguration which handles game logic.
	 * @param gui			A boolean determining whether the player is running the game via
	 * 						TERMINAL or GUI.
	 */	
	public void play(boolean gui){	
		// Primary game loop. Plays out three phases.
		while (stillPlaying){

			String player = "X";
			playTurn(gui, 0, 0, player);
			player = "O";
			playTurn(gui, 0, 0, player);

			// Checks to see if game has ended.
			if (!hasMoves("O",newBoard) && !hasMoves("X",newBoard)){
				stillPlaying = false;
				gameBoard.printBoard(gameBoard.getBoard());
				gameBoard.appointWinner(newBoard);
			}
		}
	}
}
