package Search;

import Helper.Configuration;
import UI.Button;

import java.util.Objects;

public class DomineeringSearch implements GameSearch {
    private Button buttons[][]=null;
    private static DomineeringSearch domineeringSearchInstance=null;
    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    private DomineeringSearch(Button[][] buttons) {
        this.buttons = buttons;
    }
    public static DomineeringSearch getInstance(Button[][] buttons){
        if(domineeringSearchInstance==null){
            domineeringSearchInstance=new DomineeringSearch(buttons);
        }else{
            domineeringSearchInstance.setButtons(buttons);
        }
        return domineeringSearchInstance;
    }
    public byte getNumberOfPosibleMove(byte player) {
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
    public byte getNumbreOfMoveByPlayer(byte player){
        byte nbrPossibleMove=0;
        if(buttons!=null){
            if(player==1){
                for(byte i=0;i<buttons.length;i++){
                    for(byte j=0;j<buttons[0].length-1;j++){
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), Configuration.player1Color) && Objects.equals(buttons[i][j+1].getBackgroundColor(), Configuration.player1Color)){
                            nbrPossibleMove+=1;
                            j++;
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<buttons.length-1;i++){
                    for(byte j=0;j<buttons[0].length;j++){
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), Configuration.player2Color) && Objects.equals(buttons[i+1][j].getBackgroundColor(), Configuration.player2Color)){
                            nbrPossibleMove+=1;
                            j++;
                        }
                    }
                }
            }
        }
        return nbrPossibleMove;
    }
    public byte getCurrentPlayer(){
        if(this.getNumbreOfMoveByPlayer((byte)1)==this.getNumbreOfMoveByPlayer((byte)2)){
            return (byte)1;
        }else if(this.getNumbreOfMoveByPlayer((byte)1)>this.getNumbreOfMoveByPlayer((byte)2)){
            return (byte)2;
        }else{
            return (byte)1;
        }
    }
}
