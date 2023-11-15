package UI;

import Game.Position;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event implements ActionListener {
    public String[][] array=null;
    final String bgColor="#000000";
    final String player1Color="#ffff00";
    final String player2Color="#0000ff";
    final String defaultColor="#000000";
    static int player=1;
    static int move=0;
    private Button[][] buttons;
    public void setButtons(Button[][] buttonsArr){
        this.buttons=new Button[7][8];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                // Assign the reference of the existing button to the new array
                this.buttons[i][j] = buttonsArr[i][j];
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Access the source of the event (button) and retrieve its text
        Button sourceButton = (Button) e.getSource();
        if(Objects.equals(sourceButton.getBackgroundColor(), defaultColor)){
            String tooltip=sourceButton.getToolTipText();
            String[] pos=tooltip.split(",");
            int ligne=Integer.parseInt(pos[0]);
            int column=Integer.parseInt(pos[1]);
            Position position=new Position(ligne,column);
            List<Position> listOfValidPosition=position.getPossibleMove(1);
            if(listOfValidPosition.size()==1) {
                if(!Objects.equals(this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].getBackgroundColor(), defaultColor)){
                    sourceButton.setBackgroundColor(player1Color);
                    this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(player1Color);
                }
            }else if(listOfValidPosition.size()==2){
                byte validIndex=0;
                sourceButton.setBackgroundColor(player1Color);
                for(byte i=0;i<2;i++){
                    if(Objects.equals(this.buttons[listOfValidPosition.get(i).getX()][listOfValidPosition.get(i).getY()].getBackgroundColor(), defaultColor)){
                        validIndex++;
                        
                    }
                }
                this.buttons[listOfValidPosition.get(0).getX()][listOfValidPosition.get(0).getY()].setBackgroundColor(player1Color);
            }

            for (var posi: listOfValidPosition) {
                System.out.println(posi);
            }
        }



    }
}
