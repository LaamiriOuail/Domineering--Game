package Helper;

import UI.Button;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The StringTableFile class represents a utility class for managing string table data
 * related to the background colors of UI buttons. It provides methods to save and load
 * background colors to and from a file.
 */
public class StringTableFile {
    private String[][] backgroundColors;
    private String fileColors="./Data/colors.txt";
    private String fileSauvgarde="./Data/sauvgarde.txt";
    private static  StringTableFile instance;
    /**
     * Private constructor to create a new instance of StringTableFile.
     * Initializes the background colors based on the provided buttons.
     *
     * @param buttons The 2D array of UI buttons.
     */
    private StringTableFile(Button[][] buttons){
       this.setBackgroundColors(buttons);
    }
    /**
     * Retrieves a singleton instance of the StringTableFile class.
     * If an instance does not exist, a new one is created based on the provided buttons.
     *
     * @param buttons The 2D array of UI buttons.
     * @return The singleton instance of StringTableFile.
     */
    public static  StringTableFile getInstance(Button[][] buttons){
        if(instance==null){
            instance=new StringTableFile(buttons);
        }
        return  instance;
    }
    /**
     * Sets the background colors based on the provided UI buttons.
     *
     * @param buttons The 2D array of UI buttons.
     */
    public void setBackgroundColors(Button[][] buttons){
        if(buttons!=null){
            backgroundColors=new String[buttons.length][buttons[0].length];
            for (byte i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    if(buttons[i][j]!=null)
                        backgroundColors[i][j]=buttons[i][j].getBackgroundColor();
                }
            }
        }
    }
    public void saveSauvgardeToFile(Button[][] buttons,String title){
        //if(move==1) player is player-1 or player +1
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.fileSauvgarde, true))) {
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            // Define the desired date and time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            // Format the current date and time using the formatter
            String formattedDateTime = now.format(formatter);
            writer.println(formattedDateTime+","+title);
            for (int i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    writer.print(backgroundColors[i][j]);
                    // Add a delimiter (e.g., comma) between color values
                    if (j < backgroundColors.length ) {
                        writer.print(",");
                    }
                }
                writer.println(); // Move to the next line for the next row
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Sauvgard> uploadSauvgardeFromFile() {
        String formattedDateTime = "";
        String title ="";
        ArrayList<Sauvgard> groupeSauvgarde=new ArrayList<>();
        String[][] backgroundColorss = new String[8][7];
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileSauvgarde))) {
            String line;
            int row = 0;
            int index=0;
            while ((line = reader.readLine()) != null && row < backgroundColorss.length) {
                String[] colors = line.split(",");
                if(colors.length==2){
                    formattedDateTime=colors[0];
                    title=colors[1];
                    row=0;
                }else{

                    for (byte col = 0; col < colors.length && col < backgroundColorss[row].length; col++) {
                        backgroundColorss[row][col] = colors[col];
                    }
                    row++;
                    if(row==7){
                        groupeSauvgarde.add(new Sauvgard(backgroundColorss,title,formattedDateTime));
                        for (int i=0;i<backgroundColorss.length;i++){
                            for (int j=0;j<backgroundColorss[0].length;j++){
                                System.out.println(backgroundColorss[i][j]);
                            }
                        }
                        System.out.println("----------------------------");
                    }
                }

            }
            //}
            return groupeSauvgarde;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves a default set of background colors to the specified file.
     *
     */
    public void saveVoidColors(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.fileColors))) {
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
            writer.println("#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff,#ffffff\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves the background colors to the specified file.
     *
     */
    public void saveBackgroundColorToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.fileColors))) {
            for (int i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    writer.print(backgroundColors[i][j]);
                    // Add a delimiter (e.g., comma) between color values
                    if (j < backgroundColors.length ) {
                        writer.print(",");
                    }
                }
                writer.println(); // Move to the next line for the next row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads background colors from the specified file and updates the UI buttons accordingly.
     *
     * @param buttons  The 2D array of UI buttons to update.
     */
    public void loadBackgroundColorFromFile(Button[][] buttons) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileColors))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < buttons.length) {
                String[] colors = line.split(",");
                for (byte col = 0; col < colors.length && col < buttons[row].length; col++) {
                    backgroundColors[row][col] = colors[col];
                }
                row++;
            }
            for(byte i=0;i<buttons.length;i++){
                for(byte j=0;j<buttons[0].length;j++){
                    buttons[i][j].setBackgroundColor(backgroundColors[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}