package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Event implements ActionListener {
    public String[][] array=null;
    Event(String[][] array1){
        /*
        array = new String[8][7];
        for (byte i = 0; i < 8; i++) {
            for (byte j = 0; j < 7; j++) {
                array[i][j]=array1[i][j];
            }
        }
        */

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Access the source of the event (button) and retrieve its text
        Button sourceButton = (Button) e.getSource();

    }
}
)