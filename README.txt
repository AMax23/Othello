Team 4 - Othello
Version R4v1
April 11, 2017

PACKAGES:
- graphics (GameGUI.java, GuiBoard.java, Window.java)
- terminal (GameNOGUI.java, PlayerVsPlayer.java, PlayerVsComputer.java)
- logic (Board.java, ComputerPlayer.java, Player.java)
- tests

==================================================================================================================================

** RUNNING THE GAME **
In order to run the game you must compile everything first.
In the terminal simply type:

> javac graphics/*.java
> javac logic/*.java
> javac terminal/*.java
> javac tests/*.java

There are 2 versions of this game; terminal and GUI.
To run the terminal version, type:
> java terminal/GameNOGUI

To run the graphics version, type:
> java graphics/GameGUI

To run the tests, in the terminal enter:
> java tests/TestHub

==================================================================================================================================

** HOW TO PLAY THE GAME **

AVAILABLE GAME MODES: 
- Player Vs Player
- Player Vs Computer (Easy)
- Player Vs Computer (Hard)

- In game mode with computer player, the human is the black piece ("X" in terminal) and they always go first.

MAKING MOVES:
- You can "flip" an opponent's piece by trapping their piece between two of your own. This can be done in all cardinal and ordinal (diagonal) directions. A piece must be flipped in order for a move to be valid.

- Valid moves are indicated by small grey circles in the graphical version, and asterisk (*) symbols in the terminal version. If one or more valid moves are available, one must be made on your turn. Turns are only skipped in the event that either player does not have a valid move.

GAME OBJECTIVE:
- The objective of the game is to have more pieces of your color on the board by the end of the game.

- If a player has no valid moves, their turn is passed and their opponent gets to move until a valid move becomes available.

THE GAME ENDS WHEN: 
a) There are no valid moves left for either player 
b) The board is full.

==================================================================================================================================
