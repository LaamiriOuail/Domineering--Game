package Search;

import Helper.Board;
import Helper.Configuration;
import Helper.Move;
import Helper.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DomineeringSearch implements GameSearch {
    // Singleton instance
    private static DomineeringSearch domineeringSearchInstance=null;
    /**
     * Private constructor for Singleton pattern.
     */
    private DomineeringSearch() {

    }
    /**
     * Get the instance of DomineeringSearch using the Singleton pattern.
     *
     * @return The DomineeringSearch instance.
     */
    public static DomineeringSearch getInstance(){
        if(domineeringSearchInstance==null){
            domineeringSearchInstance=new DomineeringSearch();
        }else{
        }
        return domineeringSearchInstance;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public byte getNumbreOfMoveByPlayer(byte player){

        return (byte) this.getValidMovesi(player).size();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Move> getValidMovesi(byte player){
        List<Move> groupMove=new ArrayList<>();
        if(Configuration.buttons!=null){
            boolean exist=false;
            if(player==1){
                for(byte i=0;i<Configuration.buttons.length;i++){
                    for(byte j=0;j<Configuration.buttons[0].length-1;j++){
                        exist=false;
                        if(Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.player1Color) && Objects.equals(Configuration.buttons[i][j+1].getBackgroundColor(), Configuration.player1Color)){
                            for(byte k=0;k<groupMove.size();k++){
                                if(groupMove.get(k).hasPosition(new Position(i,j)) || groupMove.get(k).hasPosition(new Position(i,j+1))){
                                    exist=true;
                                    break;
                                }
                            }
                            if(!exist){
                                groupMove.add(new Move(new Position(i,j),new Position(i,j+1), (byte) 1));
                            }
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<Configuration.buttons.length-1;i++){
                    for(byte j=0;j<Configuration.buttons[0].length;j++){
                        exist=false;
                        if(Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.player2Color) && Objects.equals(Configuration.buttons[i+1][j].getBackgroundColor(), Configuration.player2Color)){
                            for(byte k=0;k<groupMove.size();k++){
                                if(groupMove.get(k).hasPosition(new Position(i,j)) || groupMove.get(k).hasPosition(new Position(i+1,j))){
                                    exist=true;
                                    break;
                                }
                            }
                            if(!exist){
                                groupMove.add(new Move(new Position(i,j),new Position(i+1,j), (byte) 1));
                            }
                        }
                    }
                }
            }
        }
        return groupMove;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<int[]> getValidMoves(String[][] state,byte player) {
        List<int[]> validMoves = new ArrayList<>();
        if (Configuration.buttons != null) {
            if (player == 1) {
                for (byte i = 0; i < Configuration.buttons.length; i++) {
                    for (byte j = 0; j < Configuration.buttons[0].length - 1; j++) {
                        if (Objects.equals(state[i][j], Configuration.defaultColor) && Objects.equals(state[i][j + 1], Configuration.defaultColor)) {
                            validMoves.add(new int[]{i, j, 1});
                        }
                    }
                }
            } else if (player == 2) {
                for (byte i = 0; i < Configuration.buttons.length - 1; i++) {
                    for (byte j = 0; j < Configuration.buttons[0].length; j++) {
                        if (Objects.equals(state[i][j], Configuration.defaultColor) && Objects.equals(state[i + 1][j], Configuration.defaultColor)) {
                            validMoves.add(new int[]{i, j, 2});
                        }
                    }
                }
            }
        }
        return validMoves;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Board> getValideStates(byte player){
        List<Move> groupMove=new ArrayList<>();
        List<Board> groupBords=new ArrayList<>();
        if(Configuration.buttons!=null){
            boolean exist=false;
            if(player==1){
                for(byte i=0;i<Configuration.buttons.length;i++){
                    for(byte j=0;j<Configuration.buttons[0].length-1;j++){
                        exist=false;
                        if(Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.player1Color) && Objects.equals(Configuration.buttons[i][j+1].getBackgroundColor(), Configuration.player1Color)){
                            for(byte k=0;k<groupMove.size();k++){
                                if(groupMove.get(k).hasPosition(new Position(i,j)) || groupMove.get(k).hasPosition(new Position(i,j+1))){
                                    exist=true;
                                    break;
                                }
                            }
                            if(!exist){
                                groupMove.add(new Move(new Position(i,j),new Position(i,j+1),(byte) player));
                                Board board=new Board();
                                String[][] boardS=board.getBoard();
                                boardS[i][j]=Configuration.player1Color;
                                boardS[i][j+1]=Configuration.player1Color;
                                groupBords.add(new Board(boardS));
                            }
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<Configuration.buttons.length-1;i++){
                    for(byte j=0;j<Configuration.buttons[0].length;j++){
                        exist=false;
                        if(Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.player2Color) && Objects.equals(Configuration.buttons[i+1][j].getBackgroundColor(), Configuration.player2Color)){
                            for(byte k=0;k<groupMove.size();k++){
                                if(groupMove.get(k).hasPosition(new Position(i,j)) || groupMove.get(k).hasPosition(new Position(i+1,j))){
                                    exist=true;
                                    break;
                                }
                            }
                            if(!exist){
                                groupMove.add(new Move(new Position(i,j),new Position(i+1,j),(byte) player));
                                Board board=new Board();
                                String[][] boardS=board.getBoard();
                                boardS[i][j]=Configuration.player1Color;
                                boardS[i+1][j]=Configuration.player1Color;
                                groupBords.add(new Board(boardS));
                            }
                        }
                    }
                }
            }
        }
        return groupBords;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public byte getNextPlayer(){
        if(this.getNumbreOfMoveByPlayer((byte)1)==this.getNumbreOfMoveByPlayer((byte)2)){
            return (byte)1;
        }else if(this.getNumbreOfMoveByPlayer((byte)1)>this.getNumbreOfMoveByPlayer((byte)2)){
            return (byte)2;
        }else{
            return (byte)1;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public byte getNumberOfPosibleMove(byte player){
        byte nbrPossibleMove = 0;
        if (Configuration.buttons != null) {
            if (player == 1) {
                for (byte i = 0; i < Configuration.buttons.length; i++) {
                    for (byte j = 0; j < Configuration.buttons[0].length - 1; j++) {
                        if (Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.defaultColor) && Objects.equals(Configuration.buttons[i][j + 1].getBackgroundColor(), Configuration.defaultColor)) {
                            nbrPossibleMove += 1;
                        }
                    }
                }
            } else if (player == 2) {
                for (byte i = 0; i < Configuration.buttons.length - 1; i++) {
                    for (byte j = 0; j < Configuration.buttons[0].length; j++) {
                        if (Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.defaultColor) && Objects.equals(Configuration.buttons[i + 1][j].getBackgroundColor(), Configuration.defaultColor)) {
                            nbrPossibleMove += 1;
                        }
                    }
                }
            }
        }
        return nbrPossibleMove;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public byte playerWin(){
        if(this.getNumberOfPosibleMove((byte)1)==0 && this.getNumberOfPosibleMove((byte)2)>0){
            return 2;
        }else if(this.getNumberOfPosibleMove((byte)2)==0 && this.getNumberOfPosibleMove((byte)1)>0){
            return 1;
        }else if(this.getNumberOfPosibleMove((byte)2)==0 && this.getNumberOfPosibleMove((byte)1)==0){
            return -1;
        }
        else{
            return 0;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void makeMove(int x, int y, byte player) {
        if (player == 1) {
            Configuration.buttons[x][y].setBackgroundColor(Configuration.player1Color);
            Configuration.buttons[x][y+1].setBackgroundColor(Configuration.player1Color);
        } else if (player == 2) {
            Configuration.buttons[x][y].setBackgroundColor(Configuration.player2Color);
            Configuration.buttons[x+1][y].setBackgroundColor(Configuration.player2Color);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void makeHelpMove(int x, int y, byte player) {
        if (player == 1) {
            Configuration.buttons[x][y].setBackgroundColor(Configuration.intermediateColor);
            Configuration.buttons[x][y+1].setBackgroundColor(Configuration.intermediateColor);
            Configuration.helpMove=new Move(new Position(x,y),new Position(x,y+1),Configuration.player);
        } else if (player == 2) {
            Configuration.buttons[x][y].setBackgroundColor(Configuration.intermediateColor);
            Configuration.buttons[x+1][y].setBackgroundColor(Configuration.intermediateColor);
            Configuration.helpMove=new Move(new Position(x,y),new Position(x+1,y),Configuration.player);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean playHelpMove(int x, int y) {
        Position position=new Position(x,y);
        if (!position.isValid()) {
            System.out.println("Invalid move");
            return false;
        }
        this.makeHelpMove(x,y,this.getNextPlayer());
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean playMove(int x, int y) {
        Position position=new Position(x,y);
        if (!position.isValid()) {
            System.out.println("Invalid move");
            return false;
        }
        this.makeMove(x,y,this.getNextPlayer());
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int heuristic(int[] move) {
        int safeMovesC = 0;
        int safeMovesH = 0;
        int horizontal = this.getNumberOfPosibleMove((byte) 1);
        int vertical = this.getNumberOfPosibleMove((byte) 2);
        int centerX = 0;
        int centerO = 0;
        int empty = 0;
        int asym = 0;

        double coef = (empty + 2) * 100.0 / (Configuration.row * Configuration.column);

        if (coef > Configuration.asymPercentFloor && coef < Configuration.asymPercentCeil) {
            if (move[2] == 1) {
                if (move[1] % 2 == 0) {
                    asym = 1;
                }
            } else {
                if (move[0] % 2 == 0) {
                    asym = 1;
                }
            }
        }

        int dist = 0;
        if (Configuration.asymMove != 0) {
            if (move[2] == Configuration.asymMove) {
                dist = (int) Math.sqrt(Math.pow(move[1] - Configuration.asymMove, 2) + Math.pow(move[0] - Configuration.asymMove, 2));
                if (dist > 0.95 && dist < 1.05) {
                    dist = 10;
                }
            }
        }

        return (horizontal - vertical) + safeMovesH * Configuration.safeMovesCoef + Configuration.column * (Configuration.row - 1) + Configuration.row * (Configuration.column - 1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int[] maxValue(String[][] state, int depth, int alpha, int beta, int[] move) {
        List<int[]> nextStates = getValidMoves(state, this.getNextPlayer());
        if (depth == 0 || nextStates.isEmpty()) {
            return new int[]{heuristic(move), move[0], move[1]};
        } else {
            for (int[] s : nextStates) {
                int[] result = minValue(Board.generateBoard(state, s[0], s[1], s[2]), depth - 1, alpha, beta, s.clone());
                if (result[0] > alpha) {
                    alpha = result[0];
                    move[0] = s[0];
                    move[1] = s[1];
                }
                if (alpha >= beta) {
                    return new int[]{beta, move[0], move[1]};
                }
            }
            return new int[]{alpha, move[0], move[1]};
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int[] minValue(String[][] state, int depth, int alpha, int beta, int[] move) {
        List<int[]> nextStates = getValidMoves(state, this.getNextPlayer());
        if (depth == 0 || nextStates.isEmpty()) {
            return new int[]{heuristic(move), move[0], move[1]};
        } else {
            for (int[] s : nextStates) {
                int[] result = maxValue(Board.generateBoard(state, s[0], s[1], s[2]), depth - 1, alpha, beta, s.clone());
                beta = Math.min(beta, result[0]);
                if (beta <= alpha) {
                    return new int[]{alpha, move[0], move[1]};
                }
            }
            return new int[]{beta, move[0], move[1]};
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int[] minmax(String[][] state, int depth, boolean myMove, int[] alpha, int[] beta) {
        int[] move = myMove ? maxValue(state, depth, alpha[0], beta[0], new int[]{0, 0, 0}) :
                minValue(state, depth, alpha[0], beta[0], new int[]{0, 0, 0});
        return move;
    }

}
