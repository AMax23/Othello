package graphics;

import logic.Board;
import logic.Player;

/**
 * GameGUI.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class acts as a launcher into the GUI version of the game. Can be overridden manually.
 * THIS FILE MAY BE REMOVED OR INTEGRATED ELSEWHERE LATER ON.
 * This file is an OPTIONAL part of the game Othello.
 */
public class GameGUI {

	/**
	 * Launches the game based on the value of gameMode in its code.
	 */
	public static void main (String[] args){
		int gameMode = 0;
		gameInit(gameMode);
	}

	/**
	 * Sets up the button grid and window when called.
	 * @param gameMode	Represents the gamemode to play. (PvP, PvC, PvhC)
	 */
	private static void gameInit(int gameMode){
		Board gameBoard = new Board(true);
		String [][] board = gameBoard.getBoard();
		GuiBoard gui = new GuiBoard(gameMode); // Prepares button grid.
		gui.setGuiWindow(); // Prepares window.
		
		// First terminal screen printed.
		Player moves = new Player();
		board = moves.getValidMoves("X",board);
		System.out.print("\nPlayer 1's turn.");
		gameBoard.printBoard(board);
		
		// First GUI board printed.
		gui.printBoard(board);
	}
}
