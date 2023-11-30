package UI;

import Game.DomineeringGame;
import Helper.*;
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

    private DomineeringSearch domineeringSearch=null;


    public void setLabelMessage(Label labelMessage){
        this.labelMessage=labelMessage;
    }

    public void toEnableBtns(boolean enable){
        if(Configuration.buttons!=null){
            for (int i = 0; i < Configuration.buttons.length; i++) {
                for (int j = 0; j < Configuration.buttons[0].length; j++) {
                    Configuration.buttons[i][j].toEnabled(enable);
                }
            }
        }
    }
    public void makeMove(Button sourceButton){
        if(Objects.equals(sourceButton.getBackgroundColor(), Configuration.defaultColor) || Objects.equals(sourceButton.getBackgroundColor(), Configuration.intermediateColor)){
            String tooltip=sourceButton.getToolTipText();
            String[] pos=tooltip.split(",");
            int ligne=Integer.parseInt(pos[0]);
            int column=Integer.parseInt(pos[1]);
            Position position=new Position(ligne,column);
            if(Configuration.move==0){
                this.listOfValidPosition=position.getPossibleMove(Configuration.player,Configuration.buttons,Configuration.defaultColor);
                if(listOfValidPosition.size()==1) {
                    if(Configuration.player==1){
                        sourceButton.setBackgroundColor(Configuration.player1Color);
                        Configuration.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(Configuration.player1Color);
                        Configuration.player=2;
                    }else if(Configuration.player==2){
                        sourceButton.setBackgroundColor(Configuration.player2Color);
                        Configuration.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(Configuration.player2Color);
                        Configuration.player=1;
                    }
                    Configuration.intermediatePosition=null;
                    //file.setBackgroundColors();
                    //file.saveBackgroundColorToFile();
                }else if(listOfValidPosition.size()==2){
                    Configuration.intermediatePosition=position;
                    for (var positioni:listOfValidPosition) {
                        Configuration.buttons[positioni.getX()][positioni.getY()].setBackgroundColor(Configuration.intermediateColor);
                    }
                    if(Configuration.player==1){
                        sourceButton.setBackgroundColor(Configuration.player1Color);
                    }else if(Configuration.player==2){
                        sourceButton.setBackgroundColor(Configuration.player2Color);
                    }
                    Configuration.move=1;
                }

            }else if(Configuration.move==1 && position.isIn(listOfValidPosition)){
                Configuration.move=0;
                for (var positioni:listOfValidPosition) {
                    Configuration.buttons[positioni.getX()][positioni.getY()].setBackgroundColor(Configuration.defaultColor);
                }
                if(Configuration.player==1){
                    sourceButton.setBackgroundColor(Configuration.player1Color);
                    Configuration.player=2;
                }else if(Configuration.player==2){
                    sourceButton.setBackgroundColor(Configuration.player2Color);
                    Configuration.player=1;
                }
                Configuration.intermediatePosition=null;
                //file.setBackgroundColors();
                //file.saveBackgroundColorToFile();
            }
            if(Configuration.domineeringSearch.playerWin()==1){
                labelMessage.toVisible(true);
                labelMessage.setText("PLayer 1 win");
                this.toEnableBtns(false);
            }else if(Configuration.domineeringSearch.playerWin()==2){
                labelMessage.toVisible(true);
                labelMessage.setText("PLayer 2 win");
                this.toEnableBtns(false);
            }else if(Configuration.domineeringSearch.playerWin()==-1){
                labelMessage.toVisible(true);
                labelMessage.setText("PTied in the match");
                this.toEnableBtns(false);
            }else {
                labelMessage.toVisible(false);
                labelMessage.setText("");
                this.toEnableBtns(true);
            }
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
        //file=StringTableFile.getInstance();
        Configuration.player=Configuration.domineeringSearch.getNextPlayer();
        Button sourceButton = (Button) e.getSource();
        if(Configuration.machine==0){
            this.makeMove(sourceButton);
        }else{
            if(Configuration.player!=Configuration.machine){
                makeMove(sourceButton);
            }
            if(Configuration.move==0){
                Board board=new Board();
                int[] best=Configuration.domineeringSearch.minmax(board.getBoard(),7,true,new int[]{0},new int[]{10000000});
                //Configuration.buttons[best[1]][best[2]].setBackgroundColor(Configuration.player2Color);
                //Configuration.buttons[best[1]+1][best[2]].setBackgroundColor(Configuration.player2Color);
                Configuration.domineeringSearch.playMove(best[1],best[2]);
                    if(Configuration.player==1){
                        Configuration.player=2;
                    }else if(Configuration.player==2){
                        Configuration.player=1;
                    }
                }
        }



        }
    }


