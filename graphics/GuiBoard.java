package graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import logic.Board;
import logic.ComputerPlayer;
import logic.Player;
import terminal.PlayerVsComputer;
import terminal.PlayerVsPlayer;

/**
 * GuiBoard.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class represents all the interactable elements within the window.
 * This file is an ADDITION to the game Othello.
 */
public class GuiBoard extends Window{

	// Colours used in the game and the board size.
	private final int ROWSIZE = 8;
	private final int COLSIZE = 8;
	private final Color COL1 = new Color(209, 175, 123);
	private final Color COL2 = new Color(126, 95, 69);
	private final Color BORDERCOLOUR = Color.BLACK;

	// Images for black and white pieces, valid move, and a transparent background.
	private final ImageIcon BLACKPIECE = new ImageIcon("black.png");
	private final ImageIcon WHITEPIECE = new ImageIcon("white.png");
	private final ImageIcon VALIDMOVE  = new ImageIcon("valid.png");
	private final ImageIcon EMPTY 	   = new ImageIcon("blank.png");

	// This makes an 8x8 grid where each cell on the board is a button.
	private JButton [][] grid = new JButton[ROWSIZE][COLSIZE];
	private Player moves = new Player();
	private Board gameBoard = new Board();
	private String player = "X";
	private boolean gameOver = false;
	private int gameMode = -1;

	/**
	 * Constructor method. Creates a grid where each cell is a button.
	 * 
	 * @param inGameMode	Input gamemode that determines what version of the game should be played.
	 */
	public GuiBoard(int inGameMode){
		gameMode = inGameMode;
		// This loops, makes and adds each cell to the grid. Each cell is a button.
		for (int row = 0; row < ROWSIZE; row++){
			for (int col = 0; col < COLSIZE; col++)
				grid[row][col] = new JButton();
		}
	}

	/**
	 * Creates a new grid layout, where each cell is a button and alternates in colour.
	 */
	private void createGrid(){
		// Holds all the contents
		Container pane = getContentPane();

		// Creates a grid layout size 8 x 8
		pane.setLayout(new GridLayout(ROWSIZE,COLSIZE));

		// Temporary colour used in loop to alternate between two colours.
		Color colour;

		// This loops, makes and adds each cell to the grid
		for (int row = 0; row < ROWSIZE; row++){
			// Ensures every other cell is a different colour.
			if (row%2 == 0){
				colour = COL1;
			}
			else {
				colour = COL2;
			}
			// Sets background colour of each cell and provides a border. (Alternates each cell colour)
			for (int col = 0; col < COLSIZE; col++){
				grid[row][col].setBackground(colour);
				grid[row][col].setBorder(new LineBorder(BORDERCOLOUR));

				if(colour == (COL1)){
					colour = COL2;
				}
				else {
					colour = COL1;
				}
				// Adds each cell to the container, and adds it to the MouseListener.
				pane.add(grid[row][col]);
				grid[row][col].addMouseListener(new MouseEvents());
			}
		}
	}

	/**
	 * Sets up window (size, title, etc), and displays the grid.
	 */
	public void setGuiWindow(){
		setWindowProperties();
		createGrid();
		showChangeGameMode();
	}

	/**
	 * Prints the game icons on the window.
	 * 
	 * @param board		A String[][] representing the gameboard that is to be translated
	 * 					into a visual version.
	 */
	public void printBoard(String [][] board){

		for (int row = 0; row < ROWSIZE; row++){

			for (int col = 0; col < COLSIZE; col++){

				if (board[row][col].equals(" X "))
					grid[row][col].setIcon(BLACKPIECE);

				else if (board[row][col].equals(" O "))
					grid[row][col].setIcon(WHITEPIECE);

				else if (board[row][col].equals(" * "))
					grid[row][col].setIcon(VALIDMOVE);

				else
					grid[row][col].setIcon(EMPTY);
			}
		}
	}

	/**
	 * Does everything necessary to give the user the most up-to-date game board.
	 * 
	 * @param player represents the current player.
	 * @param moves is a logic module for determining whether a player has valid moves.
	 */
	private void refreshBoard(String player, Player moves){
		moves.getValidMoves(player, gameBoard.getBoard());
		printBoard(gameBoard.getBoard());
		gameBoard.printBoard(gameBoard.getBoard());
	}

	/**
	 * Waits for exactly 300ms. Placed here to prevent clutter in code.
	 */
	private void waitTurn(){
		int ms = 300;

		try {
			Thread.sleep(ms);
		}
		catch(InterruptedException ex) {
			System.out.printf("Failed to wait for %dms.\n",ms);
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Resets the board to a default, unplayed board.
	 */
	public void eventNewGame(){
		gameBoard.resetBoard();
		refreshBoard("X",moves);
		showChangeGameMode();
		gameOver = false;
	}

	/**
	 * Changes the game mode the player is in.
	 * 
	 * @param inGameMode represents three game modes. 0: PvP, 1: PvC (Easy), 2: PvC (Hard).
	 */
	public void changeGameMode(int inGameMode){
		gameMode = inGameMode;
	}

	/**
	 * Calls a method which saves the current plays and the current player.
	 * <p>
	 * Any method containing 'event' at the beginning overrides some method in Window.java.
	 * It acts as a means for Guiboard to communicate with the frame it is being hosted in.
	 */
	public void eventSave(){
		gameBoard.saveBoard(player);
		JOptionPane.showMessageDialog(null, "Board successfully saved!");
	}

	/**
	 * Calls a method that loads the datafile SAVESTATE.txt. Prints statement with whichever player's turn.
	 */
	public void eventLoad(){
		player = gameBoard.loadBoard(player, moves);
		String opponent = "";
		String playerPiece = "";
		int playerNum;
		if (player.equals("X")){
			opponent = " O ";
			playerPiece = " X ";
			playerNum = 1;
		}
		else {
			opponent = " X ";
			playerPiece = " O ";
			playerNum = 2;
		}
		System.out.printf("Player %d's turn.",playerNum);
		refreshBoard(player, moves);
		JOptionPane.showMessageDialog(null, "Board successfully loaded!");
		
	}

	/**
	 * MouseEvents
	 * @author 	Team 4
	 * @version	R3-V1
	 * 
	 * PRIVATE class representing all actions to be taken when user plays the game.
	 * This file is a CORE part of the class GuiBoard.java
	 */
	private class MouseEvents implements MouseListener{

		/**
		 * A collection of logical classes that allow the game to function.
		 */
		private PlayerVsPlayer pvpState = new PlayerVsPlayer();
		private PlayerVsComputer pvcState = new PlayerVsComputer();
		private ComputerPlayer cpu = new ComputerPlayer();
		
		/**
		 * Handles the 'game logic' for the user provides input via mouse.
		 * 
		 * @param clickEvent	A MouseListener parameter that determines what to do when a click is given.
		 */
		public void mouseClicked(MouseEvent clickEvent){
			Object clickedCell = clickEvent.getSource();

			for (int row = 0; row < ROWSIZE; row++){
				for (int col = 0; col < COLSIZE; col++){
					if ( (clickedCell == grid[row][col])  && (grid[row][col].getIcon().equals(VALIDMOVE)) && gameOver == false){

						// GAMEMODE 0
						if (player.equals("X") && (gameMode == 0)){
							pvpState.playTurn(true, row, col, player);
						}
						else if (player.equals("O") && (gameMode == 0)){
							pvpState.playTurn(true, row, col, player);
						}

						// IF GAMEMODE 1/2
						if ((gameMode == 1) || (gameMode == 2)){
							pvcState.playTurn(true, row, col, player);
							refreshBoard(player, moves);

							if (gameMode == 1){	
								waitTurn();
								pvcState.cpuTurn(cpu, true, false);						
								refreshBoard(player, moves);
							}

							else if (gameMode == 2){
								waitTurn();
								pvcState.cpuTurn(cpu, true, true);
								refreshBoard(player, moves);
							}													
						}

						// Switches players.
						if (player.equals("X") && (gameMode == 0)){
							player = "O";
							System.out.print("\nPlayer 2's turn.");
							refreshBoard(player, moves);
						}
						else if (player.equals("O") && (gameMode == 0)){
							player = "X";
							System.out.print("\nPlayer 1's turn.");
							refreshBoard(player, moves);
						}
					}
				}
			}
		}

		/**
		 * Handles the 'game logic' for the user provides input via mouse.
		 * 
		 * @param pressEvent	A MouseListener parameter that determines what to do when a press is given.
		 */
		public void mousePressed(MouseEvent pressEvent){
		}

		/**
		 * Handles the 'game logic' for the user provides input via mouse.
		 * 
		 * @param releaseEvent	A MouseListener parameter that determines what to do when a release is given.
		 */
		public void mouseReleased(MouseEvent releaseEvent) {
		}

		/**
		 * Handles the 'game logic' for the user provides input via mouse. Constantly checks to see if game has ended.
		 * 
		 * @param enterEvent	A MouseListener parameter that determines what to do when the mouse enters.
		 */
		public void mouseEntered(MouseEvent enterEvent){
			
			// Checks to see if game has ended.
			if (!moves.hasMoves("O",gameBoard.getBoard()) && !moves.hasMoves("X",gameBoard.getBoard()) && !gameOver){
				gameBoard.printBoard(gameBoard.getBoard());
				printBoard(gameBoard.getBoard());
				gameBoard.appointWinner(gameBoard.getBoard());
				gameOver = true;
			}
			
			// If game has not ended, but there are no turns, pass to the other player.
			Player moves = new Player();
			if (gameOver == false){

				if (player.equals("X")  && !moves.hasMoves(player,gameBoard.getBoard())){
					JOptionPane.showMessageDialog(null,"No valid moves for player 1","Turn Skipped",JOptionPane.INFORMATION_MESSAGE);
					if (gameMode == 0){
						player = "O";
						refreshBoard(player,moves);
					}
					else if (gameMode == 1){
						pvcState.cpuTurn(cpu, true, false);
					}
					else if (gameMode == 2){
						pvcState.cpuTurn(cpu, true, true);
					}

				}
				else if (player.equals("O")  && !moves.hasMoves(player,gameBoard.getBoard())){
					JOptionPane.showMessageDialog(null,"No valid moves for player 2","Turn Skipped",JOptionPane.INFORMATION_MESSAGE);
					player = "X";
					gameBoard.setBoard(moves.getValidMoves(player,gameBoard.getBoard()));
					gameBoard.printBoard(gameBoard.getBoard());
					printBoard(gameBoard.getBoard());

				}
			}
		}

		/**
		 * Handles the 'game logic' for the user provides input via mouse.
		 * 
		 * @param exitEvent	A MouseListener parameter that determines what to do when the mouse leaves.
		 */
		public void mouseExited(MouseEvent exitEvent){
		}

	}

}
