package Game;

import UI.Button;
import UI.Event;
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
    private boolean firstButtonClicked=false;
    private boolean secondButtonClicked=false;
    int player=1;
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
                DomineeringGame.this.makeOneToOneFrame();
                DomineeringGame.this.mainFrame.Close();

            }
        });
    }
    //endregion
    //region make One to One play window
    private void makeOneToOneFrame() {
        this.oneToOneGameFrame = window.addFrame(0, 0, 600, 600, "#ff0000");
        this.domineeringFrame = this.oneToOneGameFrame.addFrame(30, 150, 540, 406, "#00ff00");
        this.goToMainFrameButton=this.oneToOneGameFrame.addButton(10,10, 100, 57, "Return", "", "Back to home page ", true, "#000000", "#ffffff", 24, "Arial", false, false);
        // Create a 2D array to store buttons
        this.buttons = new Button[7][8];
        Event event=new Event();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                this.buttons[i][j] = this.domineeringFrame.addButton(j * 68, i * 58, 67, 57, "", "", i + "," + j, true, "#ffffff", "#000000", 0, "Arial", false, false);
                this.buttons[i][j].addActionListener(event);
            }
        }
        event.setButtons(this.buttons);
        this.goToMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.oneToOneGameFrame.Close();
                DomineeringGame.this.makeMainFrame();
            }
        });
    }

    //endregion
}
