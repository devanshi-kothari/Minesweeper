=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. The first concept is 2D arrays. There is a 2D array that represents my game board. It
  is a 2D array of Cell objects, each of which stores whether the cell is revealed, whether
  the cell contains a bomb, and whether the cell is flagged.
  I believe that this is an appropriate use of 2D arrays because the game board is in a 2D
  array format and must store all of these values throughout the entire game.

  2. The next concept is recursion. This is implemented when the user clicks on an empty cell
  that is not surrounded by other bombs in order to reveal is. When this happens, recursion takes
  place to reveal other cells until empty cells that have surrounding bombs are revealed.

  3. The next concept is File I/O. This concept allows the user to save and reload the game
  after exiting. This is an appropriate use of the concept because it allows you to write the
  game state to different files and read the game state from these files to reconstruct your
  game board.

  4. The final concept is testable component. This allows you to do JUnit testing. This is
  an appropriate use of the concept because it allows you to test to make sure that each of your
  functions acts properly.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
    - There is a Cell class. This class stores whether each tile is revealed, is a bomb, or has a flagged.
    - There is a Minesweeper class. This class stores the entire game board, the total number of bombs, the
    number of flags that are left (after the user puts down flags), and the game state (whether you have won,
    lost, or are still continuing the game). This class also has all the methods that allows the game to run.
    - There is a RunMinesweeper class. This class contains all the buttons that the user can click on when
    playing the game. It also has the implementations of these buttons, meaning all the code that allows
    the button to function.
    - There is a GameBoard class. This class contains the event listeners which connect the event of a mouse
    click to the desired functionality. It differentiates between left and right clicks which are essential
    to the functionality of the game. It also has the paintComponent function which implements all
    the GUI which allows the user to see the game on their screen.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
    Yes, I had trouble with my checkWinner() function because my game was able to show when the user was
    losing the game, but not when they had won the game.
    I also had trouble with my File I/O implementation because I was able to save the game but not reload
    the game. This required lots of troubleshooting.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
    Yes, I believe that my design has good separation of functionality because none of the game logic
    relies on the GUI. My private states are also encapsulated well and can only be accessed or changed
    through the getter and setter methods.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
    N/A