package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * A custom JTextField with additional features for setting text, tooltip, color, background color, font, and editability.
 */
public class TextField extends JTextField {

    /**
     * Constructs a TextField with specified properties.
     *
     * @param left         The left position of the TextField.
     * @param top          The top position of the TextField.
     * @param width        The width of the TextField.
     * @param height       The height of the TextField.
     * @param text         The initial text of the TextField.
     * @param toolTip      The tooltip to display when hovering over the TextField.
     * @param color        The text color in hexadecimal format (e.g., "#RRGGBB").
     * @param backgroundColor The background color in hexadecimal format.
     * @param fontSize     The font size.
     * @param fontFamily   The font family.
     * @param bold         Set to true for bold text.
     * @param italic       Set to true for italic text.
     * @param isEditable   Set to true to allow text editing, false to make it read-only.
     */
    public TextField(int left, int top, int width, int height, String text, String toolTip, String color, String backgroundColor, int fontSize, String fontFamily, boolean bold, boolean italic, boolean isEditable) {
        this.resize(left, top, width, height);
        this.setText(text);
        this.setTooltip(toolTip);
        this.setColor(color);
        this.setBackgroundColor(backgroundColor);
        this.setFont(fontSize, fontFamily, bold, italic);
        this.canEdit(isEditable);
    }

    /**
     * Sets the text for the TextField if the text is not empty.
     *
     * @param text The text to set for the TextField.
     */
    public void setText(String text) {
        if (!"".equals(text)) {
            this.setText(text);
        }
    }

    /**
     * Sets the tooltip for the TextField if the tooltip is not empty.
     *
     * @param tooltip The tooltip to set for the TextField.
     */
    public void setTooltip(String tooltip) {
        if (!"".equals(tooltip)) {
            this.setToolTipText(tooltip);
        }
    }

    /**
     * Resizes the TextField to the specified dimensions.
     *
     * @param left   The left position.
     * @param top    The top position.
     * @param width  The width.
     * @param height The height.
     */
    public void resize(int left, int top, int width, int height) {
        this.setBounds(left, top, width, height);
    }

    /**
     * Sets whether the TextField is editable or not.
     *
     * @param enable Set to true to allow editing, false to make it read-only.
     */
    public void canEdit(boolean enable) {
        this.setEditable(enable);
    }

    /**
     * Sets the text color of the TextField.
     *
     * @param hexaColor The text color in hexadecimal format (e.g., "#RRGGBB").
     */
    public void setColor(String hexaColor) {
        Color myColor = null;
        try {
            myColor = Color.decode(hexaColor);
        } catch (NumberFormatException e) {
            System.err.println("TextField::setColor::The provided hexadecimal color code is not valid.");
        }
        if (myColor != null) {
            this.setForeground(myColor);
        }
    }

    /**
     * Sets the background color of the TextField.
     *
     * @param hexaColor The background color in hexadecimal format.
     */
    public void setBackgroundColor(String hexaColor) {
        Color myBackgroundColor = null;
        try {
            myBackgroundColor = Color.decode(hexaColor);
        } catch (NumberFormatException e) {
            System.err.println("TextField::setBackgroundColor::The provided hexadecimal color code is not valid.");
        }
        if (myBackgroundColor != null) {
            this.setBackground(myBackgroundColor);
        }
    }

    /**
     * Sets the font properties for the TextField.
     *
     * @param fontSize   The font size.
     * @param fontFamily The font family.
     * @param bold       Set to true for bold text.
     * @param italic     Set to true for italic text.
     */
    public void setFont(int fontSize, String fontFamily, boolean bold, boolean italic) {
        boolean isFontAvailable = false;
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String name : fontNames) {
            if (name.equals(fontFamily)) {
                isFontAvailable = true;
                break;
            }
        }
        if (isFontAvailable) {
            int fontStyle = Font.PLAIN;
            if (bold && italic) {
                fontStyle = Font.ITALIC + Font.BOLD;
            } else if (bold) {
                fontStyle = Font.BOLD;
            } else if (italic) {
                fontStyle = Font.ITALIC;
            }
            this.setFont(new Font(fontFamily, fontStyle, fontSize));
        }
    }
}
