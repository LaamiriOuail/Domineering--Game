package Game;

import Helper.Configuration;
import Helper.Sauvgard;
import Helper.StringTableFile;
import UI.Button;
import UI.Event;
import UI.Frame;
import UI.Label;
import UI.TextField;
import UI.Window;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
/**
 * The DomineeringGame class represents the main game controller for the Domineering game.
 * It handles the main game window, settings, saved games, and the game logic.
 * This class uses the Singleton design pattern to ensure only one instance of the game is created.
 * @author Laamiri Ouail & Sadik Hajar
 * @version 1.0
 * @since 2023-12-01
 */
public class DomineeringGame {
    private Window window;
    private Frame mainFrame=null;
    private Button btnGameWithPerson=null;
    private Button btnGameWithAgent=null;
    private Button btnMainExit=null;
    private Button btnsauvgardedGamebtn=null;
    private Button btnChangeSizeGame=null;
    private Button btnSetting = null;
    private Frame settingFrame = null;
    private Frame updateSettingFRame = null;
    private Frame oneToOneGameFrame=null;
    private Frame domineeringFrame=null;
    private Button goToMainFrameButton=null;
    private Button restartGameButton=null;
    private Button sauvgardebtn=null;
    private Frame sauvgardeFrame=null;
    private TextField inputTitle=null;
    private Button submitSauvgarde=null;
    private Button refuseSauvgarde=null;
    private Label labelInputSauvgarde=null;
    private Frame SauvgardedFrame=null;
    private Frame SauvgardedTableFrame=null;
    private Frame changeSizeGameFrame=null;
    private Label labelRow=null;
    private TextField inputRow=null;
    private Label labelColumn=null;
    private TextField inputColumn=null;
    private Label labelBoardColor=null;
    private TextField inputBoardColor=null;
    private Label labelPlayer1Color=null;
    private TextField inputPlayer1Color=null;
    private Label labelPlayer2Color=null;
    private TextField inputPlayer2Color=null;
    private Label labelIntermediateColor=null;
    private TextField inputIntermediateColor=null;
    private Label labelAppBackgroundColor=null;
    private TextField inputAppBackgroundColor=null;
    private Button saveSetting=null;

    private int windowHeight;
    private int windowWidth;
    private Event event=null;

    private static DomineeringGame domineeringGameInstance=null;
    /**
     * Constructs a new instance of the DomineeringGame class.
     * It initializes the game window, sets up configurations, and loads saved configurations.
     */
    private DomineeringGame() {
        this.event=new Event();
        this.loadConfigurations();
        Configuration.buttons=new Button[Configuration.row][Configuration.column];
        this.windowHeight=(Configuration.row-1)*58+57+200;
        this.windowWidth=(Configuration.column-1)*68+67+60;
        this.window = Window.getInstance(windowWidth, windowHeight, "Domineering", "", true);
        this.makeMainFrame();
        this.window.Show();
        //file=StringTableFile.getInstance(buttons);
        //file.saveConfiguarations(Configuration.row,Configuration.column,Configuration.defaultColor,Configuration.player1Color,Configuration.player2Color,Configuration.machineColor,Configuration.intermediateColor,Configuration.mainBgColor);

    }
    /**
     * Sets up the initial configurations for the game, including the game window,
     * buttons, and other components.
     */
    private void setAttribute() {
        this.window.setVisible(false);
        this.event=new Event();
        Configuration.buttons=new Button[Configuration.row][Configuration.column];
        this.windowHeight=(Configuration.row-1)*58+57+200;
        this.windowWidth=(Configuration.column-1)*68+67+60;
        this.window = Window.getInstance(windowWidth, windowHeight, "Domineering", "", true);
    }
    /**
     * Gets the singleton instance of the DomineeringGame class.
     *
     * @return The singleton instance of the DomineeringGame class.
     */
    public static DomineeringGame getInstance( ){
        if(domineeringGameInstance==null){
            domineeringGameInstance=new DomineeringGame();
        }
        return  domineeringGameInstance;
    }
    /**
     * Loads configuration settings from a file and updates the Configuration class accordingly.
     *
     * This method uses the StringTableFile singleton instance to load configuration settings from a file.
     * It retrieves an array of configurations, including row and column size, default colors for players and board,
     * and the background color of the application. If the loaded configurations are valid (contain 7 elements),
     * it updates the Configuration class with the new values.
     *
     * Note: This method is responsible for initializing the Configuration class with settings loaded from a file.
     */
    private void loadConfigurations(){
        Configuration.file=StringTableFile.getInstance();
        String[] configurations=Configuration.file.loadConfigurations();
        if(configurations!=null){
            if(configurations.length==7){
                Configuration.row = Short.parseShort(configurations[0]);
                Configuration.column = Short.parseShort(configurations[1]);
                Configuration.defaultColor = configurations[2];
                Configuration.player1Color = configurations[3];
                Configuration.player2Color = configurations[4];
                Configuration.intermediateColor = configurations[5];
                Configuration.mainBgColor = configurations[6];
            }
        }
    }
    /**
     * Creates and initializes the main frame with buttons for different game options.
     *
     * This method sets up the main frame, including buttons for playing with a friend,
     * playing against the laptop, accessing saved games, going to settings, and exiting the application.
     * It associates action listeners for each button to handle the corresponding functionalities.
     *
     * Note: This method is responsible for creating the initial main frame layout and setting up button functionalities
     * for various game options.
     */
    private void makeMainFrame() {
        short width=0;
        short size=0;
        short left= 0;
        if(Configuration.column<=6){
            width= (short) (windowWidth-100);
            size=10;
            left=50;
        }else{
            width= (short) (windowWidth-200);
            size=20;
            left= 100;
        }
        short top= (short) ((windowHeight-460)/2);

        short height=80;
        short space=15;
        this.mainFrame = window.addFrame(0, 0, windowWidth, windowHeight, Configuration.mainBgColor);
        this.btnGameWithPerson = this.mainFrame.addButton(left, top, width, height, "Play with friend", "", "Click me to play with your friend", true, "#0000ff", "#ffffff", size+10, "Arial", true, false);
        this.btnGameWithAgent = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Play Laptop", "", "Click me to play with your laptop", true, "#0000ff", "#ffffff", size+10, "Arial", true, false);
        this.btnsauvgardedGamebtn = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Saved Games", "", "Click me to exit", true, "#0000ff", "#ffffff", size+10, "Arial", true, false);
        this.btnSetting = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Setting", "", "Click me to go setting", true, "#0000ff", "#ffffff", size+10, "Arial", true, false);
        this.btnMainExit = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Exit", "", "Click me to exit", true, "#0000ff", "#ffffff", size+10, "Arial", true, false);
        this.btnMainExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.window.Close();
            }
        });
        this.btnGameWithAgent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.mainFrame.Close();
                DomineeringGame.this.makeGameFrame();
                Configuration.machine=2;
                DomineeringGame.this.finalizeMainFrame();
            }
        });
        this.btnGameWithPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.mainFrame.Close();
                DomineeringGame.this.makeGameFrame();
                Configuration.machine=0;
                DomineeringGame.this.finalizeMainFrame();
            }
        });
        this.btnsauvgardedGamebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.mainFrame.Close();
                DomineeringGame.this.makeSauvgardedFrame();
                DomineeringGame.this.finalizeMainFrame();
            }
        });
        this.btnSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.mainFrame.Close();
                DomineeringGame.this.makeSettingFrame();
                DomineeringGame.this.finalizeMainFrame();
            }
        });
    }
    /**
     * Creates and initializes the game frame with buttons and UI elements.
     *
     * This method sets up the game frame, including buttons for gameplay, restart, and saving.
     * It also associates action listeners for various buttons, such as returning to the main frame,
     * saving the game, restarting the game, and accessing the help feature.
     *
     * Note: This method is responsible for creating the initial game frame layout and setting up button functionalities.
     */
    private void makeGameFrame() {
        short width=0;
        short size=0;
        if(Configuration.column<6){
            width= 70;
            size=16;
        }else{
            width= 100;
            size=20;
        }
        //this.oneToOneGameFrame = window.addFrame(0, 0, 600, 600, mainBgColor);
        this.oneToOneGameFrame = window.addFrame(0, 0, windowWidth, windowHeight, Configuration.mainBgColor);
        this.domineeringFrame = this.oneToOneGameFrame.addFrame(30, 150, this.windowWidth-62, this.windowHeight-169, Configuration.mainBgColor);
        this.goToMainFrameButton=this.oneToOneGameFrame.addButton(10,10, width, 47, "Return", "", "Back to home page ", true, "#000000", "#ffffff", size, "Arial", false, false);
        this.restartGameButton=this.oneToOneGameFrame.addButton(windowWidth-110,10, width, 47, "Restart", "", "Restart game", true, "#000000", "#ffffff", size, "Arial", false, false);        // Create a 2D array to store buttons
        this.sauvgardebtn=this.oneToOneGameFrame.addButton(windowWidth-225,10, width+10, 47, "Save", "", "Save game", true, "#000000", "#ffffff", size, "Arial", false, false);        // Create a 2D array to store buttons
        Configuration.helpBtn=this.oneToOneGameFrame.addButton(windowWidth-340,10, width+10, 47, "Help", "", "Help game", true, "#000000", "#ffffff", size, "Arial", false, false);        // Create a 2D array to store buttons
        /*if(Configuration.column<=6){
            this.btnChangeSizeGame=this.oneToOneGameFrame.addButton(10,60, width, 47, "size", "", "sauvgarde game", true, "#000000", "#ffffff", size, "Arial", false, false);        // Create a 2D array to store buttons
        }else{
            this.btnChangeSizeGame=this.oneToOneGameFrame.addButton(width+20,10, width, 47, "size", "", "sauvgarde game", true, "#000000", "#ffffff", size, "Arial", false, false);        // Create a 2D array to store buttons
        }
        */
        Configuration.labelMessage=this.oneToOneGameFrame.addLabel(30,80,windowWidth-60,60,"","","",true,false, Configuration.mainBgColor,"#ffffff",size+4,"Arial",false,true);
        Configuration.labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < Configuration.row; i++) {
            for (int j = 0; j < Configuration.column; j++) {
                Configuration.buttons[i][j] = this.domineeringFrame.addButton(j * 68, i * 58, 67, 57, "", "", i + "," + j, true, "#ffffff", Configuration.defaultColor, 0, "Arial", false, false);
                Configuration.buttons[i][j].addActionListener(event);
            }
        }
        event.setLabelMessage(Configuration.labelMessage);
        //event.setButtons();
        //file=StringTableFile.getInstance(buttons);
        //file.loadBackgroundColorFromFile(buttons);
        Configuration.helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configuration.helpPlayer();
            }
        });
        this.goToMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.oneToOneGameFrame.Close();
                DomineeringGame.this.finalizeOneToOneFrame();
                DomineeringGame.this.makeMainFrame();
                Configuration.setMove((byte)0);
                Configuration.initializeHelpPlayerParameters();
            }
        });
        this.sauvgardebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.makeSauvgardeOneGameFrame();
            }
        });
        this.restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.domineeringFrame.Close();
                DomineeringGame.this.restart();
                Configuration.initializeHelpPlayerParameters();
                DomineeringGame.this.domineeringFrame.Show();
            }
        });
    }
    /**
     * Finalizes the main frame by nullifying its components.
     *
     * This method sets all components of the main frame to null, including buttons and other UI elements.
     * It is typically called when transitioning away from the main frame to ensure that resources are released
     * and references are cleared.
     *
     * Note: This method is part of the cleanup process after the main frame is no longer needed.
     */
    private void finalizeMainFrame() {
        this.mainFrame = null;
        this.btnGameWithPerson = null;
        this.btnGameWithAgent = null;
        this.btnsauvgardedGamebtn = null;
        this.btnMainExit = null;
    }
    /**
     * Finalizes the one-to-one game frame by nullifying its components.
     *
     * This method sets all components of the one-to-one game frame to null, including buttons,
     * labels, and other UI elements. It is typically called when transitioning away from the
     * one-to-one game frame to ensure that resources are released and references are cleared.
     *
     * Note: This method is part of the cleanup process after the one-to-one game frame is no longer needed.
     */
    private void finalizeOneToOneFrame() {
        this.oneToOneGameFrame = null;
        this.domineeringFrame = null;
        this.goToMainFrameButton=null;
        this.restartGameButton=null;
        this.sauvgardebtn=null;
        Configuration.labelMessage=null;
    }
    /**
     * Finalizes the frame for viewing saved games by setting all relevant components to null,
     * clearing references, and preparing for the next frame or action.
     *
     * This method sets the `SauvgardedTableFrame`, `SauvgardedFrame`, and `goToMainFrameButton` to null,
     * effectively releasing memory and resources associated with these components.
     * It is typically called when transitioning to another frame or completing the interaction with the saved games frame.
     */

    public void finalizeSauvgardedFrame(){
        this.SauvgardedTableFrame=null;
        this.SauvgardedFrame=null;
        this.goToMainFrameButton=null;
    }
    /**
     * Restarts the Domineering game by resetting the game board, move counters, and message label.
     *
     * This method iterates through each button in the game board and sets its background color to the default color.
     * Additionally, it enables all buttons and resets the move counter to zero. It hides and clears the message label.
     * Finally, it saves the void colors configuration to the file for future reference.
     * This method is typically called when restarting the game after completion or when needed.
     */
    private void restart(){
        for (int i = 0; i < Configuration.row; i++) {
            for (int j = 0; j < Configuration.column; j++) {
                Configuration.buttons[i][j].setBackgroundColor(Configuration.defaultColor);
                Configuration.buttons[i][j].toEnabled(true);
            }
        }
        Configuration.setMove((byte)0);
        Configuration.labelMessage.toVisible(false);
        Configuration.labelMessage.setText("");
        Configuration.file=StringTableFile.getInstance();
        Configuration.file.saveVoidColors();
    }
    /**
     * Creates and initializes the frame for viewing saved games.
     *
     * This method sets up the SauvgardedFrame by adding components such as buttons and tables.
     * It creates a "Return" button to go back to the main frame and invokes the makeSauvgardedTable method
     * to display a table of saved games. The frame is typically used to navigate and interact with saved game data.
     *
     * Note: The method associates an ActionListener with the "Return" button to handle the action of going back to the main frame.
     *
     * @see #makeSauvgardedTable()
     */
    private void makeSauvgardedFrame(){
        this.SauvgardedFrame= window.addFrame(0, 0, windowWidth, windowHeight, Configuration.mainBgColor);
        this.goToMainFrameButton=this.SauvgardedFrame.addButton(10,10, 100, 57, "Return", "", "Back to home page ", true, "#000000", "#ffffff", 20, "Arial", false, false);
        this.makeSauvgardedTable();
        this.goToMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.SauvgardedFrame.Close();
                DomineeringGame.this.finalizeSauvgardedFrame();
                DomineeringGame.this.makeMainFrame();
            }
        });
    }
    /**
     * Creates and initializes a table within the SauvgardedFrame to display saved game information.
     *
     * This method reads saved game data from a file, populates a table with columns "ID," "Description," and "Date,"
     * and associates a custom TableCellRenderer to set font size for better visibility. It also adds a ListSelectionListener
     * to the table for handling row selection events, enabling the user to load a selected saved game.
     *
     * Note: The method dynamically adjusts the table's size based on the number of saved games and their information.
     *
     * @see #makeSauvgardedFrame()
     */
    private void makeSauvgardedTable(){
        Configuration.file=StringTableFile.getInstance();
        ArrayList<Sauvgard> sauvgards=Configuration.file.uploadSauvgardeFromFile();
        this.SauvgardedTableFrame=this.SauvgardedFrame.addFrame(10, 115, windowWidth-20, 30*sauvgards.size()+30, Configuration.mainBgColor);
        this.SauvgardedTableFrame.setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Description", "Date"});
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(180);
        table.setRowHeight(30);
        // Custom TableCellRenderer to set font size
        TableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            Font font = new Font("Arial", Font.PLAIN, 18); // Set your desired font size

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(font);
                return c;
            }
        };

        // Apply the custom renderer to all columns
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
        for(byte i=0;i<sauvgards.size();i++){
            model.addRow(new Object[]{model.getRowCount() + 1, sauvgards.get(i).getTitle(), sauvgards.get(i).getFormattedDateTime()});
        }

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    // Check if a row is selected
                    if (selectedRow != -1 && selectedRow < sauvgards.size()) {
                        int idColumnIndex = 0;  // Assuming the ID is in the first column
                        Object selectedId = table.getValueAt(selectedRow, idColumnIndex);

                        // Check if selectedId is not null and is of the expected type
                        if (selectedId != null && selectedId instanceof Integer) {
                            int id = (int) selectedId;
                            DomineeringGame.this.SauvgardedFrame.Close();
                            DomineeringGame.this.finalizeSauvgardedFrame();
                            Sauvgard svg = sauvgards.get(id - 1);
                            Configuration.row= (short) svg.getBackgroundColors().length;
                            Configuration.column= (short) svg.getBackgroundColors()[0].length;
                            Configuration.machine=svg.getMachine();
                            DomineeringGame.this.setAttribute();
                            DomineeringGame.this.makeGameFrame();
                            for(byte i=0;i<Configuration.buttons.length;i++){
                                for(byte j=0;j<Configuration.buttons[0].length;j++){
                                    Configuration.buttons[i][j].setBackgroundColor(svg.getBackgroundColors()[i][j]);
                                }
                            }
                        }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        this.SauvgardedTableFrame.add(scrollPane, BorderLayout.CENTER);
    }
    /**
     * Creates and initializes a frame for saving a one-to-one game.
     *
     * This method checks the existence of the one-to-one game frame and the absence of the sauvgarde frame.
     * If conditions are met, it closes the one-to-one game frame, sets up the sauvgarde frame with input fields
     * for a description and buttons for submitting or refusing the sauvgarde. It also handles the actions of the
     * submit and refuse buttons, allowing the user to save or cancel the sauvgarde process.
     *
     * Note: The sauvgarde frame is designed to capture additional information such as a description before saving.
     *
     * @see #makeGameFrame()
     */
    private void makeSauvgardeOneGameFrame(){
        if(DomineeringGame.this.oneToOneGameFrame!=null){
            if(DomineeringGame.this.sauvgardeFrame==null){
                DomineeringGame.this.oneToOneGameFrame.Close();
                if(Configuration.labelMessage!=null){
                    if (!Objects.equals(Configuration.labelMessage.getText(), "")) {
                        Configuration.labelMessage.setText("");
                        Configuration.labelMessage.toVisible(false);
                    }
                }
                DomineeringGame.this.sauvgardeFrame=DomineeringGame.this.oneToOneGameFrame.addFrame(30,100,this.windowWidth-62,50, Configuration.mainBgColor);
                DomineeringGame.this.inputTitle=DomineeringGame.this.sauvgardeFrame.addInput(140,10,windowWidth-430,30,"","","","#ffffff",21,"#000000",false,false,true);
                DomineeringGame.this.submitSauvgarde=DomineeringGame.this.sauvgardeFrame.addButton(windowWidth-280,10, 100, 30, "Submit", "", "Sauvgarder game", true, "#000000", "#ffffff", 18, "Arial", false, false);        //
                DomineeringGame.this.refuseSauvgarde=DomineeringGame.this.sauvgardeFrame.addButton(windowWidth-170,10, 100, 30, "refuse", "", "refuse sauvgarde game", true, "#000000", "#ffffff", 18, "Arial", false, false);        //
                DomineeringGame.this.labelInputSauvgarde=DomineeringGame.this.sauvgardeFrame.addLabel(10,10,130,30,"Description :","","",true,true,"#ffffff", Configuration.mainBgColor,14,"",true,false);
                DomineeringGame.this.oneToOneGameFrame.Show();
                DomineeringGame.this.refuseSauvgarde.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DomineeringGame.this.oneToOneGameFrame.Close();
                        DomineeringGame.this.sauvgardeFrame.Close();
                        DomineeringGame.this.sauvgardeFrame=null;
                        DomineeringGame.this.oneToOneGameFrame.Show();
                    }
                });
                DomineeringGame.this.submitSauvgarde.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DomineeringGame.this.oneToOneGameFrame.Close();
                        Configuration.file=StringTableFile.getInstance();
                        if(!Objects.equals(DomineeringGame.this.inputTitle.getText(), "")){
                            if(Configuration.intermediatePosition!=null){
                                if(Objects.equals(Configuration.buttons[Configuration.intermediatePosition.getX()][Configuration.intermediatePosition.getY()].getBackgroundColor(), Configuration.player1Color)){
                                    Configuration.buttons[Configuration.intermediatePosition.getX()][Configuration.intermediatePosition.getY()].setBackgroundColor(Configuration.defaultColor);
                                    Configuration.buttons[Configuration.intermediatePosition.getX()][Configuration.intermediatePosition.getY()-1].setBackgroundColor(Configuration.defaultColor);
                                    Configuration.buttons[Configuration.intermediatePosition.getX()][Configuration.intermediatePosition.getY()+1].setBackgroundColor(Configuration.defaultColor);
                                }
                                if(Objects.equals(Configuration.buttons[Configuration.intermediatePosition.getX()][Configuration.intermediatePosition.getY()].getBackgroundColor(), Configuration.player2Color)){
                                    Configuration.buttons[Configuration.intermediatePosition.getX()][Configuration.intermediatePosition.getY()].setBackgroundColor(Configuration.defaultColor);
                                    Configuration.buttons[Configuration.intermediatePosition.getX()-1][Configuration.intermediatePosition.getY()].setBackgroundColor(Configuration.defaultColor);
                                    Configuration.buttons[Configuration.intermediatePosition.getX()+1][Configuration.intermediatePosition.getY()].setBackgroundColor(Configuration.defaultColor);
                                }
                            }else{
                                System.out.println("Vide");
                            }
                            Configuration.file.saveSauvgardeToFile(DomineeringGame.this.inputTitle.getText(),Configuration.row,Configuration.column,Configuration.machine);
                            if(Configuration.labelMessage!=null){
                                Configuration.labelMessage.setText("Game sauvgarded successufully");
                                Configuration.labelMessage.toVisible(true);
                            }
                            DomineeringGame.this.sauvgardeFrame.Close();
                            DomineeringGame.this.sauvgardeFrame=null;
                        }else{
                            DomineeringGame.this.labelInputSauvgarde.setColor("#ff0000");
                        }
                        DomineeringGame.this.oneToOneGameFrame.Show();
                    }
                });
            }
        }

    }
    /**
     * Creates and initializes a settings frame for configuring game parameters.
     *
     * This method sets up a frame with input fields for configuring various game settings,
     * such as the number of rows and columns, board and player colors, and background colors.
     * It also includes buttons for returning to the main frame, submitting changes, and associated
     * action listeners to handle these interactions. The entered settings are saved upon submission.
     *
     * Note: The method utilizes the window dimensions and current configuration settings to adjust
     * the layout dynamically based on the number of columns.
     *
     * @see #saveSettings()
     * @see #finalizeSettingFrame()
     * @see #makeMainFrame()
     */
    private void makeSettingFrame(){
        short width=0;
        short size=0;
        short left= 0;
        if(Configuration.column<=6){
            width= (short) (windowWidth-100);
            size=10;
            left=50;
        }else{
            width= (short) (windowWidth-200);
            size=20;
            left= 100;
        }
        short top= (short) ((windowHeight-460)/2);
        short height=80;
        short space=15;

        this.settingFrame = window.addFrame(0, 0, windowWidth, windowHeight, Configuration.mainBgColor);
        this.goToMainFrameButton=this.settingFrame.addButton(10,10, 100, 47, "Return", "", "Back to home page ", true, "#000000", "#ffffff", 22, "Arial", false, false);
        this.labelRow=this.settingFrame.addLabel(30,100,100,30,"Row : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputRow=this.settingFrame.addInput(280,100,200,30,""+Configuration.row,"","#000000","",28,"Arial",true,true,true);
        this.labelColumn=this.settingFrame.addLabel(30,150,100,30,"Column : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputColumn=this.settingFrame.addInput(280,150,200,30,""+Configuration.column,"","#000000","",22,"Arial",true,true,true);
        this.labelBoardColor=this.settingFrame.addLabel(30,200,180,30,"Board color : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputBoardColor=this.settingFrame.addInput(280,200,200,30,Configuration.defaultColor,"","#000000","",22,"Arial",true,true,true);
        this.labelPlayer1Color=this.settingFrame.addLabel(30,250,180,30,"PLayer 1 color : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputPlayer1Color=this.settingFrame.addInput(280,250,200,30,Configuration.player1Color,"","#000000","",22,"Arial",true,true,true);
        this.labelPlayer2Color=this.settingFrame.addLabel(30,300,180,30,"PLayer 2 color : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputPlayer2Color=this.settingFrame.addInput(280,300,200,30,Configuration.player2Color,"","#000000","",22,"Arial",true,true,true);
        this.labelIntermediateColor=this.settingFrame.addLabel(30,350,290,30,"Intermediate color : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputIntermediateColor=this.settingFrame.addInput(280,350,200,30,Configuration.intermediateColor,"","#000000","",22,"Arial",true,true,true);
        this.labelAppBackgroundColor=this.settingFrame.addLabel(30,400,290,30,"App background color : ","","",true,true,"#ffffff","",18,"Arial",true,false);
        this.inputAppBackgroundColor=this.settingFrame.addInput(280,400,200,30,Configuration.mainBgColor,"","#000000","",22,"Arial",true,true,true);
        this.saveSetting=this.settingFrame.addButton(160,470,150,30,"Submit","","Submit change",true,Configuration.mainBgColor,"#ffffff",22,"Arial",false,false);
        Configuration.labelMessage=this.settingFrame.addLabel(30,510,200,30,"","","",true,false,Configuration.mainBgColor,"#ffffff",18,"Arial",true,false);
        this.goToMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.settingFrame.Close();
                DomineeringGame.this.finalizeSettingFrame();
                DomineeringGame.this.makeMainFrame();
            }
        });
        this.saveSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.saveSettings();
                DomineeringGame.this.settingFrame.Close();
                DomineeringGame.this.finalizeSettingFrame();
                DomineeringGame.this.makeMainFrame();
            }
        });
    }
    /**
     * Saves the updated game settings entered by the user.
     *
     * This method retrieves values from input fields representing various game settings,
     * validates them, and updates the Configuration class with the new values. The settings
     * include the number of rows and columns, board and player colors, and background colors.
     * After updating the configurations, the method saves them to a configuration file.
     * If any input value is not in the correct format or range, an error message is displayed.
     *
     * Note: The method performs input validation and updates the Configuration class accordingly.
     *
     * @see Configuration
     */
    private void saveSettings() {
        try{
            // Get the values from the input fields
            short newRow = Short.parseShort(inputRow.getText());
            short newColumn = Short.parseShort(inputColumn.getText());
            String newBoardColor = inputBoardColor.getText();
            String newPlayer1Color = inputPlayer1Color.getText();
            String newPlayer2Color = inputPlayer2Color.getText();
            String newIntermediateColor = inputIntermediateColor.getText();
            String newAppBackgroundColor = inputAppBackgroundColor.getText();

            // Update the Configuration class with the new values
            if(newRow>5){
                Configuration.row = newRow;
            }
            if(newColumn>5){
                Configuration.column = newColumn;
            }
            domineeringGameInstance.setAttribute();
            if(Configuration.isColor(newBoardColor)){
                Configuration.defaultColor = newBoardColor;
            }
            if(Configuration.isColor(newPlayer1Color)){
                Configuration.player1Color = newPlayer1Color;
            }
            if(Configuration.isColor(newPlayer2Color)){
                Configuration.player2Color = newPlayer2Color;
            }
            if(Configuration.isColor(newIntermediateColor)){
                Configuration.intermediateColor = newIntermediateColor;
            }
            if(Configuration.isColor(newAppBackgroundColor)){
                Configuration.mainBgColor = newAppBackgroundColor;
            }
            // Perform any additional actions you need with the updated settings
            // Close the setting frame and transition to the main framen
            Configuration.file.saveConfigurations(Configuration.row,Configuration.column,Configuration.defaultColor,Configuration.player1Color, Configuration.player2Color,Configuration.intermediateColor,Configuration.mainBgColor);
        }catch (Exception e){
            Configuration.labelMessage.setVisible(true);
            Configuration.labelMessage.setText("The information is not correct");
        }

    }
    /**
     * Finalizes the setting frame by nullifying its components.
     *
     * This method sets all components of the setting frame to null, including buttons,
     * labels, and input fields. It is typically called when transitioning away from the
     * setting frame to ensure that resources are released and references are cleared.
     *
     * Note: This method is part of the cleanup process after the setting frame is no longer needed.
     */
    private void finalizeSettingFrame(){
        this.settingFrame =null;
        this.goToMainFrameButton=null;
        this.labelRow=null;
        this.inputRow=null;
        this.labelColumn=null;
        this.inputColumn=null;
        this.labelBoardColor=null;
        this.inputBoardColor=null;
        this.labelPlayer1Color=null;
        this.inputPlayer1Color=null;
        this.labelPlayer2Color=null;
        this.inputPlayer2Color=null;
        this.labelIntermediateColor=null;
        this.inputIntermediateColor=null;
        this.labelAppBackgroundColor=null;
        this.inputAppBackgroundColor=null;
        this.saveSetting=null;
        Configuration.labelMessage=null;

    }
}