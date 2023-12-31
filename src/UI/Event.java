package UI;

import Game.DomineeringGame;
import Helper.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
/**
 * The Event class implements the ActionListener interface and handles button click events
 * for a user interface associated with the "Domineering" game.
 * @author Laamiri Ouail & Hajar Sadik
 * @version 1.0
 * @since 2023-12-01
 */
public class Event implements ActionListener {
    List<Position> listOfValidPosition=null;
    /**
     * Sets the label message for the UI.
     *
     * @param labelMessage The label message to be set.
     */
    public void setLabelMessage(Label labelMessage){
        Configuration.labelMessage=labelMessage;
    }
    /**
     * Enables or disables all buttons in the UI.
     *
     * @param enable True to enable, false to disable.
     */
    public void toEnableBtns(boolean enable){
        if(Configuration.buttons!=null){
            for (int i = 0; i < Configuration.buttons.length; i++) {
                for (int j = 0; j < Configuration.buttons[0].length; j++) {
                    Configuration.buttons[i][j].toEnabled(enable);
                }
            }
        }
    }
    /**
     * Makes a move in the game based on the button clicked.
     *
     * @param sourceButton The button that was clicked.
     */
    public void makeMove(Button sourceButton){
        if(Objects.equals(sourceButton.getBackgroundColor(), Configuration.defaultColor) || Objects.equals(sourceButton.getBackgroundColor(), Configuration.intermediateColor)){
            String tooltip=sourceButton.getToolTipText();
            String[] pos=tooltip.split(",");
            int ligne=Integer.parseInt(pos[0]);
            int column=Integer.parseInt(pos[1]);
            Position position=new Position(ligne,column);
            boolean go=true;
            if(Configuration.helpMove!=null){
                if (Configuration.helpMove.hasPosition(position)) {
                    if(Configuration.helpMove.getPlayer()==1){
                        Configuration.buttons[Configuration.helpMove.getPosition1().getX()][Configuration.helpMove.getPosition1().getY()].setBackgroundColor(Configuration.player1Color);
                        Configuration.buttons[Configuration.helpMove.getPosotion2().getX()][Configuration.helpMove.getPosotion2().getY()].setBackgroundColor(Configuration.player1Color);
                        Configuration.player=2;
                    }else if(Configuration.helpMove.getPlayer()==2){
                        Configuration.buttons[Configuration.helpMove.getPosition1().getX()][Configuration.helpMove.getPosition1().getY()].setBackgroundColor(Configuration.player2Color);
                        Configuration.buttons[Configuration.helpMove.getPosotion2().getX()][Configuration.helpMove.getPosotion2().getY()].setBackgroundColor(Configuration.player2Color);
                        Configuration.player=1;
                    }
                    go=false;
                }else{
                    Configuration.buttons[Configuration.helpMove.getPosition1().getX()][Configuration.helpMove.getPosition1().getY()].setBackgroundColor(Configuration.defaultColor);
                    Configuration.buttons[Configuration.helpMove.getPosotion2().getX()][Configuration.helpMove.getPosotion2().getY()].setBackgroundColor(Configuration.defaultColor);

                }
                Configuration.helpMove=null;
            }
            if(go){
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
            }
            if(Configuration.domineeringSearch.playerWin()==1){
                Configuration.labelMessage.toVisible(true);
                Configuration.labelMessage.setText("PLayer 1 win");
                this.toEnableBtns(false);
            }else if(Configuration.domineeringSearch.playerWin()==2){
                Configuration.labelMessage.toVisible(true);
                Configuration.labelMessage.setText("PLayer 2 win");
                this.toEnableBtns(false);
            }else if(Configuration.domineeringSearch.playerWin()==-1){
                Configuration.labelMessage.toVisible(true);
                Configuration.labelMessage.setText("PTied in the match");
                this.toEnableBtns(false);
            }else {
                Configuration.labelMessage.toVisible(false);
                Configuration.labelMessage.setText("");
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
        if(Configuration.labelMessage!=null){
            if (!Objects.equals(Configuration.labelMessage.getText(), "")) {
                Configuration.labelMessage.setText("");
                Configuration.labelMessage.toVisible(false);
                //DomineeringGame.getInstance().finalizeSauvgardedFrame();
            }
        }
        //file=StringTableFile.getInstance();
        Configuration.player=Configuration.domineeringSearch.getNextPlayer();
        Button sourceButton = (Button) e.getSource();
        if(Configuration.machine==0){
            this.makeMove(sourceButton);
            Configuration.testHelpButton();
        }else{
            if(Configuration.player!=Configuration.machine){
                makeMove(sourceButton);
                if(Configuration.move==0){
                    Board board=new Board();
                    int[] best=Configuration.domineeringSearch.minmax(board.getBoard(),3,true,new int[]{0},new int[]{10000000});
                    Configuration.domineeringSearch.playMove(best[1],best[2]);
                    if(Configuration.player==1){
                        Configuration.player=2;
                    }else if(Configuration.player==2){
                        Configuration.player=1;
                    }
                }
                Configuration.testHelpButton();
            }
        }



        }
    }


