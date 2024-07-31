# Minesweeper

## Description
This project is my implementation of the game minesweeper for my CIS 1200 final project.

There are 4 key concepts used throughout this implentation: 2D arrays, recursion, file I/O, and JUnit testing.

**2D Arrays**: The game board is represented by a 2D array of Cell objects, each of which stores whether the cell is revealed, whether the cell contains a bomb, and whether the cell is flagged.

**Recursion**: Recursion is used to implement the functionality of flooding. This is what happens when the user clicks on an empty cell that is not surrounded by other bombs. When this happens, recursion takes place to reveal other cells until empty cells that have surrounding bombs are revealed.

**File I/O**: Utilizing file I/O allows the user to save and reload the game after exiting.

**JUnit Testing**: I verified the correctness of my implementation through unit testing via JUnit.

## Overview of Classes
**Cell.java**: This class stores whether each tile is revealed, is a bomb, or has a flagged.

**Minesweeper.java**: This class stores the entire game board, the total number of bombs, the number of flags that are left (after the user puts down flags), and the game state (whether you have won, lost, or are still continuing the game). This class also has all the methods that allow the game to run.

**RunMinesweeper.java**: This class contains all the buttons that the user can click on when playing the game. It also has the implementations of these buttons, meaning all the code that allows the button to function.

**GameBoard.java**: This class contains the event listeners which connect the event of a mouse click to the desired functionality. It differentiates between left and right clicks which are essential to the functionality of the game. It also has the paintComponent function which implements all the GUI which allows the user to see the game on their screen.

## How to Run
Run the project by running the Game class in src/main/java/org/cis1200