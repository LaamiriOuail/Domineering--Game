package UI;

import Helper.Position;
import Helper.StringTableFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
/**
 * The Event class implements the ActionListener interface and handles button click events
 * for a user interface associated with the "Domineering" game.
 */
public class Event implements ActionListener {
    private StringTableFile file;
    private final String filename="data.txt";
    final String player1Color="#800080";
    final String player2Color="#000000";
    final String intermediateColor="#F5F5DC";
    final String defaultColor="#ffffff";
    List<Position> listOfValidPosition=null;
    private byte player=1;

    public void setMove(byte move) {
        this.move = move;
    }

    private byte move=0;
    private Button[][] buttons;
    private Label labelMessage=null;
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
        }
    }
    public void setLabel(Label label){
        this.labelMessage=label;
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
    /**
     * Calculates and returns the number of possible moves for the specified player on the game board.
     *
     * @param player The player identifier (1 or 2).
     * @return The number of possible moves for the specified player.
     */
    private byte getNumberOfPosibleMove(byte player){
        byte nbrPossibleMove=0;
        if(buttons!=null){
            if(player==1){
                for(byte i=0;i<buttons.length;i++){
                    for(byte j=0;j<buttons[0].length-1;j++){
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), defaultColor) && Objects.equals(buttons[i][j+1].getBackgroundColor(), defaultColor)){
                            nbrPossibleMove+=1;
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<buttons.length-1;i++){
                    for(byte j=0;j<buttons[0].length;j++){
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), defaultColor) && Objects.equals(buttons[i+1][j].getBackgroundColor(), defaultColor)){
                            nbrPossibleMove+=1;
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
        file=StringTableFile.getInstance(buttons);
        Button sourceButton = (Button) e.getSource();
        if(Objects.equals(sourceButton.getBackgroundColor(), defaultColor) || Objects.equals(sourceButton.getBackgroundColor(), intermediateColor)){
            String tooltip=sourceButton.getToolTipText();
            String[] pos=tooltip.split(",");
            int ligne=Integer.parseInt(pos[0]);
            int column=Integer.parseInt(pos[1]);
            Position position=new Position(ligne,column);
                if(move==0){
                        this.listOfValidPosition=position.getPossibleMove(player,this.buttons,this.defaultColor);
                        if(listOfValidPosition.size()==1) {
                                if(player==1){
                                    sourceButton.setBackgroundColor(player1Color);
                                    this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(player1Color);
                                    player=2;
                                }else if(player==2){
                                    sourceButton.setBackgroundColor(player2Color);
                                    this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(player2Color);
                                    player=1;
                                }
                            file.setBackgroundColors(buttons);
                            file.saveBackgroundColorToFile(filename);
                        }else if(listOfValidPosition.size()==2){
                            for (var positioni:listOfValidPosition) {
                                this.buttons[positioni.getX()][positioni.getY()].setBackgroundColor(intermediateColor);
                            }
                            if(player==1){
                                sourceButton.setBackgroundColor(player1Color);
                            }else if(player==2){
                                sourceButton.setBackgroundColor(player2Color);
                            }
                            move=1;
                        }

                }else if(move==1 && position.isIn(listOfValidPosition)){
                    move=0;
                    for (var positioni:listOfValidPosition) {
                        this.buttons[positioni.getX()][positioni.getY()].setBackgroundColor(defaultColor);
                    }
                    if(player==1){
                        sourceButton.setBackgroundColor(player1Color);
                        player=2;
                    }else if(player==2){
                        sourceButton.setBackgroundColor(player2Color);
                        player=1;
                    }
                    file.setBackgroundColors(buttons);
                    file.saveBackgroundColorToFile(filename);
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
    }
}
