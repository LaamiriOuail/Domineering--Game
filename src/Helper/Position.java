package Helper;

import UI.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Position class represents a position on a game board with X and Y coordinates.
 * It provides methods to check the validity of the position, get possible moves for a player,
 * and access the X and Y coordinates.
 */
public class Position {
    private int X;
    private int Y;

    /**
     * Constructs a new Position object with the specified X and Y coordinates.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Checks if the current position is valid on the game board.
     *
     * @return True if the position is valid, false otherwise.
     */
    public boolean isValid() {
        return this.X >= 0 && this.X < 7 && this.Y >= 0 && this.Y < 8;
    }

    /**
     * Gets a list of valid positions representing possible moves for the specified player
     * at the current position on a game board.
     *
     * @param player       The player identifier (1 or 2).
     * @param btns         A 2D array of Button objects representing the game board.
     * @param defaultColor The default background color to check against when determining valid moves.
     * @return A list of Position objects representing possible moves.
     */
    public List<Position> getPossibleMove(byte player, Button[][] btns,String defaultColor) {
        // One: left, right
        // Two: top, bottom
        List<Position> list = new ArrayList<>();
        Position pos = null;

        if (player == 2) {
            pos = new Position(this.X - 1, Y);
            if (pos.isValid() && Objects.equals(btns[pos.getX()][pos.getY()].getBackgroundColor(), defaultColor))
                list.add(pos);

            pos = new Position(this.X + 1, Y);
            if (pos.isValid() && Objects.equals(btns[pos.getX()][pos.getY()].getBackgroundColor(), defaultColor))
                list.add(pos);
        } else if (player == 1) {
            pos = new Position(this.X, Y - 1);
            if (pos.isValid() && Objects.equals(btns[pos.getX()][pos.getY()].getBackgroundColor(), defaultColor))
                list.add(pos);

            pos = new Position(this.X, Y + 1);
            if (pos.isValid() && Objects.equals(btns[pos.getX()][pos.getY()].getBackgroundColor(), defaultColor))
                list.add(pos);
        }

        return list;
    }

    /**
     * Gets the X coordinate of the position.
     *
     * @return The X coordinate.
     */
    public int getX() {
        return X;
    }

    /**
     * Gets the Y coordinate of the position.
     *
     * @return The Y coordinate.
     */
    public int getY() {
        return Y;
    }
    //docString
    public boolean isIn(List<Position> positions){
        boolean exist=false;
        for (var position:positions) {
            if(position.getY()==this.Y && position.getX()==this.X){
                exist=true;
                break;
            }

        }
        return exist;
    }
    /**
     * Returns a string representation of the Position object.
     *
     * @return A string representation of the form "Position{X=x, Y=y}".
     */
    @Override
    public String toString() {
        return "Position{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
