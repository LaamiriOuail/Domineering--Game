package Search;

import Helper.Board;
import Helper.Move;
import UI.Button;

import java.util.List;

public interface GameSearch {
    byte getNumberOfPosibleMove(byte player);
    byte getNumbreOfMoveByPlayer(byte player);
    byte getNextPlayer();
    List<Move> getValidMovesi(byte player);
    List<int[]> getValidMoves(String[][] state,byte player);
    List<Board> getValideStates(byte player);
    byte playerWin();
    void makeMove(int y, int x, byte player);
    boolean playMove(int x, int y);
    int[] maxValue(String[][] state, int depth, int alpha, int beta, int[] move);
    int[] minValue(String[][] state, int depth, int alpha, int beta, int[] move);
    int[] minmax(String[][] state, int depth, boolean myMove, int[] alpha, int[] beta);
    boolean playHelpMove(int x, int y);
    void makeHelpMove(int x, int y, byte player);
    int heuristic(int[] move);
}


