package Helper;

import UI.Button;

import java.io.*;
/**
 * The StringTableFile class represents a utility class for managing string table data
 * related to the background colors of UI buttons. It provides methods to save and load
 * background colors to and from a file.
 */
public class StringTableFile {
    private String[][] backgroundColors;
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
                    backgroundColors[i][j]=buttons[i][j].getBackgroundColor();
                }
            }
        }
    }
    /**
     * Saves a default set of background colors to the specified file.
     *
     * @param fileName The name of the file to save the background colors.
     */
    public void saveVoid(String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
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
     * @param fileName The name of the file to save the background colors.
     */
    public void saveBackgroundColorToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
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
     * @param fileName The name of the file to load background colors from.
     * @param buttons  The 2D array of UI buttons to update.
     */
    public void loadBackgroundColorFromFile(String fileName,Button[][] buttons) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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
                for(byte j=0;j<buttons.length;j++){
                    buttons[i][j].setBackgroundColor(backgroundColors[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}