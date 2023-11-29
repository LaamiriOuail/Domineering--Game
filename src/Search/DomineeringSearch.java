package Search;

import Helper.Configuration;
import UI.Button;

import java.util.Objects;

public class DomineeringSearch implements GameSearch {
    private static DomineeringSearch domineeringSearchInstance=null;
    private DomineeringSearch() {

    }
    public static DomineeringSearch getInstance(){
        if(domineeringSearchInstance==null){
            domineeringSearchInstance=new DomineeringSearch();
        }else{
        }
        return domineeringSearchInstance;
    }
    public byte getNumberOfPosibleMove(byte player) {
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
    public byte getNumbreOfMoveByPlayer(byte player){
        byte nbrPossibleMove=0;
        if(Configuration.buttons!=null){
            if(player==1){
                for(byte i=0;i<Configuration.buttons.length;i++){
                    for(byte j=0;j<Configuration.buttons[0].length-1;j++){
                        if(Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.player1Color) && Objects.equals(Configuration.buttons[i][j+1].getBackgroundColor(), Configuration.player1Color)){
                            nbrPossibleMove+=1;
                            j++;
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<Configuration.buttons.length-1;i++){
                    for(byte j=0;j<Configuration.buttons[0].length;j++){
                        if(Objects.equals(Configuration.buttons[i][j].getBackgroundColor(), Configuration.player2Color) && Objects.equals(Configuration.buttons[i+1][j].getBackgroundColor(), Configuration.player2Color)){
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
