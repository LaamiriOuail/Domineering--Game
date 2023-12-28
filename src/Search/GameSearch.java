package Search;

import Helper.Board;
import Helper.Move;

import java.util.List;
/**
 * Interface for defining game search functionality.
 * @author Laamiri Ouail & Hajar Sadik
 * @version 1.0
 * @since 2023-12-01
 */
public interface GameSearch {
    /**
     * Get the number of possible moves for the specified player.
     *
     * @param player The player for whom to get the number of possible moves.
     * @return The number of possible moves for the player.
     */
    byte getNumberOfPosibleMove(byte player);
    /**
     * Get the number of moves made by the specified player.
     *
     * @param player The player for whom to get the number of moves.
     * @return The number of moves made by the player.
     */
    byte getNumbreOfMoveByPlayer(byte player);
    /**
     * Get the next player to make a move.
     *
     * @return The next player to make a move.
     */
    byte getNextPlayer();
    /**
     * Get a list of valid moves for the specified player.
     *
     * @param player The player for whom to get valid moves.
     * @return A list of valid moves for the player.
     */
    List<Move> getValidMovesi(byte player);
    /**
     * Get a list of valid moves for the specified player based on the current state.
     *
     * @param state  The current state of the game.
     * @param player The player for whom to get valid moves.
     * @return A list of valid moves for the player.
     */
    List<int[]> getValidMoves(String[][] state,byte player);
    /**
     * Get a list of valid game states for the specified player.
     *
     * @param player The player for whom to get valid game states.
     * @return A list of valid game states for the player.
     */
    List<Board> getValideStates(byte player);
    /**
     * Check if a player has won the game.
     *
     * @return The winning player or 0 if no player has won.
     */
    byte playerWin();
    /**
     * Make a move on the game board.
     *
     * @param y      The y-coordinate of the move.
     * @param x      The x-coordinate of the move.
     * @param player The player making the move.
     */
    void makeMove(int y, int x, byte player);
    /**
     * Play a move on the game board.
     *
     * @param x The x-coordinate of the move.
     * @param y The y-coordinate of the move.
     * @return True if the move was played successfully, false otherwise.
     */
    boolean playMove(int x, int y);
    /**
     * Get the maximum value for a state in a minimax search.
     *
     * @param state  The current state of the game.
     * @param depth  The depth of the search.
     * @param alpha  The alpha value for pruning.
     * @param beta   The beta value for pruning.
     * @param move   The best move found so far.
     * @return An array containing the best move and its value.
     */
    int[] maxValue(String[][] state, int depth, int alpha, int beta, int[] move);
    /**
     * Get the minimum value for a state in a minimax search.
     *
     * @param state  The current state of the game.
     * @param depth  The depth of the search.
     * @param alpha  The alpha value for pruning.
     * @param beta   The beta value for pruning.
     * @param move   The best move found so far.
     * @return An array containing the best move and its value.
     */
    int[] minValue(String[][] state, int depth, int alpha, int beta, int[] move);
    /**
     * Perform a minimax search on the game state.
     *
     * @param state   The current state of the game.
     * @param depth   The depth of the search.
     * @param myMove  True if it's the current player's move, false otherwise.
     * @param alpha   The alpha value for pruning.
     * @param beta    The beta value for pruning.
     * @return An array containing the best move and its value.
     */
    int[] minmax(String[][] state, int depth, boolean myMove, int[] alpha, int[] beta);
    /**
     * Play a move on the game board for a helper move.
     *
     * @param x The x-coordinate of the helper move.
     * @param y The y-coordinate of the helper move.
     * @return True if the helper move was played successfully, false otherwise.
     */
    boolean playHelpMove(int x, int y);
    /**
     * Make a helper move on the game board.
     *
     * @param x      The x-coordinate of the helper move.
     * @param y      The y-coordinate of the helper move.
     * @param player The player making the helper move.
     */
    void makeHelpMove(int x, int y, byte player);
    /**
     * Calculate the heuristic value for a move.
     *
     * @param move An array containing the move coordinates and value.
     * @return The heuristic value for the move.
     */
    int heuristic(int[] move);
}


