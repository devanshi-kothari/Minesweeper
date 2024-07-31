package org.cis1200.minesweeper;

import java.io.*;

public class Minesweeper {

    private Cell[][] board;
    private int numBombs;
    private int numFlags;
    private int gameOver;

    public Minesweeper() {
        this.board = new Cell[8][8];
        this.numBombs = 10;
        resetBoard();
    }

    public void resetBoard() {
        this.numFlags = 10;
        this.gameOver = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = new Cell();
            }
        }
        int i = 0;
        while (i < numBombs) {
            int r = (int) (Math.random() * 8);
            int c = (int) (Math.random() * 8);
            if (!board[r][c].isBomb()) {
                board[r][c].setBomb(true);
                i++;
            }
        }
        System.out.println("reached this point");
    }

    public int getNumSurroundingBombs(int r, int c) {
        int counter = 0;

        int leftBound = c - 1;
        int rightBound = c + 1;
        int topBound = r - 1;
        int bottomBound = r + 1;

        if (leftBound < 0) {
            leftBound = 0;
        }
        if (rightBound >= board[0].length) {
            rightBound = board[0].length - 1;
        }
        if (topBound < 0) {
            topBound = 0;
        }
        if (bottomBound >= board.length) {
            bottomBound = board.length - 1;
        }

        for (int col = leftBound; col <= rightBound; col++) {
            for (int row = topBound; row <= bottomBound; row++) {
                if (board[row][col].isBomb()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int revealCell(int r, int c) {
        if (!board[r][c].isRevealed() && !board[r][c].isFlagged()) {
            if (board[r][c].isBomb()) {
                gameOver = -1;
                board[r][c].setRevealed(true);

                // reveal all cells
                for (int row = 0; row < board.length; row++) {
                    for (int col = 0; col < board[0].length; col++) {
                        revealCell(row, col);
                    }
                }
            } else if (!board[r][c].isBomb() && getNumSurroundingBombs(r, c) > 0 &&
                    getNumSurroundingBombs(r, c) <= 8) {
                // condition that cell is empty but surrounded by at least 1 bomb

                board[r][c].setRevealed(true);
                if (checkWinner()) {
                    gameOver = 1;
                }
                return getNumSurroundingBombs(r, c);
            } else {
                // condition that cell is empty is not surrounded by a bomb
                board[r][c].setRevealed(true);

                int leftBound = c - 1;
                int rightBound = c + 1;
                int topBound = r - 1;
                int bottomBound = r + 1;

                if (leftBound < 0) {
                    leftBound = 0;
                }
                if (rightBound >= board[0].length) {
                    rightBound = board[0].length - 1;
                }
                if (topBound < 0) {
                    topBound = 0;
                }
                if (bottomBound >= board.length) {
                    bottomBound = board.length - 1;
                }
                for (int row = topBound; row <= bottomBound; row++) {
                    for (int col = leftBound; col <= rightBound; col++) {
                        if (row != r || col != c) {
                            revealCell(row, col);
                        }
                    }
                }

                if (checkWinner()) {
                    gameOver = 1;
                }
                return getNumSurroundingBombs(r, c);
            }
        }
        return -1;
    }

    public void addFlag(int r, int c) {
        if (!board[r][c].isRevealed()) {
            if (board[r][c].isFlagged()) {
                board[r][c].setFlagged(false);
                numFlags++;
            } else {
                board[r][c].setFlagged(true);
                numFlags--;
                if (checkWinner()) {
                    gameOver = 1;
                }
            }
        }
    }

    public boolean checkWinner() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].isBomb() && !board[r][c].isFlagged()) {
                    return false;
                }
                if (!board[r][c].isBomb() && !board[r][c].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell[][] getBoard() {
        return board;
    }
    public Cell getCell(int r, int c) {
        return board[r][c];
    }

    public int getGameOverState() {
        return gameOver;
    }

    public int getNumBombs() {
        return numBombs;
    }

    public int getNumFlags() {
        return numFlags;
    }

    /**
     * Below methods are for File I/O
     * These methods are not tested in the MinesweeperTest.java class
     */
    public void saveBombLocations() {
        try {
            FileWriter file = new FileWriter("files/savedBombLocations.txt");
            BufferedWriter bw = new BufferedWriter(file);
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    bw.write(board[row][col].isBomb() + ",");
                }
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void saveRevealedLocations() {
        try {
            FileWriter file = new FileWriter("files/savedRevealedLocations.txt");
            BufferedWriter bw = new BufferedWriter(file);
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    bw.write(board[row][col].isRevealed() + ",");
                }
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void saveFlaggedLocations() {
        try {
            FileWriter file = new FileWriter("files/savedFlaggedLocations.txt");
            BufferedWriter bw = new BufferedWriter(file);
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    bw.write(board[row][col].isFlagged() + ",");
                }
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void reloadAll() {
        try {
            BufferedReader loadBombs = new BufferedReader(
                    new FileReader("files/savedBombLocations.txt"));
            BufferedReader loadRevealed = new BufferedReader(
                    new FileReader("files/savedRevealedLocations.txt"));
            BufferedReader loadFlags = new BufferedReader(
                    new FileReader("files/savedFlaggedLocations.txt"));

            for (int row = 0; row < board.length; row++) {
                String rowBombs = loadBombs.readLine();
                String[] eachRowBombs = rowBombs.split(",");

                String rowRevealed = loadRevealed.readLine();
                String[] eachRowRevealed = rowRevealed.split(",");

                String rowFlags = loadFlags.readLine();
                String[] eachRowFlags = rowFlags.split(",");

                for (int col = 0; col < board[0].length; col++) {
                    board[row][col] = new Cell(eachRowBombs[col], eachRowFlags[col],
                            eachRowRevealed[col]);
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * The below method is to make sure that my test cases run properly because each
     * Minesweeper object has a random arrangement of bombs.
     * This method is only utilized in the test cases in the MinesweeperTest.java class but
     * is not actually tested itself.
     */
    public void forTesting() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new Cell();
            }
        }
        board[2][3].setBomb(true);
        board[5][1].setBomb(true);
        board[4][5].setBomb(true);
        board[6][6].setBomb(true);
        board[5][7].setBomb(true);
        board[3][1].setBomb(true);
        board[0][0].setBomb(true);
        board[7][4].setBomb(true);
        board[1][5].setBomb(true);
        board[4][3].setBomb(true);

        board[7][7].setFlagged(true);
    }

}
