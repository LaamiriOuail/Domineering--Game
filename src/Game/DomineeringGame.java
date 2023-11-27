package Game;

import Helper.Sauvgard;
import Helper.StringTableFile;
import UI.*;
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
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DomineeringGame {
    //region Argument
    private StringTableFile file=null;
    private final String filename="data.txt";
    private final String defaultColor="#ffffff";//of buttons
    private final String mainBgColor="#00008b";
    private final String backgroundGame="#000000";
    private Window window=null;
    private Frame mainFrame=null;
    private Button btnGameWithPerson=null;
    private Button btnGameWithAgent=null;
    private Button btnMainExit=null;
    private Button btnsauvgardedGamebtn=null;
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
    private int row;
    private int column;
    private Event event=null;
    private static DomineeringGame domineeringGameInstance=null;
    //endregion
    //region Constructeur
    private DomineeringGame(int row , int column) {
        this.window = Window.getInstance(600, 600, "Domineering", "", true);
        this.makeMainFrame();
        this.window.Show();
        this.row=row;
        this.column=column;
        this.event=new Event();
        this.buttons=new Button[this.column][this.row];
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
        this.btnGameWithPerson = this.mainFrame.addButton(100, 70, 400, 100, "Play with friend", "", "Click me to play with your friend", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnGameWithAgent = this.mainFrame.addButton(100, 190, 400, 100, "Play Laptop", "", "Click me to play with your laptop", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnsauvgardedGamebtn = this.mainFrame.addButton(100, 310, 400, 100, "Sauvgarded Game", "", "Click me to exit", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
        this.btnMainExit = this.mainFrame.addButton(100, 430, 400, 100, "Exit", "", "Click me to exit", true, "#0000ff", "#ffffff", 30, "Arial", true, false);
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
        this.oneToOneGameFrame = window.addFrame(0, 0, 600, 600, mainBgColor);
        this.domineeringFrame = this.oneToOneGameFrame.addFrame(30, 150, 540, 406, backgroundGame);
        this.goToMainFrameButton=this.oneToOneGameFrame.addButton(10,10, 100, 57, "Return", "", "Back to home page ", true, "#000000", "#ffffff", 20, "Arial", false, false);
        this.restartGameButton=this.oneToOneGameFrame.addButton(490,10, 100, 57, "Restart", "", "Restart game", true, "#000000", "#ffffff", 20, "Arial", false, false);        // Create a 2D array to store buttons
        this.sauvgardebtn=this.oneToOneGameFrame.addButton(350,10, 120, 57, "Sauvgarde", "", "sauvgarde game", true, "#000000", "#ffffff", 20, "Arial", false, false);        // Create a 2D array to store buttons
        this.labelMessage=this.oneToOneGameFrame.addLabel(30,80,540,60,"","","",true,false,mainBgColor,"#ffffff",24,"Arial",false,true);
        this.labelMessage.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                this.buttons[i][j] = this.domineeringFrame.addButton(j * 68, i * 58, 67, 57, "", "", i + "," + j, true, "#ffffff", defaultColor, 0, "Arial", false, false);
                this.buttons[i][j].addActionListener(event);
            }
        }
        event.setLabelMessage(this.labelMessage);
        event.setButtons(this.buttons);
        file=StringTableFile.getInstance(buttons);
        file.loadBackgroundColorFromFile(buttons);

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
            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    this.buttons[i][j].setBackgroundColor(defaultColor);
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
        this.SauvgardedFrame= window.addFrame(0, 0, 600, 600, mainBgColor);
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
        this.SauvgardedTableFrame=this.SauvgardedFrame.addFrame(10, 115, 580, 485, mainBgColor);
        this.SauvgardedTableFrame.setLayout(new BorderLayout());
        file=StringTableFile.getInstance(buttons);
        ArrayList<Sauvgard> sauvgards=file.uploadSauvgardeFromFile();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Description", "Date"});
        JTable table = new JTable(model);
        // Set the preferred width for the "ID" column
        // Set the preferred width for the ID column
        int idColumnIndex = 0;  // Assuming the ID is in the first column
        int idColumnWidth = 10;  // Set your desired width for the ID column
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(180);
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
            DomineeringGame.this.sauvgardeFrame=DomineeringGame.this.oneToOneGameFrame.addFrame(30,100,540,50,mainBgColor);
            DomineeringGame.this.inputTitle=DomineeringGame.this.sauvgardeFrame.addInput(80,10,200,30,"","","","#ffffff",18,"#000000",false,false,true);
            DomineeringGame.this.submitSauvgarde=DomineeringGame.this.sauvgardeFrame.addButton(300,10, 100, 30, "Submit", "", "Sauvgarder game", true, "#000000", "#ffffff", 18, "Arial", false, false);        //
            DomineeringGame.this.refuseSauvgarde=DomineeringGame.this.sauvgardeFrame.addButton(410,10, 100, 30, "refuse", "", "refuse sauvgarde game", true, "#000000", "#ffffff", 18, "Arial", false, false);        //
            DomineeringGame.this.labelInputSauvgarde=DomineeringGame.this.sauvgardeFrame.addLabel(10,10,80,30,"Description :","","",true,true,"#ffffff",mainBgColor,22,"",true,false);
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
                        DomineeringGame.this.file.saveSauvgardeToFile(buttons,DomineeringGame.this.inputTitle.getText());
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
