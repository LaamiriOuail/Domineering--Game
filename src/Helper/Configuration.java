package Helper;

import Search.DomineeringSearch;
import Search.GameSearch;
import UI.Button;
import UI.Label;

import java.awt.*;

/**
 * Configuration class holds static variables and methods used throughout the Domineering game.
 * @author Laamiri Ouail & Hajar Sadik
 * @version 1.0
 * @since 2023-12-01
 */
public abstract class Configuration {

    /**
     * Number of help moves available for player 1.
     */
    public static short helpPlayer1 = 3;

    /**
     * Number of help moves available for player 2.
     */
    public static short helpPlayer2 = 3;

    /**
     * Button to trigger the help functionality.
     */
    public static Button helpBtn = null;

    /**
     * Number of rows in the game board.
     */
    public static short row = 7;

    /**
     * Number of columns in the game board.
     */
    public static short column = 7;

    /**
     * Color code for player 1's pieces.
     */
    public static String player1Color = "#800080";

    /**
     * Color code for player 2's pieces.
     */
    public static String player2Color = "#000000";

    /**
     * Color code for intermediate state during help moves.
     */
    public static String intermediateColor = "#F5F5DC";

    /**
     * Default color code for empty spaces on the board.
     */
    public static String defaultColor = "#ffffff";

    /**
     * Main background color code.
     */
    public static String mainBgColor = "#00008b";

    /**
     * File path for storing color configurations.
     */
    public static String fileColors = "./Data/colors.txt";

    /**
     * File path for saving game states.
     */
    public static String fileSauvgarde = "./Data/sauvegarde.txt";

    /**
     * File path for storing game configurations.
     */
    public static String fileConfiguration = "./Data/configurations.txt";

    /**
     * Color code for machine-controlled pieces.
     */
    public static String machineColor = "#000000";

    /**
     * 2D array representing the game buttons.
     */
    public static Button[][] buttons = null;

    /**
     * Current player (1 or 2).
     */
    public static byte player = 1;

    /**
     * Machine player identifier.
     */
    public static byte machine = 0;

    /**
     * Move identifier.
     */
    public static byte move = 0;

    /**
     * Label for displaying messages.
     */
    public static Label labelMessage = null;

    /**
     * Lower bound percentage for asymmetrical moves.
     */
    public static int asymPercentFloor = 62;

    /**
     * Upper bound percentage for asymmetrical moves.
     */
    public static int asymPercentCeil = 63;

    /**
     * Asymmetrical move identifier.
     */
    public static int asymMove;

    /**
     * Coefficient for safe moves.
     */
    public static int safeMovesCoef = 10;

    /**
     * Position for intermediate state during help moves.
     */
    public static Position intermediatePosition = null;

    /**
     * Move for help functionality.
     */
    public static Move helpMove = null;

    /**
     * Sets the current move.
     *
     * @param move The move identifier.
     */
    public static void setMove(byte move) {
        Configuration.move = move;
    }

    /**
     * Singleton instance of the DomineeringSearch class.
     */
    public static GameSearch domineeringSearch = DomineeringSearch.getInstance();

    /**
     * StringTableFile instance for file-related operations.
     */
    public static StringTableFile file = StringTableFile.getInstance();

    /**
     * Provides help to the player based on the current game state.
     */
    public static void helpPlayer() {
        testHelpButton();
        if (Configuration.player == 1) {
            Configuration.helpPlayer1--;
            int[] best = Configuration.domineeringSearch.minmax((new Board()).getBoard(), 4, true, new int[]{0}, new int[]{10000000});
            Configuration.domineeringSearch.playHelpMove(best[1], best[2]);
        } else if (Configuration.player == 2) {
            Configuration.helpPlayer2--;
            int[] best = Configuration.domineeringSearch.minmax((new Board()).getBoard(), 4, true, new int[]{0}, new int[]{10000000});
            Configuration.domineeringSearch.playHelpMove(best[1], best[2]);
        }
        Configuration.helpBtn.toEnabled(false);
    }

    /**
     * Initializes the parameters for help functionality.
     */
    public static void initializeHelpPlayerParameters() {
        Configuration.helpPlayer1 = 3;
        Configuration.helpPlayer2 = 3;
        Configuration.player = 1;
    }

    /**
     * Tests if the help button should be enabled based on the current game state.
     */
    public static void testHelpButton() {
        if (Configuration.move == 0) {
            if (Configuration.player == 1) {
                if (Configuration.helpPlayer1 > 0 && Configuration.helpPlayer1 <= 3) {
                    Configuration.helpBtn.toEnabled(true);
                } else {
                    Configuration.helpBtn.toEnabled(false);
                }
            } else if (Configuration.player == 2) {
                if (Configuration.helpPlayer2 > 0 && Configuration.helpPlayer2 <= 3) {
                    Configuration.helpBtn.toEnabled(true);
                } else {
                    Configuration.helpBtn.toEnabled(false);
                }
            }
        }
    }
    public static boolean isColor(String hexaColor){
        if (hexaColor == null) {
            return false;
        }

        Color myBackgroundColor = null;
        try {
            myBackgroundColor = Color.decode(hexaColor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
