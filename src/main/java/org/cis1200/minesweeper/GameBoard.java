package org.cis1200.minesweeper;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Minesweeper ms; // model for the game
    private JLabel status; // current status text

    // Game constants
    public static final int BOARD_WIDTH = 400;
    public static final int BOARD_HEIGHT = 400;
    private Color color;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        ms = new Minesweeper(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();

                // updates the model given the coordinates of the LEFT mouseclick
                if (e.getButton() == MouseEvent.BUTTON1) {
                    ms.revealCell(p.y / 50, p.x / 50);

                }
                // updates the model given the coordinates of the RIGHT mouseclick
                if (e.getButton() == MouseEvent.BUTTON3) {
                    ms.addFlag(p.y / 50, p.x / 50);
                }

                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        ms.resetBoard();
        status.setText("New game started.");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    public void save() {
        ms.saveBombLocations();
        ms.saveFlaggedLocations();
        ms.saveRevealedLocations();
    }

    public void reload() {
        ms.reloadAll();
        repaint();
    }


    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        status.setText("You have " + Integer.toString(ms.getNumFlags()) + " flags left.");

        int gameStatus = ms.getGameOverState();
        if (gameStatus == -1) {
            status.setText("You lost. Try again.");
        } else if (gameStatus == 1) {
            status.setText("You win!");
        }
    }

    /**
     * Draws the game board.
     *
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    // this is your repaint function
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(50, 0, 50, 400);
        g.drawLine(100, 0, 100, 400);
        g.drawLine(150, 0, 150, 400);
        g.drawLine(200, 0, 200, 400);
        g.drawLine(250, 0, 250, 400);
        g.drawLine(300, 0, 300, 400);
        g.drawLine(350, 0, 350, 400);

        g.drawLine(0, 50, 400, 50);
        g.drawLine(0, 100, 400, 100);
        g.drawLine(0, 150, 400, 150);
        g.drawLine(0, 200, 400, 200);
        g.drawLine(0, 250, 400, 250);
        g.drawLine(0, 300, 400, 300);
        g.drawLine(0, 350, 400, 350);


        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell state = ms.getCell(row, col);
                if (state.isRevealed()) {
                    g.setColor(new Color(230,194,160,255));
                    g.fillRect(col * 50, row * 50, 49, 49);

                    if (state.isBomb()) {
                        g.setColor(Color.BLACK);
                        g.fillOval(col * 50, row * 50, 49, 49);
                    } else if (ms.getNumSurroundingBombs(row, col) == 1) {
                        g.setColor(new Color(29,119,210,255));
                        g.drawString(String.valueOf(1), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 2) {
                        g.setColor(new Color(56,142,60,255));
                        g.drawString(String.valueOf(2), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 3) {
                        g.setColor(new Color(211,48,47,255));
                        g.drawString(String.valueOf(3), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 4) {
                        g.setColor(new Color(123,32,162,255));
                        g.drawString(String.valueOf(4), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 5) {
                        g.setColor(new Color(249,149,26,255));
                        g.drawString(String.valueOf(5), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 6) {
                        g.setColor(new Color(1,151,167,255));
                        g.drawString(String.valueOf(6), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 7) {
                        g.setColor(new Color(67,67,67,255));
                        g.drawString(String.valueOf(7), col * 50 + 25, row * 50 + 25);
                    } else if (ms.getNumSurroundingBombs(row, col) == 8) {
                        g.setColor(new Color(157,157,157,255));
                        g.drawString(String.valueOf(8), col * 50 + 25, row * 50 + 25);
                    }
                } else {
                    g.setColor(new Color(170,215,82,255));
                    g.fillRect(col * 50, row * 50, 49, 49);

                    if (state.isFlagged()) {
                        g.setColor(new Color(241,55,9,255));
                        g.drawString("F", col * 50 + 25, row * 50 + 25);
                    }
                }
            }
        }
    }



    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
