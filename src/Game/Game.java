package Game;

import Helper.Configuration;

public interface Game {
    /**
     * Sets up the initial configurations for the game, including the game window,
     * buttons, and other components.
     */
    default void setAttribute() {

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
    void loadConfigurations();
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
    void makeMainFrame();
    /**
     * Creates and initializes the game frame with buttons and UI elements.
     *
     * This method sets up the game frame, including buttons for gameplay, restart, and saving.
     * It also associates action listeners for various buttons, such as returning to the main frame,
     * saving the game, restarting the game, and accessing the help feature.
     *
     * Note: This method is responsible for creating the initial game frame layout and setting up button functionalities.
     */
    void makeGameFrame();
    /**
     * Finalizes the main frame by nullifying its components.
     *
     * This method sets all components of the main frame to null, including buttons and other UI elements.
     * It is typically called when transitioning away from the main frame to ensure that resources are released
     * and references are cleared.
     *
     * Note: This method is part of the cleanup process after the main frame is no longer needed.
     */
    void finalizeMainFrame();
    /**
     * Finalizes the one-to-one game frame by nullifying its components.
     *
     * This method sets all components of the one-to-one game frame to null, including buttons,
     * labels, and other UI elements. It is typically called when transitioning away from the
     * one-to-one game frame to ensure that resources are released and references are cleared.
     *
     * Note: This method is part of the cleanup process after the one-to-one game frame is no longer needed.
     */
    void finalizeOneToOneFrame();
    /**
     * Finalizes the frame for viewing saved games by setting all relevant components to null,
     * clearing references, and preparing for the next frame or action.
     *
     * This method sets the `SauvgardedTableFrame`, `SauvgardedFrame`, and `goToMainFrameButton` to null,
     * effectively releasing memory and resources associated with these components.
     * It is typically called when transitioning to another frame or completing the interaction with the saved games frame.
     */
    void finalizeSauvgardedFrame();
    /**
     * Restarts the Domineering game by resetting the game board, move counters, and message label.
     *
     * This method iterates through each button in the game board and sets its background color to the default color.
     * Additionally, it enables all buttons and resets the move counter to zero. It hides and clears the message label.
     * Finally, it saves the void colors configuration to the file for future reference.
     * This method is typically called when restarting the game after completion or when needed.
     */
    void restart();
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
    void makeSauvgardedFrame();
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
    void makeSauvgardedTable();
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
    void makeSauvgardeOneGameFrame();
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
    void makeSettingFrame();
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
    void saveSettings();
    /**
     * Finalizes the setting frame by nullifying its components.
     *
     * This method sets all components of the setting frame to null, including buttons,
     * labels, and input fields. It is typically called when transitioning away from the
     * setting frame to ensure that resources are released and references are cleared.
     *
     * Note: This method is part of the cleanup process after the setting frame is no longer needed.
     */
    void finalizeSettingFrame();
}
