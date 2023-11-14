package UI;

import javax.swing.*;
import java.awt.*;

/**
 * A custom JPanel that represents a frame with additional features for setting bounds and background color.
 */
public class Frame extends JPanel {

    /**
     * Constructs a Frame with specified properties.
     *
     * @param left           The left position of the Frame.
     * @param top            The top position of the Frame.
     * @param width          The width of the Frame.
     * @param height         The height of the Frame.
     * @param backgroundColor The background color in hexadecimal format (e.g., "#RRGGBB").
     */
    Frame(int left, int top, int width, int height, String backgroundColor) {
        this.setBounds(left, top, width, height);
        this.setLayout(null);
        Color myBackgroundColor = null;
        try {
            myBackgroundColor = Color.decode(backgroundColor);
            this.setBackground(myBackgroundColor);
        } catch (NumberFormatException e) {
            System.err.println("Frame::bgc::The provided hexadecimal color code is not valid.");
        }
    }

    /**
     * Adds a Label to the Frame with specified properties.
     *
     * @param left          The left position of the Label.
     * @param top           The top position of the Label.
     * @param width         The width of the Label.
     * @param height        The height of the Label.
     * @param text          The text to display on the Label.
     * @param icon          The path to the icon image file for the Label.
     * @param toolTip       The tooltip to display when hovering over the Label.
     * @param enable        Set to true to enable the Label, false to disable it.
     * @param color         The text color in hexadecimal format (e.g., "#RRGGBB").
     * @param backgroundColor The background color in hexadecimal format.
     * @param fontSize      The font size.
     * @param fontFamily    The font family.
     * @param bold          Set to true for bold text.
     * @param italic        Set to true for italic text.
     * @return The created Label component.
     */
    public Label addLabel(int left, int top, int width, int height, String text, String icon, String toolTip, boolean enable, String color, String backgroundColor, int fontSize, String fontFamily, boolean bold, boolean italic) {
        Label label = new Label(left, top, width, height, text, icon, toolTip, enable, color, backgroundColor, fontSize, fontFamily, bold, italic);
        this.add(label);
        return label;
    }

    /**
     * Adds a Button to the Frame with specified properties.
     *
     * @param left          The left position of the Button.
     * @param top           The top position of the Button.
     * @param width         The width of the Button.
     * @param height        The height of the Button.
     * @param text          The text to display on the Button.
     * @param icon          The path to the icon image file for the Button.
     * @param toolTip       The tooltip to display when hovering over the Button.
     * @param enable        Set to true to enable the Button, false to disable it.
     * @param color         The text color in hexadecimal format (e.g., "#RRGGBB").
     * @param backgroundColor The background color in hexadecimal format.
     * @param fontSize      The font size.
     * @param fontFamily    The font family.
     * @param bold          Set to true for bold text.
     * @param italic        Set to true for italic text.
     * @return The created Button component.
     */
    public Button addButton(int left, int top, int width, int height, String text, String icon, String toolTip, boolean enable, String color, String backgroundColor, int fontSize, String fontFamily, boolean bold, boolean italic) {
        Button btn = new Button(left, top, width, height, text, icon, toolTip, enable, color, backgroundColor, fontSize, fontFamily, bold, italic);
        this.add(btn);
        return btn;
    }

    /**
     * Adds a child Frame to the current Frame with specified properties.
     *
     * @param left           The left position of the child Frame.
     * @param top            The top position of the child Frame.
     * @param width          The width of the child Frame.
     * @param height         The height of the child Frame.
     * @param backgroundColor The background color of the child Frame in hexadecimal format.
     * @return The created child Frame component.
     */
    public Frame addFrame(int left, int top, int width, int height, String backgroundColor) {
        Frame frame = new Frame(left, top, width, height, backgroundColor);
        this.add(frame);
        return frame;
    }

    /**
     * Adds an input TextField to the Frame with specified properties.
     *
     * @param left          The left position of the TextField.
     * @param top           The top position of the TextField.
     * @param width         The width of the TextField.
     * @param height        The height of the TextField.
     * @param text          The initial text for the TextField.
     * @param toolTip       The tooltip to display when hovering over the TextField.
     * @param color         The text color in hexadecimal format (e.g., "#RRGGBB").
     * @param backgroundColor The background color in hexadecimal format.
     * @param fontSize      The font size.
     * @param fontFamily    The font family.
     * @param bold          Set to true for bold text.
     * @param italic        Set to true for italic text.
     * @param isEditable    Set to true to allow editing of the TextField, false to make it read-only.
     * @return The created TextField component.
     */
    public TextField addInput(int left, int top, int width, int height, String text, String toolTip, String color, String backgroundColor, int fontSize, String fontFamily, boolean bold, boolean italic, boolean isEditable) {
        TextField input = new TextField(left, top, width, height, text, toolTip, color, backgroundColor, fontSize, fontFamily, bold, italic, isEditable);
        this.add(input);
        return input;
    }

    /**
     * Shows the Frame, making it visible on the screen.
     */
    public void Show() {
        this.setVisible(true);
    }

    /**
     * Closes the Frame, making it invisible on the screen.
     */
    public void Close() {
        this.setVisible(false);
    }
}
