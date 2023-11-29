package Search;

import UI.Button;

public interface GameSearch {
    byte getNumberOfPosibleMove(byte player);
    byte getNumbreOfMoveByPlayer(byte player);
    byte getCurrentPlayer();

}
