package Helper;

import java.util.Arrays;

/**
 * The Sauvgard class represents a saved state in the game, including background colors,
 * title, formatted date and time, and the identifier of the machine.
 * @author Laamiri Ouail & Hajar Sadik
 * @version 1.0
 * @since 2023-12-01
 */
public class Sauvgard {
    /**
     * 2D array representing the background colors of the saved state.
     */
    String[][] backgroundColors;

    /**
     * The title of the saved state.
     */
    String title;

    /**
     * The formatted date and time when the state was saved.
     */
    String formattedDateTime;

    /**
     * The identifier of the machine associated with the saved state.
     */
    byte machine;

    /**
     * Constructs a Sauvgard object with the specified background colors, title,
     * formatted date and time, and machine identifier.
     *
     * @param backgroundColors 2D array representing the background colors.
     * @param title            The title of the saved state.
     * @param formattedDateTime The formatted date and time.
     * @param machine          The identifier of the machine.
     */
    public Sauvgard(String[][] backgroundColors, String title, String formattedDateTime, byte machine) {
        this.backgroundColors = new String[backgroundColors.length][backgroundColors[0].length];
        this.title = title;
        this.machine = machine;
        this.formattedDateTime = formattedDateTime;
        for (int i = 0; i < backgroundColors.length; i++) {
            for (int j = 0; j < backgroundColors[0].length; j++) {
                this.backgroundColors[i][j] = backgroundColors[i][j];
            }
        }
    }

    /**
     * Gets the background colors of the saved state.
     *
     * @return 2D array representing the background colors.
     */
    public String[][] getBackgroundColors() {
        return backgroundColors;
    }

    /**
     * Gets the title of the saved state.
     *
     * @return The title of the saved state.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the formatted date and time when the state was saved.
     *
     * @return The formatted date and time.
     */
    public String getFormattedDateTime() {
        return formattedDateTime;
    }

    /**
     * Gets the identifier of the machine associated with the saved state.
     *
     * @return The identifier of the machine.
     */
    public byte getMachine() {
        return machine;
    }

    /**
     * Returns a string representation of the Sauvgard object.
     *
     * @return A string representation of the form "Sauvgard{title='title', formattedDateTime='formattedDateTime'}".
     */
    @Override
    public String toString() {
        return "Sauvgard{" +
                "title='" + title + '\'' +
                ", formattedDateTime='" + formattedDateTime + '\'' +
                '}';
    }
}
