package Helper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The StringTableFile class represents a utility class for managing string table data
 * related to the background colors of UI buttons. It provides methods to save and load
 * background colors to and from a file.
 * @author Laamiri Ouail
 * @version 1.0
 * @since 2023-12-01
 */
public class StringTableFile {
    private String[][] backgroundColors;
    private static StringTableFile instance;

    /**
     * Private constructor to create a new instance of StringTableFile.
     * Initializes the background colors based on the provided buttons.
     */
    private StringTableFile() {
        // Check and create the Data directory if it doesn't exist
        checkAndCreateDirectory("./Data");
        // Check and create files if they don't exist
        checkAndCreateFile(Configuration.fileColors);
        checkAndCreateFile(Configuration.fileSauvgarde);
        checkAndCreateFile(Configuration.fileConfiguration);
        this.setBackgroundColors();
    }
    /**
     * Checks if the file at the specified path exists. If not, creates an empty file.
     *
     * @param filePath The path of the file to check and create.
     */
    private void checkAndCreateFile(String filePath) {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                System.out.println("File created: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception according to your needs
            }
        } else {
            System.out.println("File already exists: " + filePath);
        }
    }
    /**
     * Checks if the directory at the specified path exists. If not, creates the directory and any necessary parent directories.
     *
     * @param directoryPath The path of the directory to check and create.
     */
    private void checkAndCreateDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created: " + directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception according to your needs
            }
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }
    }
    /**
     * Retrieves a singleton instance of the StringTableFile class.
     * If an instance does not exist, a new one is created based on the provided buttons.
     *
     * @return The singleton instance of StringTableFile.
     */
    public static StringTableFile getInstance() {
        if (instance == null) {
            instance = new StringTableFile();
        }
        return instance;
    }

    /**
     * Sets the background colors based on the provided UI buttons.
     */
    public void setBackgroundColors() {
        if (Configuration.buttons != null) {
            backgroundColors = new String[Configuration.buttons.length][Configuration.buttons[0].length];
            for (byte i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    if (Configuration.buttons[i][j] != null)
                        backgroundColors[i][j] = Configuration.buttons[i][j].getBackgroundColor();
                }
            }
        }
    }

    /**
     * Saves a default set of background colors to the specified file.
     */
    public void saveVoidColors() {
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
     */
    public void saveBackgroundColorToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileColors))) {
            for (int i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    writer.print(backgroundColors[i][j]);
                    // Add a delimiter (e.g., comma) between color values
                    if (j < backgroundColors.length) {
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

            // DomineeringGame domineeringGame=DomineeringGame.getInstance()
            for (byte i = 0; i < Configuration.buttons.length; i++) {
                for (byte j = 0; j < Configuration.buttons[0].length; j++) {
                    Configuration.buttons[i][j].setBackgroundColor(backgroundColors[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the configurations (row, column, colors) to the specified file.
     *
     * @param row                  The number of rows.
     * @param column               The number of columns.
     * @param defaultBoardColor    The default board color.
     * @param player1Color         The color for player 1.
     * @param player2Color         The color for player 2.
     * @param secondPossibleMoveColor The color for the second possible move.
     * @param backgroundAppColor   The background color of the application.
     */
    public void saveConfigurations(short row, short column, String defaultBoardColor, String player1Color,
                                   String player2Color, String secondPossibleMoveColor, String backgroundAppColor) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileConfiguration))) {
            writer.println(row + "," + column + "," + defaultBoardColor + "," + player1Color + ","
                    + player2Color + "," + secondPossibleMoveColor + "," + backgroundAppColor);
            // Move to the next line for the next row
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads configurations (row, column, colors) from the specified file.
     *
     * @return An array containing loaded configuration values.
     */
    public String[] loadConfigurations() {
        String[] configValues = null;
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

    /**
     * Saves a saved state to the specified file, including background colors, title, date and time, and machine identifier.
     *
     * @param title   The title of the saved state.
     * @param row     The number of rows in the saved state.
     * @param column  The number of columns in the saved state.
     * @param machine The machine identifier associated with the saved state.
     */
    public void saveSauvgardeToFile(String title, int row, int column, int machine) {
        //if(move==1) player is player-1 or player +1
        try (PrintWriter writer = new PrintWriter(new FileWriter(Configuration.fileSauvgarde, true))) {
            this.setBackgroundColors();
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            // Define the desired date and time format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            // Format the current date and time using the formatter
            String formattedDateTime = now.format(formatter);
            writer.println(formattedDateTime + "," + title + "," + row + "," + column + "," + machine);
            for (int i = 0; i < row; i++) {
                for (byte j = 0; j < column; j++) {
                    writer.print(backgroundColors[i][j]);
                    // Add a delimiter (e.g., comma) between color values
                    if (j < backgroundColors.length) {
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

    /**
     * Uploads saved states from the specified file and returns a list of Sauvgard objects.
     *
     * @return An ArrayList of Sauvgard objects representing saved states.
     */
    public ArrayList<Sauvgard> uploadSauvgardeFromFile() {
        String formattedDateTime = "";
        String title = "";
        ArrayList<Sauvgard> groupeSauvgarde = new ArrayList<>();
        String[][] backgroundColorss = new String[Configuration.row][Configuration.column];
        int ligne = 0;
        int row = 0;
        int column = 0;
        byte machine = 0;
        try {
            // Create a Scanner to read from the file
            Scanner scanner = new Scanner(new File(Configuration.fileSauvgarde));
            // Read and print each line from the file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process the line as needed (e.g., split and extract values)
                if (line.contains(",")) {
                    String[] values = line.split(",");

                    if (values.length == 5) {
                        formattedDateTime = values[0];
                        title = values[1];
                        row = Integer.parseInt(values[2]);
                        column = Integer.parseInt(values[3]);
                        machine = Byte.parseByte(values[4]);
                        backgroundColorss = new String[row][column];
                        ligne = 0;
                    } else {
                        for (int col = 0; col < column; col++) {
                            backgroundColorss[ligne][col] = values[col];
                        }
                        ligne++;
                    }
                    if (ligne == row) {
                        groupeSauvgarde.add(new Sauvgard(backgroundColorss, title, formattedDateTime, machine));
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
}
