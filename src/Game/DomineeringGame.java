package Game;

import UI.Button;
import UI.Frame;
import UI.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
public class DomineeringGame {
    //region Argument
    private Window window;
    private Frame mainFrame;
    private Button btnGameWithPerson;
    private Button btnGameWithAgent;
    private Button btnMainExit;
    private Frame oneToOneGameFrame;
    private Frame domineeringFrame;
    private Button btn00;
    private Button btn01;
    private Button btn02;
    private Button btn03;
    private Button btn04;
    private Button btn05;
    private Button btn06;
    private Button btn07;
    private Button btn10;
    private Button btn11;
    private Button btn12;
    private Button btn13;
    private Button btn14;
    private Button btn15;
    private Button btn16;
    private Button btn17;
    private Button btn20;
    private Button btn21;
    private Button btn22;
    private Button btn23;
    private Button btn24;
    private Button btn25;
    private Button btn26;
    private Button btn27;
    private Button btn30;
    private Button btn31;
    private Button btn32;
    private Button btn33;
    private Button btn34;
    private Button btn35;
    private Button btn36;
    private Button btn37;
    private Button btn40;
    private Button btn41;
    private Button btn42;
    private Button btn43;
    private Button btn44;
    private Button btn45;
    private Button btn46;
    private Button btn47;
    private Button btn50;
    private Button btn51;
    private Button btn52;
    private Button btn53;
    private Button btn54;
    private Button btn55;
    private Button btn56;
    private Button btn57;
    private Button btn60;
    private Button btn61;
    private Button btn62;
    private Button btn63;
    private Button btn64;
    private Button btn65;
    private Button btn66;
    private Button btn67;

    //endregion
    //region Constructeur
    public DomineeringGame() {
        this.window=Window.getInstance(600,600,"Domineering","",true);
        this.makeMainFrame();
        this.window.Show();
    }
    //endregion
    //region make Main Window
    private void makeMainFrame(){
        this.mainFrame=window.addFrame(0,0,600,600,"#ff0000");
        this.btnGameWithPerson=this.mainFrame.addButton(100,100,400,100,"Play with friend","","Click me to play with your friend",true,"#0000ff","",30,"Arial",true,false);
        this.btnGameWithAgent=this.mainFrame.addButton(100,250,400,100,"Play Laptop","","Click me to play with your laptop",true,"#0000ff","",30,"Arial",true,false);
        this.btnMainExit=this.mainFrame.addButton(100,400,400,100,"Exit","","Click me to exit",true,"#0000ff","",30,"Arial",true,false);
        this.btnMainExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.window.Close();
            }
        });
        this.btnGameWithPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.mainFrame.Close();
                DomineeringGame.this.makeOneToOneFrame();
            }
        });
    }
    //endregion
    //region make One to One play window
    private void makeOneToOneFrame(){
        this.oneToOneGameFrame=window.addFrame(0,0,600,600,"#ff0000");
        this.domineeringFrame=this.oneToOneGameFrame.addFrame(30,150,540,406,"#00ff00");
        final String BACKGROUND_COLOR_BUTTON_GAME = "#000000";
        //region Logique

        //endregion
        //region ligne 1
        this.btn00=this.domineeringFrame.addButton(0,0,67,57,"","","0,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn01=this.domineeringFrame.addButton(68,0,67,57,"","","0,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn02=this.domineeringFrame.addButton(136,0,67,57,"","","0,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn03=this.domineeringFrame.addButton(204,0,67,57,"","","0,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn04=this.domineeringFrame.addButton(272,0,67,57,"","","0,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn05=this.domineeringFrame.addButton(340,0,67,57,"","","0,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn06=this.domineeringFrame.addButton(408,0,67,57,"","","0,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn07=this.domineeringFrame.addButton(476,0,67,57,"","","0,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion
        //region ligne 2
        this.btn10=this.domineeringFrame.addButton(0,58,67,57,"","","1,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn11=this.domineeringFrame.addButton(68,58,67,57,"","","1,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn12=this.domineeringFrame.addButton(136,58,67,57,"","","1,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn13=this.domineeringFrame.addButton(204,58,67,57,"","","1,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn14=this.domineeringFrame.addButton(272,58,67,57,"","","1,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn15=this.domineeringFrame.addButton(340,58,67,57,"","","1,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn16=this.domineeringFrame.addButton(408,58,67,57,"","","1,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn17=this.domineeringFrame.addButton(476,58,67,57,"","","1,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion
        //region ligne 3
        this.btn20=this.domineeringFrame.addButton(0,116,67,57,"","","2,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn21=this.domineeringFrame.addButton(68,116,67,57,"","","2,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn22=this.domineeringFrame.addButton(136,116,67,57,"","","2,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn23=this.domineeringFrame.addButton(204,116,67,57,"","","2,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn24=this.domineeringFrame.addButton(272,116,67,57,"","","2,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn25=this.domineeringFrame.addButton(340,116,67,57,"","","2,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn26=this.domineeringFrame.addButton(408,116,67,57,"","","2,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn27=this.domineeringFrame.addButton(476,116,67,57,"","","2,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion
        //region ligne 4
        this.btn30=this.domineeringFrame.addButton(0,174,67,57,"","","3,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn31=this.domineeringFrame.addButton(68,174,67,57,"","","3,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn32=this.domineeringFrame.addButton(136,174,67,57,"","","3,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn33=this.domineeringFrame.addButton(204,174,67,57,"","","3,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn34=this.domineeringFrame.addButton(272,174,67,57,"","","3,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn35=this.domineeringFrame.addButton(340,174,67,57,"","","3,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn36=this.domineeringFrame.addButton(408,174,67,57,"","","3,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn37=this.domineeringFrame.addButton(476,174,67,57,"","","3,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion
        //region ligne 5
        this.btn40=this.domineeringFrame.addButton(0,232,67,57,"","","4,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn41=this.domineeringFrame.addButton(68,232,67,57,"","","4,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn42=this.domineeringFrame.addButton(136,232,67,57,"","","4,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn43=this.domineeringFrame.addButton(204,232,67,57,"","","4,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn44=this.domineeringFrame.addButton(272,232,67,57,"","","4,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn45=this.domineeringFrame.addButton(340,232,67,57,"","","4,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn46=this.domineeringFrame.addButton(408,232,67,57,"","","4,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn47=this.domineeringFrame.addButton(476,232,67,57,"","","4,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion
        //region ligne 6
        this.btn50=this.domineeringFrame.addButton(0,290,67,57,"","","5,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn51=this.domineeringFrame.addButton(68,290,67,57,"","","5,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn52=this.domineeringFrame.addButton(136,290,67,57,"","","5,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn53=this.domineeringFrame.addButton(204,290,67,57,"","","5,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn54=this.domineeringFrame.addButton(272,290,67,57,"","","5,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn55=this.domineeringFrame.addButton(340,290,67,57,"","","5,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn56=this.domineeringFrame.addButton(408,290,67,57,"","","5,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn57=this.domineeringFrame.addButton(476,290,67,57,"","","5,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion
        //region ligne 7
        this.btn60=this.domineeringFrame.addButton(0,348,67,57,"","","6,0",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn61=this.domineeringFrame.addButton(68,348,67,57,"","","6,1",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn62=this.domineeringFrame.addButton(136,348,67,57,"","","6,2",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn63=this.domineeringFrame.addButton(204,348,67,57,"","","6,3",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn64=this.domineeringFrame.addButton(272,348,67,57,"","","6,4",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn65=this.domineeringFrame.addButton(340,348,67,57,"","","6,5",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn66=this.domineeringFrame.addButton(408,348,67,57,"","","6,6",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        this.btn67=this.domineeringFrame.addButton(476,348,67,57,"","","6,7",true,"", BACKGROUND_COLOR_BUTTON_GAME,0,"Arial",false,false);
        //endregion

    }
    //endregion
}
*/
import UI.Button;
import UI.Frame;
import UI.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DomineeringGame {

    //region Argument
    private Window window;
    private Frame mainFrame;
    private Button btnGameWithPerson;
    private Button btnGameWithAgent;
    private Button btnMainExit;
    private Frame oneToOneGameFrame;
    private Frame domineeringFrame;
    private Button goToMainFrameButton;
    private Button[][] buttons;

    //endregion
    //region Constructeur
    public DomineeringGame() {
        this.window = Window.getInstance(600, 600, "Domineering", "", true);
        this.makeMainFrame();
        this.window.Show();
    }
    //endregion
    //region make Main Window
    private void makeMainFrame() {
        this.mainFrame = window.addFrame(0, 0, 600, 600, "#ff0000");
        this.btnGameWithPerson = this.mainFrame.addButton(100, 100, 400, 100, "Play with friend", "", "Click me to play with your friend", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnGameWithAgent = this.mainFrame.addButton(100, 250, 400, 100, "Play Laptop", "", "Click me to play with your laptop", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnMainExit = this.mainFrame.addButton(100, 400, 400, 100, "Exit", "", "Click me to exit", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnMainExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.window.Close();
            }
        });
        this.btnGameWithPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.mainFrame.Close();
                DomineeringGame.this.makeOneToOneFrame();
            }
        });
    }
    //endregion
    //region make One to One play window
    private void makeOneToOneFrame() {
        this.oneToOneGameFrame = window.addFrame(0, 0, 600, 600, "#ff0000");
        this.domineeringFrame = this.oneToOneGameFrame.addFrame(30, 150, 540, 406, "#00ff00");
        this.goToMainFrameButton=this.domineeringFrame.addButton(, , 67, 57, "Return ", "", i + "," + j, true, "#ffffff", "#000000", 0, "Arial", false, false);
        // Create a 2D array to store buttons
        this.buttons = new Button[7][8];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                this.buttons[i][j] = this.domineeringFrame.addButton(j * 68, i * 58, 67, 57, "", "", i + "," + j, true, "#ffffff", "#000000", 0, "Arial", false, false);
            }
        }
    }
    //endregion
}
