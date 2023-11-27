package Search;

import UI.Button;

import java.util.Objects;

public class DomineeringSearch {
    private Button buttons[][]=null;
    final String player1Color="#800080";
    final String player2Color="#000000";
    final String defaultColor="#ffffff";
    private static DomineeringSearch domineeringSearchInstance=null;

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    private DomineeringSearch(Button[][] buttons) {
        this.buttons = buttons;
    }
    public static DomineeringSearch getInstance(Button buttons[][]){
        if(domineeringSearchInstance==null){
            domineeringSearchInstance=new DomineeringSearch(buttons);
        }else{
            domineeringSearchInstance.setButtons(buttons);
        }
        return domineeringSearchInstance;
    }
    public byte evalutionFunction(byte player) {
        byte nbrPossibleMove = 0;
        if (buttons != null) {
            if (player == 1) {
                for (byte i = 0; i < buttons.length; i++) {
                    for (byte j = 0; j < buttons[0].length - 1; j++) {
                        if (Objects.equals(buttons[i][j].getBackgroundColor(), defaultColor) && Objects.equals(buttons[i][j + 1].getBackgroundColor(), defaultColor)) {
                            nbrPossibleMove += 1;
                        }
                    }
                }
            } else if (player == 2) {
                for (byte i = 0; i < buttons.length - 1; i++) {
                    for (byte j = 0; j < buttons[0].length; j++) {
                        if (Objects.equals(buttons[i][j].getBackgroundColor(), defaultColor) && Objects.equals(buttons[i + 1][j].getBackgroundColor(), defaultColor)) {
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
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), player1Color) && Objects.equals(buttons[i][j+1].getBackgroundColor(), player1Color)){
                            nbrPossibleMove+=1;
                            j++;
                        }
                    }
                }
            }else if(player==2){
                for(byte i=0;i<buttons.length-1;i++){
                    for(byte j=0;j<buttons[0].length;j++){
                        if(Objects.equals(buttons[i][j].getBackgroundColor(), player2Color) && Objects.equals(buttons[i+1][j].getBackgroundColor(), player2Color)){
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
