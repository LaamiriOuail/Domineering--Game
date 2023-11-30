package Helper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


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
     */
    private StringTableFile(){
       this.setBackgroundColors();
    }
    /**
     * Retrieves a singleton instance of the StringTableFile class.
     * If an instance does not exist, a new one is created based on the provided buttons.
     *
     * @return The singleton instance of StringTableFile.
     */
    public static  StringTableFile getInstance(){
        if(instance==null){
            instance=new StringTableFile();
        }
        return  instance;
    }
    /**
     * Sets the background colors based on the provided UI buttons.
     *
     */
    public void setBackgroundColors(){
        if(Configuration.buttons!=null){
            backgroundColors=new String[Configuration.buttons.length][Configuration.buttons[0].length];
            for (byte i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    if(Configuration.buttons[i][j]!=null)
                        backgroundColors[i][j]=Configuration.buttons[i][j].getBackgroundColor();
                }
            }
        }
    }
    public void saveSauvgardeToFile(String title,int row,int column,int machine){
        //if(move==1) player is player-1 or player +1
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileSauvgarde, true))) {
            this.setBackgroundColors();
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            // Define the desired date and time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            // Format the current date and time using the formatter
            String formattedDateTime = now.format(formatter);
            writer.println(formattedDateTime+","+title+","+row+","+column+","+machine);
            for (int i = 0; i < row; i++) {
                for (byte j = 0; j < column; j++) {
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
            System.out.println(e);
        }
    }
    public ArrayList<Sauvgard> uploadSauvgardeFromFile() {
        String formattedDateTime = "";
        String title = "";
        ArrayList<Sauvgard> groupeSauvgarde = new ArrayList<>();
        String[][] backgroundColorss = new String[Configuration.row][Configuration.column];
        int ligne=0;
        int row=0;
        int column=0;
        byte machine=0;
        try {
            // Create a Scanner to read from the file
            Scanner scanner = new Scanner(new File(Configuration.fileSauvgarde));
            // Read and print each line from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process the line as needed (e.g., split and extract values)
                if (line.contains(",")) {
                    String[] values = line.split(",");

                    if(values.length==5){
                        formattedDateTime=values[0];
                        title=values[1];
                        row= Integer.parseInt(values[2]);
                        column= Integer.parseInt(values[3]);
                        machine=Byte.parseByte(values[4]);
                        backgroundColorss = new String[row][column];
                        ligne=0;
                    }else {
                        for(int col=0;col<column;col++){
                            backgroundColorss[ligne][col]=values[col];
                        }
                        ligne++;
                    }
                    if(ligne==row){
                        groupeSauvgarde.add(new Sauvgard(backgroundColorss,title,formattedDateTime,machine));
                    }
                }
            }
            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.err.println("File not found: " + Configuration.fileSauvgarde);
        }
        return groupeSauvgarde;
    }


    /**
     * Saves a default set of background colors to the specified file.
     *
     */
    public void saveVoidColors(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileColors))) {
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
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileColors))) {
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
     */
    public void loadBackgroundColorFromFile() {
        setBackgroundColors();
        try (BufferedReader reader = new BufferedReader(new FileReader(Configuration.fileColors))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < Configuration.buttons.length) {
                String[] colors = line.split(",");
                for (byte col = 0; col < colors.length && col < Configuration.buttons[row].length; col++) {
                    backgroundColors[row][col] = colors[col];
                }
                row++;
            }

            //DomineeringGame domineeringGame=DomineeringGame.getInstance()
            for(byte i=0;i<Configuration.buttons.length;i++){
                for(byte j=0;j<Configuration.buttons[0].length;j++){
                    Configuration.buttons[i][j].setBackgroundColor(backgroundColors[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveConfiguarations(short row,short column,String defaultBoardColor,String player1Color,String player2Color,String secondPossibleMoveColor,String backgroundAppColor) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileConfiguration))) {
            writer.println(row+","+column+","+defaultBoardColor+","+player1Color+","+player2Color+","+secondPossibleMoveColor+","+backgroundAppColor); // Move to the next line for the next row
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[] loadConfigurations() {
        String[] configValues=null;
        try (BufferedReader reader = new BufferedReader(new FileReader(Configuration.fileConfiguration))) {
            String line = reader.readLine();
            if (line != null) {
                configValues = line.split(",");
            } else {
                System.out.println("Empty configuration file.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return configValues;
    }
}
