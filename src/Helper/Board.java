package Helper;

/**
 * Represents the game board for the Domineering game.
 * @author Laamiri Ouail & Sadik Hajar
 * @version 1.0
 * @since 2023-12-01
 */
public class Board {

    /**
     * The 2D array representing the game board.
     */
    public String[][] board = null;

    /**
     * Constructs a Board object with the given 2D array.
     *
     * @param board The 2D array representing the game board.
     */
    public Board(String[][] board) {
        if (board.length == Configuration.row && board[0].length == Configuration.column) {
            this.setBoard(board);
        } else {
            System.out.println("Board: Size problem");
        }
    }

    /**
     * Default constructor that initializes the board from the Configuration buttons.
     */
    public Board() {
        this.setBoardFromButtons();
    }

    /**
     * Sets the game board with the given 2D array.
     *
     * @param board The 2D array representing the game board.
     */
    public void setBoard(String[][] board) {
        this.board = new String[Configuration.row][Configuration.column];
        for (short i = 0; i < board.length; i++) {
            for (short j = 0; j < board[0].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    /**
     * Sets the game board from the Configuration buttons.
     */
    public void setBoardFromButtons() {
        this.board = new String[Configuration.row][Configuration.column];
        for (short i = 0; i < board.length; i++) {
            for (short j = 0; j < board[0].length; j++) {
                this.board[i][j] = Configuration.buttons[i][j].getBackgroundColor();
            }
        }
    }

    /**
     * Generates a new game board based on the last board state, move coordinates, and player.
     *
     * @param lastBoard The last state of the game board.
     * @param x         The x-coordinate of the move.
     * @param y         The y-coordinate of the move.
     * @param player    The player making the move.
     * @return The new game board after the move.
     */
    public static String[][] generateBoard(String[][] lastBoard, int x, int y, int player) {
        String[][] board = new String[lastBoard.length][lastBoard[0].length];
        Position position;
        for (short i = 0; i < board.length; i++) {
            for (short j = 0; j < board[0].length; j++) {
                board[i][j] = lastBoard[i][j];
            }
        }
        if (player == 1) {
            position = new Position(x, y);
            if (position.isValid()) {
                board[x][y] = Configuration.player1Color;
            }
            position = new Position(x, y + 1);
            if (position.isValid()) {
                board[x][y + 1] = Configuration.player1Color;
            }
        } else if (player == 2) {
            position = new Position(x, y);
            if (position.isValid()) {
                board[x][y] = Configuration.player2Color;
            }
            position = new Position(x - 1, y);
            if (position.isValid()) {
                board[x - 1][y] = Configuration.player2Color;
            }
        }
        return board;
    }

    /**
     * Gets the current game board.
     *
     * @return The 2D array representing the game board.
     */
    public String[][] getBoard() {
        return board;
    }
}
