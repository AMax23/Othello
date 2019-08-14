package terminal;
import java.util.Scanner;
import logic.ComputerPlayer;

/**
 * GameNOGUI.java
 * @author 	Team 4
 * @version	R4 - V1
 * 
 * Class acts as a launcher into the terminal version of the game. Game MUST be launched from here.
 * This file is a CORE part of the game Othello.
 */
public class GameNOGUI {

	/**
	 * Initializes the terminal version of the game for the user.
	 */	
	public static void main (String [] args) {
		
		boolean guiEnabled = false;
		boolean inputNeeded = true;
		Scanner keyboard = new Scanner (System.in);
		
		System.out.println("\n///////////////////////////////////////////////////////");
		System.out.println("//                                                   //");
		System.out.println("//   OOO   TTTTT  H   H  EEEEE  L      L       OOO   //");
		System.out.println("//  O   O    T    H   H  E      L      L      O   O  //");
		System.out.println("//  O   O    T    HHHHH  EEE    L      L      O   O  //");
		System.out.println("//  O   O    T    H   H  E      L      L      O   O  //");
		System.out.println("//   OOO     T    H   H  EEEEE  LLLLL  LLLLL   OOO   //");
		System.out.println("//                                                   //");
		System.out.println("//           ~ R3 Version 1 - Made by Team 4 ~       //");
		System.out.println("//                                                   //");
		System.out.println("///////////////////////////////////////////////////////\n");
				
		// Prompts user for valid input; Whether play wants to play against a CPU or not.
		while (inputNeeded == true){
			System.out.println("\nSelect an option.");
			System.out.println("1) Player Vs. Player ");
			System.out.println("2) Player Vs. Easy CPU ");
			System.out.println("3) Player Vs. Hard CPU ");
			System.out.println("Q) Quit game. ");
			String input = keyboard.nextLine().toUpperCase();
			
			// Launches the Player Vs Player gamemode.
			if (input.equals("1")){
				inputNeeded = false;
				PlayerVsPlayer gameStatePVP = new PlayerVsPlayer();
				gameStatePVP.play(guiEnabled);
			}
			
			// Launches the Player Vs Computer gamemode. (Easy)
			else if (input.equals("2")){
				boolean hardMode = false;
				inputNeeded = false;
				ComputerPlayer cpu = new ComputerPlayer();
				PlayerVsComputer gameStatePVC = new PlayerVsComputer();
				gameStatePVC.play(cpu, hardMode, guiEnabled);
			}
			
			// Launches the Player Vs Computer gamemode. (Hard)
			else if (input.equals("3")){
				boolean hardMode = true;
				inputNeeded = false;
				ComputerPlayer cpu = new ComputerPlayer();
				PlayerVsComputer gameStatePVC = new PlayerVsComputer();
				gameStatePVC.play(cpu, hardMode, guiEnabled);
			}
			
			// Closes the client.
			else if (input.equals("Q")){
				inputNeeded = false;
				break;
			}
			
			else {
				System.out.println("Input is invalid.");
			}
		}		
	}
}
