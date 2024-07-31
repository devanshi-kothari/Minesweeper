package org.cis1200.minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class MinesweeperTest {

    @Test
    public void test() {
        assertNotEquals("CIS 120", "CIS 160");
    }

    @Test
    public void testConstructorAndReset() {
        Minesweeper ms = new Minesweeper();
        assertEquals(ms.getNumBombs(), 10);
        assertEquals(ms.getBoard().length, 8);
        assertEquals(ms.getBoard()[0].length, 8);
        assertEquals(ms.getNumFlags(), 10);
        assertEquals(ms.getGameOverState(), 0);

        int bombCounter = 0;
        for (int i = 0; i < ms.getBoard().length; i++) {
            for (int j = 0; j < ms.getBoard()[0].length; j++) {
                if (ms.getCell(i, j).isBomb()) {
                    bombCounter++;
                }
            }
        }
        assertEquals(bombCounter, 10);
    }

    @Test
    public void testNumSurroundingBombs() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();
        assertEquals(ms.getNumSurroundingBombs(3, 3), 2);
        assertEquals(ms.getNumSurroundingBombs(1, 1), 1);
        assertEquals(ms.getNumSurroundingBombs(3, 2), 3);
        assertEquals(ms.getNumSurroundingBombs(5, 6), 3);
        assertEquals(ms.getNumSurroundingBombs(7, 0), 0);
    }

    @Test
    public void testRevealCellOnBombs() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        ms.revealCell(0, 0);
        assertEquals(ms.getCell(0, 0).isRevealed(), true); // bomb is revealed
        assertEquals(ms.getCell(7, 0).isRevealed(), true); // other cell is revealed
        assertEquals(ms.getGameOverState(), -1);
    }

    @Test
    public void testRevealCellOnEmptyCellSurroundedByBombs() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        ms.revealCell(5, 6);
        assertEquals(ms.getCell(5, 6).isRevealed(), true); // cell is revealed
        assertEquals(ms.getCell(7, 0).isRevealed(), false); // other cell is not revealed
        assertEquals(ms.getGameOverState(), 0);
    }

    @Test
    public void testRevealCellOnEmptyCellNotSurroundedByBombs() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        ms.revealCell(7, 0);
        assertEquals(ms.getCell(7, 0).isRevealed(), true); // cell is revealed
        assertEquals(ms.getCell(7, 1).isRevealed(), true); // surrounding empty cell is revealed
        assertEquals(ms.getCell(6, 1).isRevealed(), true);
            // surrounding empty cell next to bomb is revealed
        assertEquals(ms.getCell(5, 0).isRevealed(), false);
            // empty cell beyond recursive call is not revealed
        assertEquals(ms.getCell(5, 1).isRevealed(), false); // nearest bomb is not revealed
        assertEquals(ms.getGameOverState(), 0);
    }

    @Test
    public void testRevealCellOnFlaggedCell() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        ms.revealCell(7, 7);
        assertEquals(ms.getCell(7, 7).isRevealed(), false); // flagged cell is not revealed
    }

    @Test
    public void testAddFlagOnRevealedCell() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        ms.revealCell(0, 1);
        ms.addFlag(0, 1);
        assertEquals(ms.getCell(0, 1).isRevealed(), true); //  cell is revealed
        assertEquals(ms.getCell(0, 1).isFlagged(), false); //  revealed cell is not flagged
    }

    @Test
    public void testAddFlag() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        ms.addFlag(0, 1);
        assertEquals(ms.getCell(0, 1).isFlagged(), true); //  cell is flagged
        ms.addFlag(0, 1);
        assertEquals(ms.getCell(0, 1).isFlagged(), false); //  same cell is not flagged
    }

    @Test
    public void testCheckWinner() {
        Minesweeper ms = new Minesweeper();
        ms.forTesting();

        // this removes the flag that was already there which is beneficial
        // for the following double for loop that reveals all the cells except for bombs
        ms.addFlag(7, 7);

        for (int row = 0; row < ms.getBoard().length; row++) {
            for (int col = 0; col < ms.getBoard()[0].length; col++) {
                if (!ms.getBoard()[row][col].isBomb()) {
                    ms.getBoard()[row][col].setRevealed(true);
                }
            }
        }

        ms.addFlag(2, 3);
        ms.addFlag(5, 1);
        ms.addFlag(4, 5);
        ms.addFlag(6, 6);
        ms.addFlag(5, 7);
        ms.addFlag(3, 1);
        ms.addFlag(0, 0);
        ms.addFlag(7, 4);
        ms.addFlag(1, 5);
        ms.addFlag(4, 3);

        assertEquals(ms.getGameOverState(), 1);

    }
}
