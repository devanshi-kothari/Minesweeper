package org.cis1200.minesweeper;

public class Cell {

    private boolean isBomb;
    private boolean isFlagged;
    private boolean isRevealed;

    public Cell() {
        this.isBomb = false;
        this.isFlagged = false;
        this.isRevealed = false;
    }

    public Cell(String isBomb, String isFlagged, String isRevealed) {
        if (isBomb.equals("true")) {
            this.isBomb = true;
        } else {
            this.isBomb = false;
        }

        if (isFlagged.equals("true")) {
            this.isFlagged = true;
        } else {
            this.isFlagged = false;
        }

        if (isRevealed.equals("true")) {
            this.isRevealed = true;
        } else {
            this.isRevealed = false;
        }
    }

    /**Getter methods **/
    public boolean isBomb() {
        return isBomb;
    }


    public boolean isFlagged() {
        return isFlagged;
    }

    public boolean isRevealed() {
        return isRevealed;
    }


    /**Setter methods **/
    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }
}