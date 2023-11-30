package Helper;

/**
 * Move class represents a move in the Domineering game, consisting of two positions and the player making the move.
 * @author Laamiri Ouail & Sadik Hajar
 * @version 1.0
 * @since 2023-12-01
 */
public class Move {

    /**
     * The first position of the move.
     */
    private Position position1 = null;

    /**
     * The second position of the move.
     */
    private Position posotion2 = null;

    /**
     * The player making the move (1 or 2).
     */
    private byte player = 0;

    /**
     * Constructs a Move object with the specified positions and player.
     *
     * @param position1 The first position of the move.
     * @param position2 The second position of the move.
     * @param player    The player making the move.
     */
    public Move(Position position1, Position position2, byte player) {
        this.position1 = position1;
        this.posotion2 = position2;
        this.player = player;
    }

    /**
     * Gets the first position of the move.
     *
     * @return The first position of the move.
     */
    public Position getPosition1() {
        return position1;
    }

    /**
     * Gets the second position of the move.
     *
     * @return The second position of the move.
     */
    public Position getPosotion2() {
        return posotion2;
    }

    /**
     * Checks if the move has a specific position.
     *
     * @param position The position to check.
     * @return True if the move has the specified position, false otherwise.
     */
    public boolean hasPosition(Position position) {
        return position1.equals(position) || posotion2.equals(position);
    }

    /**
     * Gets the player making the move.
     *
     * @return The player making the move (1 or 2).
     */
    public byte getPlayer() {
        return player;
    }
}
