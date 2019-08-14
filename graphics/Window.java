package graphics;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Window.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Creates the window for the GUI version of Othello.
 * This file is an OPTIONAL part of the game Othello.
 */
abstract class Window extends JFrame {
	/**
	 * The title of the window is stored here for ease of access
	 */
	private final String WINDOW_TITLE = "Othello Game";

	/**
	 * An ActionEvent that is called in a different file in the package.
	 */
	protected ActionEvent changeMode;
	
	/**
	 * Creates the game window as well as the menu bar.
	 */
	protected void setWindowProperties(){
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// First item on menu bar
		// Creates all the necessary elements for the "file" section of the toolbar.
		JMenu file = new JMenu("File");
		file.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(file);
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem exit = new JMenuItem("Exit");
		newGame.setFont(new Font("Arial", Font.BOLD, 12));
		save.setFont(new Font("Arial", Font.BOLD, 12));
		load.setFont(new Font("Arial", Font.BOLD, 12));
		exit.setFont(new Font("Arial", Font.BOLD, 12));
		file.add(newGame);
		file.add(save);
		file.add(load);
		file.add(exit);
		newGame.addActionListener(new newGameButtonAction());
		save.addActionListener(new saveButtonAction());
		load.addActionListener(new loadButtonAction());
		exit.addActionListener(new exitButtonAction());
		
		
		// Second item on menu bar
		// Creates all the necessary elements for the "options" section of the bar.
		JMenu options = new JMenu("Options");
		options.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(options);	
		JMenuItem AI = new JMenuItem("Change Game Mode");
		AI.setFont(new Font("Arial", Font.BOLD, 12));
		options.add(AI);
		AI.addActionListener(new changeModeAction());
		
	
		// Third item on menu bar
		// Creates all the necessary elements for the "Help" section of the toolbar.
		JMenu help = new JMenu("Help");
		help.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(help);
		JMenuItem about = new JMenuItem("About");
		JMenuItem instructions = new JMenuItem("How to play");
		about.setFont(new Font("Arial", Font.BOLD, 12));
		instructions.setFont(new Font("Arial", Font.BOLD, 12));
		help.add(about);
		help.add(instructions);
		about.addActionListener(new aboutButtonAction());
		instructions.addActionListener(new instructionsButtonAction());
		
		setSize(500, 500);
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// Player will not able to resize the window.
		setResizable(false);
		
		// Centers the window
		setLocationRelativeTo(null);

		setVisible(true);
	
	}

	private class newGameButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent newGame) {
			eventNewGame();
		}	
	}
	
	/**
	 * Overloaded method that displays error if not overloaded.
	 * <p>
	 * This overloaded method exists despite Window being abstract due to the inherent
	 * difficulties in attempting to override ActionListener files.
	 */
	protected void eventNewGame(){
		System.out.println("ERROR: Expecting GuiBoard to override this method (New Game).");
	}
	
	private class saveButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent save) {
			eventSave();
		}	
	}

	/**
	 * Overloaded method that displays error if not overloaded.
	 * <p>
	 * This overloaded method exists despite Window being abstract due to the inherent
	 * difficulties in attempting to override ActionListener files.
	 */
	protected void eventSave(){
		System.out.println("ERROR: Expecting GuiBoard to override this method (Save).");
	}
	
	private class loadButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent load) {
			eventLoad();
		}	
	}

	/**
	 * Overloaded method that displays error if not overloaded.
	 * <p>
	 * This overloaded method exists despite Window being abstract due to the inherent
	 * difficulties in attempting to override ActionListener files.
	 */
	protected void eventLoad(){
		System.out.println("ERROR: Expecting GuiBoard to override this method (Load).");
	}
	
	private class exitButtonAction implements ActionListener{
		/**
		 * First displays a confirmation dialog box before exiting the program if the user clicks the 
		 * 'exit' JMenuItem.
		 */
		public void actionPerformed(ActionEvent exit) {
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit");
			System.out.print(choice);
			if (choice == JOptionPane.YES_NO_OPTION){
				System.exit(0);
			}
			
		}
		
	}

	private class aboutButtonAction implements ActionListener{
		/**
		 * Displays a dialog window containing information about the program
		 * should the user select the 'about' JMenuItem.
		 */
		public void actionPerformed(ActionEvent about) {
			JOptionPane.showMessageDialog(null, "<html>Othello R4 - Version 1<br><br>Produced by: Team 4</html>");

		}
		
	}
	
	protected class changeModeAction implements ActionListener{
		/**
		 * Calls a method that allows the game mode to be changed.
		 */
		public void actionPerformed(ActionEvent changeMode) {
			showChangeGameMode();
		
		}
	}
	
	/**
	 * Opens a dialog box that allows the user to choose from three game modes.
	 */
	protected void showChangeGameMode(){
		Object[] options = {"PvP", "CPU (Easy)", "CPU (Hard)"};
		int choice = JOptionPane.showOptionDialog(null, "Select a Game Mode below.", "Select Game Mode", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
		if(choice == 0){
			System.out.println("PvP Selected: Two players challenge each other in black-on-white combat.");
			changeGameMode(0);
		}
		else if (choice == 1){
			System.out.println("CPU (Easy) Selected: One player goes up against an unpredictable, but stupid AI.");
			changeGameMode(1);
		}
		else if (choice == 2){
			System.out.println("CPU (Hard) Selected: One player goes up against a predictable, but sadistic AI.");
			changeGameMode(2);
		}
		else {
			changeModeAction cMA = new Window.changeModeAction();
			cMA.actionPerformed(changeMode);
		}
	}

	/**
	 * Overloaded method that displays error if not overloaded.
	 * <p>
	 * This overloaded method exists despite Window being abstract due to the inherent
	 * difficulties in attempting to override ActionListener files.
	 */
	protected void changeGameMode(int inGameMode){
		System.out.printf("ERROR: Expecting GuiBoard to override this method (Change Mode to %d).\n",inGameMode);
	}
	
	private class instructionsButtonAction implements ActionListener{
		/**
		 * Displays a dialog box containing game instructions once the
		 * 'newGame' JMenuItem is clicked.
		 */
		public void actionPerformed(ActionEvent newGame) {
			JOptionPane.showMessageDialog(null, "<html>Goal:<br>To have the majority of your colour pieces on the board when the game is over.<br> Flip your opponent's pieces by trapping them between two of your own.</html>");

		}
		
	}

}
