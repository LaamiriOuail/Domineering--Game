package UI;

import Game.DomineeringGame;
import Helper.Configuration;
import Helper.Move;
import Helper.Position;
import Helper.StringTableFile;
import Search.DomineeringSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The Event class implements the ActionListener interface and handles button click events
 * for a user interface associated with the "Domineering" game.
 */
public class Event implements ActionListener {
    private StringTableFile file;

    List<Position> listOfValidPosition=null;
    private Label labelMessage=null;
    private byte player=1;
    private byte machine=0;
    private DomineeringSearch domineeringSearch=null;
    public void setMove(byte move) {
        this.move = move;
    }
    private byte move=0;
    private Button[][] buttons;
    /**
     * Sets the array of buttons for the game board.
     *
     * @param buttonsArr A 2D array of Button objects representing the game board.
     */
    public void setButtons(Button[][] buttonsArr){
        if(buttonsArr!=null){
            this.buttons=new Button[buttonsArr.length][buttonsArr[0].length];
            for (int i = 0; i < buttonsArr.length; i++) {
                for (int j = 0; j < buttonsArr[0].length; j++) {
                    // Assign the reference of the existing button to the new array
                    this.buttons[i][j] = buttonsArr[i][j];
                }
            }
            domineeringSearch=DomineeringSearch.getInstance(buttons);
        }
    }
    public void setLabelMessage(Label labelMessage){
        this.labelMessage=labelMessage;
    }

    public void toEnableBtns(boolean enable){
        if(buttons!=null){
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[0].length; j++) {
                    this.buttons[i][j].toEnabled(enable);
                }
            }
        }
    }
    private byte getNumbreOfMoveByPlayer(byte player){
        ArrayList<Move> groupMove=new ArrayList<>();
        if(buttons!=null){
            boolean exist=false;
            if(player==1){
                for(byte i=0;i<buttons.length;i++){
                    for(byte j=0;j<buttons[0].length-1;j++){
                        exist=false;
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), Configuration.player1Color) && Objects.equals(buttons[i][j+1].getBackgroundColor(), Configuration.player1Color)){
                            for(byte k=0;k<groupMove.size();k++){
                                if(groupMove.get(k).hasPosition(new Position(i,j)) || groupMove.get(k).hasPosition(new Position(i,j+1))){
                                    exist=true;
                                    break;
                                }
                            }
                            if(!exist){
                                groupMove.add(new Move(new Position(i,j),new Position(i,j+1)));
                            }
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<buttons.length-1;i++){
                    for(byte j=0;j<buttons[0].length;j++){
                        exist=false;
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), Configuration.player2Color) && Objects.equals(buttons[i+1][j].getBackgroundColor(), Configuration.player2Color)){
                            for(byte k=0;k<groupMove.size();k++){
                                if(groupMove.get(k).hasPosition(new Position(i,j)) || groupMove.get(k).hasPosition(new Position(i+1,j))){
                                    exist=true;
                                    break;
                                }
                            }
                            if(!exist){
                                groupMove.add(new Move(new Position(i,j),new Position(i+1,j)));
                            }
                        }
                    }
                }
            }
        }
        return (byte) groupMove.size();
    }

    private byte getCurrentPlayer(){
        if(this.getNumbreOfMoveByPlayer((byte)1)==this.getNumbreOfMoveByPlayer((byte)2)){
            return (byte)1;
        }else if(this.getNumbreOfMoveByPlayer((byte)1)>this.getNumbreOfMoveByPlayer((byte)2)){
            return (byte)2;
        }else{
            return (byte)1;
        }
    }
    /**
     * Calculates and returns the number of possible moves for the specified player on the game board.
     *
     * @param player The player identifier (1 or 2).
     * @return The number of possible moves for the specified player.
     */
    private byte getNumberOfPosibleMove(byte player){
        byte nbrPossibleMove = 0;
        if (buttons != null) {
            if (player == 1) {
                for (byte i = 0; i < buttons.length; i++) {
                    for (byte j = 0; j < buttons[0].length - 1; j++) {
                        if (Objects.equals(buttons[i][j].getBackgroundColor(), Configuration.defaultColor) && Objects.equals(buttons[i][j + 1].getBackgroundColor(), Configuration.defaultColor)) {
                            nbrPossibleMove += 1;
                        }
                    }
                }
            } else if (player == 2) {
                for (byte i = 0; i < buttons.length - 1; i++) {
                    for (byte j = 0; j < buttons[0].length; j++) {
                        if (Objects.equals(buttons[i][j].getBackgroundColor(), Configuration.defaultColor) && Objects.equals(buttons[i + 1][j].getBackgroundColor(), Configuration.defaultColor)) {
                            nbrPossibleMove += 1;
                        }
                    }
                }
            }
        }
        return nbrPossibleMove;
    }
    private byte playerWin(){
        if(this.getNumberOfPosibleMove((byte)1)==0 && this.getNumberOfPosibleMove((byte)2)>0){
            return 2;
        }else if(this.getNumberOfPosibleMove((byte)2)==0 && this.getNumberOfPosibleMove((byte)1)>0){
            return 1;
        }else{
            return 0;
        }
    }
    /**
     * Handles button click events, making moves in the "Domineering" game.
     *
     * @param e The ActionEvent object representing the button click event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(labelMessage!=null){
            if (!Objects.equals(labelMessage.getText(), "")) {
                labelMessage.setText("");
                labelMessage.toVisible(false);
                DomineeringGame.getInstance().finalizeSauvgardedFrame();
            }
        }
        Position.setRowColumn(buttons.length,buttons[0].length);
        file=StringTableFile.getInstance(buttons);
        player=this.getCurrentPlayer();
        Button sourceButton = (Button) e.getSource();
        if(player!=machine){
            if(Objects.equals(sourceButton.getBackgroundColor(), Configuration.defaultColor) || Objects.equals(sourceButton.getBackgroundColor(), Configuration.intermediateColor)){
                String tooltip=sourceButton.getToolTipText();
                String[] pos=tooltip.split(",");
                int ligne=Integer.parseInt(pos[0]);
                int column=Integer.parseInt(pos[1]);
                Position position=new Position(ligne,column);
                if(move==0){
                    this.listOfValidPosition=position.getPossibleMove(player,this.buttons,Configuration.defaultColor);
                    if(listOfValidPosition.size()==1) {
                        if(player==1){
                            sourceButton.setBackgroundColor(Configuration.player1Color);
                            this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(Configuration.player1Color);
                            player=2;
                        }else if(player==2){
                            sourceButton.setBackgroundColor(Configuration.player2Color);
                            this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(Configuration.player2Color);
                            player=1;
                        }
                        file.setBackgroundColors(buttons);
                        file.saveBackgroundColorToFile();
                    }else if(listOfValidPosition.size()==2){
                        for (var positioni:listOfValidPosition) {
                            this.buttons[positioni.getX()][positioni.getY()].setBackgroundColor(Configuration.intermediateColor);
                        }
                        if(player==1){
                            sourceButton.setBackgroundColor(Configuration.player1Color);
                        }else if(player==2){
                            sourceButton.setBackgroundColor(Configuration.player2Color);
                        }
                        move=1;
                    }

                }else if(move==1 && position.isIn(listOfValidPosition)){
                    move=0;
                    for (var positioni:listOfValidPosition) {
                        this.buttons[positioni.getX()][positioni.getY()].setBackgroundColor(Configuration.defaultColor);
                    }
                    if(player==1){
                        sourceButton.setBackgroundColor(Configuration.player1Color);
                        player=2;
                    }else if(player==2){
                        sourceButton.setBackgroundColor(Configuration.player2Color);
                        player=1;
                    }
                    file.setBackgroundColors(buttons);
                    file.saveBackgroundColorToFile();
                }
                if(this.playerWin()==1){
                    labelMessage.toVisible(true);
                    labelMessage.setText("PLayer 1 win");
                    this.toEnableBtns(false);
                }else if(this.playerWin()==2){
                    labelMessage.toVisible(true);
                    labelMessage.setText("PLayer 2 win");
                    this.toEnableBtns(false);
                }else {
                    labelMessage.toVisible(false);
                    labelMessage.setText("");
                    this.toEnableBtns(true);
                }
            }
        }else{
            //MinMax alpha beta choice the perfect move to win
            if(player==1){
                player=2;
            }else if(player==2){
                player=1;
            }
        }
    }

    public void setPlayer(byte player) {
        this.player = player;
    }

    public byte getPlayer() {
        return player;
    }

    public byte getMove() {
        return move;
    }
}
