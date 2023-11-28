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

public class DomineeringGame {
    //region Argument
    private StringTableFile file=null;
    private Window window;
    private Frame mainFrame=null;
    private Button btnGameWithPerson=null;
    private Button btnGameWithAgent=null;
    private Button btnMainExit=null;
    private Button btnsauvgardedGamebtn=null;
    private Button btnSetting=null;
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
    private Label labelMessage=null;
    private Frame SauvgardedFrame=null;
    private Frame SauvgardedTableFrame=null;
    private Button[][] buttons=null;
    private int windowHeight;
    private int windowWidth;
    private Event event=null;
    private static DomineeringGame domineeringGameInstance=null;
    //endregion
    //region Constructeur
    private DomineeringGame() {
        this.event=new Event();
        this.buttons=new Button[Configuration.row][Configuration.column];
        this.windowHeight=(Configuration.row-1)*58+57+200;
        this.windowWidth=(Configuration.column-1)*68+67+60;
        this.window = Window.getInstance(windowWidth, windowHeight, "Domineering", "", true);
        this.makeMainFrame();
        this.window.Show();

    }
    private void setAttribute() {
        this.event=new Event();
        this.buttons=new Button[Configuration.row][Configuration.column];
        this.windowHeight=(Configuration.row-1)*58+57+200;
        this.windowWidth=(Configuration.column-1)*68+67+60;
        this.window = Window.getInstance(windowWidth, windowHeight, "Domineering", "", true);
    }
    public static DomineeringGame getInstance(int row , int column ){
        if(domineeringGameInstance==null){
            domineeringGameInstance=new DomineeringGame();
        }
        return  domineeringGameInstance;
    }
    //endregion
    //region make Main Window
    private void makeMainFrame() {
        short top= (short) ((windowHeight-460)/2);
        short width= (short) (windowWidth-200);
        short left= 100;
        short height=80;
        short space=15;
        this.mainFrame = window.addFrame(0, 0, windowWidth, windowHeight, Configuration.mainBgColor);
        this.btnGameWithPerson = this.mainFrame.addButton(left, top, windowWidth-200, height, "Play with friend", "", "Click me to play with your friend", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnGameWithAgent = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Play Laptop", "", "Click me to play with your laptop", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnsauvgardedGamebtn = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Sauvgarded Game", "", "Click me to exit", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnSetting = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Setting", "", "Click me to go setting", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnMainExit = this.mainFrame.addButton(left, top+= (short) (height+space), width, height, "Exit", "", "Click me to exit", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
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
    }
    //endregion
    //region make One to One play window
    private void makeOneToOneFrame() {
        //this.oneToOneGameFrame = window.addFrame(0, 0, 600, 600, mainBgColor);
        this.oneToOneGameFrame = window.addFrame(0, 0, windowWidth, windowHeight, Configuration.mainBgColor);
        this.domineeringFrame = this.oneToOneGameFrame.addFrame(30, 150, this.windowWidth-62, this.windowHeight-169, Configuration.mainBgColor);
        this.goToMainFrameButton=this.oneToOneGameFrame.addButton(10,10, 100, 57, "Return", "", "Back to home page ", true, "#000000", "#ffffff", 20, "Arial", false, false);
        this.restartGameButton=this.oneToOneGameFrame.addButton(windowWidth-110,10, 100, 57, "Restart", "", "Restart game", true, "#000000", "#ffffff", 20, "Arial", false, false);        // Create a 2D array to store buttons
        this.sauvgardebtn=this.oneToOneGameFrame.addButton(windowWidth-250,10, 120, 57, "Sauvgarde", "", "sauvgarde game", true, "#000000", "#ffffff", 20, "Arial", false, false);        // Create a 2D array to store buttons
        this.labelMessage=this.oneToOneGameFrame.addLabel(30,80,windowWidth-60,60,"","","",true,false, Configuration.mainBgColor,"#ffffff",24,"Arial",false,true);
        this.labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < Configuration.row; i++) {
            for (int j = 0; j < Configuration.column; j++) {
                this.buttons[i][j] = this.domineeringFrame.addButton(j * 68, i * 58, 67, 57, "", "", i + "," + j, true, "#ffffff", Configuration.defaultColor, 0, "Arial", false, false);
                this.buttons[i][j].addActionListener(event);
            }
        }
        event.setLabelMessage(this.labelMessage);
        event.setButtons(this.buttons);
        //file=StringTableFile.getInstance(buttons);
        //file.loadBackgroundColorFromFile(buttons);

        this.goToMainFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DomineeringGame.this.oneToOneGameFrame.Close();
                DomineeringGame.this.finalizeOneToOneFrame();
                DomineeringGame.this.makeMainFrame();
                DomineeringGame.this.event.setMove((byte)0);
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
                DomineeringGame.this.domineeringFrame.Show();
            }
        });
    }
    private void finalizeMainFrame() {
        this.mainFrame = null;
        this.btnGameWithPerson = null;
        this.btnGameWithAgent = null;
        this.btnsauvgardedGamebtn = null;
        this.btnMainExit = null;
    }
    private void finalizeOneToOneFrame() {
        this.oneToOneGameFrame = null;
        this.domineeringFrame = null;
        this.goToMainFrameButton=null;
        this.restartGameButton=null;
        this.sauvgardebtn=null;
        this.labelMessage=null;
    }
    private void finalizeSauvgardedFrame(){
        this.SauvgardedTableFrame=null;
        this.SauvgardedFrame=null;
        this.goToMainFrameButton=null;
    }
    private void restart(){
            for (int i = 0; i < Configuration.row; i++) {
                for (int j = 0; j < Configuration.column; j++) {
                    this.buttons[i][j].setBackgroundColor(Configuration.defaultColor);
                    this.buttons[i][j].toEnabled(true);
                }
            }
            event.setMove((byte)0);
            this.labelMessage.toVisible(false);
            this.labelMessage.setText("");
            file=StringTableFile.getInstance(buttons);
            file.saveVoidColors();
    }
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
    private void makeSauvgardedTable(){
        file=StringTableFile.getInstance(buttons);
        ArrayList<Sauvgard> sauvgards=file.uploadSauvgardeFromFile();
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
                                Configuration.row=svg.getBackgroundColors().length;
                                Configuration.column=svg.getBackgroundColors()[0].length;
                                DomineeringGame.this.setAttribute();
                                DomineeringGame.this.makeOneToOneFrame();
                                for(byte i=0;i<buttons.length;i++){
                                    for(byte j=0;j<buttons[0].length;j++){
                                        DomineeringGame.this.buttons[i][j].setBackgroundColor(svg.getBackgroundColors()[i][j]);
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
    private void makeSauvgardeOneGameFrame(){
        if(DomineeringGame.this.sauvgardeFrame==null){
            DomineeringGame.this.oneToOneGameFrame.Close();
            if(DomineeringGame.this.labelMessage!=null){
                if (!Objects.equals(labelMessage.getText(), "")) {
                    DomineeringGame.this.labelMessage.setText("");
                    DomineeringGame.this.labelMessage.toVisible(false);
                }
            }
            DomineeringGame.this.sauvgardeFrame=DomineeringGame.this.oneToOneGameFrame.addFrame(30,100,540,50, Configuration.mainBgColor);
            DomineeringGame.this.inputTitle=DomineeringGame.this.sauvgardeFrame.addInput(80,10,200,30,"","","","#ffffff",18,"#000000",false,false,true);
            DomineeringGame.this.submitSauvgarde=DomineeringGame.this.sauvgardeFrame.addButton(300,10, 100, 30, "Submit", "", "Sauvgarder game", true, "#000000", "#ffffff", 18, "Arial", false, false);        //
            DomineeringGame.this.refuseSauvgarde=DomineeringGame.this.sauvgardeFrame.addButton(410,10, 100, 30, "refuse", "", "refuse sauvgarde game", true, "#000000", "#ffffff", 18, "Arial", false, false);        //
            DomineeringGame.this.labelInputSauvgarde=DomineeringGame.this.sauvgardeFrame.addLabel(10,10,80,30,"Description :","","",true,true,"#ffffff", Configuration.mainBgColor,22,"",true,false);
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
                    DomineeringGame.this.file=StringTableFile.getInstance(buttons);
                    if(!Objects.equals(DomineeringGame.this.inputTitle.getText(), "")){
                            /*
                            if(event.getMove()==1){
                                if(event.getPlayer()==1) player=2;
                                else if(event.getPlayer()==2) player=1;
                            }else if(event.getMove()==0){
                                player=event.getPlayer();
                            }
                            */
                        DomineeringGame.this.file.loadBackgroundColorFromFile(buttons);
                        DomineeringGame.this.file.saveSauvgardeToFile(buttons,DomineeringGame.this.inputTitle.getText(),Configuration.row,Configuration.column);
                        if(DomineeringGame.this.labelMessage!=null){
                            labelMessage.setText("Game sauvgarded successufully");
                            labelMessage.toVisible(true);
                        }
                        DomineeringGame.this.sauvgardeFrame.Close();
                        DomineeringGame.this.sauvgardeFrame=null;
                    }else{
                        DomineeringGame.this.labelInputSauvgarde.setColor("#ff0000");
                    }
                    DomineeringGame.this.oneToOneGameFrame.Show();
                    ArrayList<Sauvgard> sauvgards=file.uploadSauvgardeFromFile();
                }
            });
        }
    }
    @Override
    protected void finalize() throws Throwable {
        this.window=null;
    }
}
