package Game;

import Helper.StringTableFile;
import UI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DomineeringGame {

    //region Argument
    StringTableFile file;
    final String defaultColor="#ffffff";//of buttons
    final String mainBgColor="#00008b";
    final String backgroundGame="#000000";
    private Window window;
    private Frame mainFrame;
    private Button btnGameWithPerson;
    private Button btnGameWithAgent;
    private Button btnMainExit;
    private Frame oneToOneGameFrame;
    private Frame domineeringFrame;
    private Button goToMainFrameButton;
    private Button restartGameButton;
    private Label labelMessage=null;
    private Button[][] buttons;
    private boolean firstButtonClicked=false;
    private boolean secondButtonClicked=false;
    int row;
    int column;
    private static DomineeringGame domineeringGameInstance;
    //endregion
    //region Constructeur
    private DomineeringGame(int row , int column) {
        this.window = Window.getInstance(600, 600, "Domineering", "", true);
        this.makeMainFrame();
        this.window.Show();
        this.row=row;
        this.column=column;
    }
    public static DomineeringGame getInstance(int row , int column ){
        if(domineeringGameInstance==null){
            domineeringGameInstance=new DomineeringGame(row,column);
        }
        return  domineeringGameInstance;
    }
    //endregion
    //region make Main Window
    private void makeMainFrame() {
        this.mainFrame = window.addFrame(0, 0, 600, 600, mainBgColor);
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
                DomineeringGame.this.makeOneToOneFrame();
                DomineeringGame.this.mainFrame.Close();

            }
        });
    }
    //endregion
    //region make One to One play window
    private void makeOneToOneFrame() {
        this.oneToOneGameFrame = window.addFrame(0, 0, 600, 600, mainBgColor);
        this.domineeringFrame = this.oneToOneGameFrame.addFrame(30, 150, 540, 406, backgroundGame);
        this.goToMainFrameButton=this.oneToOneGameFrame.addButton(10,10, 100, 57, "Return", "", "Back to home page ", true, "#000000", "#ffffff", 24, "Arial", false, false);
        this.restartGameButton=this.oneToOneGameFrame.addButton(490,10, 100, 57, "Restart", "", "Restart game", true, "#000000", "#ffffff", 24, "Arial", false, false);        // Create a 2D array to store buttons
        this.buttons = new Button[this.column][this.row];
        this.labelMessage=this.oneToOneGameFrame.addLabel(30,80,540,60,"","","",true,false,mainBgColor,"#ffffff",24,"Arial",false,true);
        this.labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
        Event event=new Event();
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                this.buttons[i][j] = this.domineeringFrame.addButton(j * 68, i * 58, 67, 57, "", "", i + "," + j, true, "#ffffff", defaultColor, 0, "Arial", false, false);
                this.buttons[i][j].addActionListener(event);
                this.buttons[i][j].toEnabled(true);
            }
        }
        event.setLabel(this.labelMessage);
        event.setButtons(this.buttons);
        file=StringTableFile.getInstance(buttons);
        file.loadBackgroundColorFromFile("data.txt",buttons);

        this.goToMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.oneToOneGameFrame.Close();
                DomineeringGame.this.makeMainFrame();
            }
        });
        this.restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.restart();
            }
        });
    }
    private void restart(){
        if(/*buttons!=null*/false){
            Event event=new Event();
            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    this.buttons[i][j].setBackgroundColor(defaultColor);
                    this.buttons[i][j].toEnabled(true);
                }
            }
            this.labelMessage.toVisible(false);
            this.labelMessage.setText("");
            file=StringTableFile.getInstance(buttons);
            file.setBackgroundColors(this.buttons);
        }
    }
}
