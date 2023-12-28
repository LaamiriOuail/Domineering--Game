package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * A custom JLabel with additional features for setting text, tooltip, icon, enabled state, color, background color, and font.
 * @author Laamiri Ouail & Hajar Sadik
 * @version 1.0
 * @since 2023-12-01
 */
public class Label extends JLabel {

    /**
     * Constructs a Label with specified properties.
     *
     * @param left           The left position of the Label.
     * @param top            The top position of the Label.
     * @param width          The width of the Label.
     * @param height         The height of the Label.
     * @param text           The text to display on the Label.
     * @param icon           The path to the icon image file for the Label.
     * @param toolTip        The tooltip to display when hovering over the Label.
     * @param isEnable       Set to true to enable the Label, false to disable it.
     * @param color          The text color in hexadecimal format (e.g., "#RRGGBB").
     * @param backgroundColor The background color in hexadecimal format.
     * @param fontSize       The font size.
     * @param fontFamily     The font family.
     * @param isBold         Set to true for bold text.
     * @param isItalic       Set to true for italic text.
     */
    public Label(int left, int top, int width, int height, String text, String icon, String toolTip, boolean isEnable,boolean isVisible, String color, String backgroundColor, int fontSize, String fontFamily, boolean isBold, boolean isItalic) {
        this.resize(left, top, width, height);
        this.setText(text);
        this.setTooltip(toolTip);
        this.setIcon(icon);
        this.toEnabled(isEnable);
        this.setBounds(left, top, width, height);
        this.setColor(color);
        this.setBackgroundColor(backgroundColor);
        this.setFont(fontSize, fontFamily, isBold, isItalic);
        this.toVisible(isVisible);
    }

    /**
     * Sets the tooltip for the Label if the tooltip is not empty.
     *
     * @param tooltip The tooltip to set for the Label.
     */
    public void setTooltip(String tooltip) {
        if (!"".equals(tooltip)) {
            this.setToolTipText(tooltip);
        }
    }

    /**
     * Sets the text for the Label if the text is not empty.
     *
     * @param text The text to set for the Label.
     */

    /**
     * Resizes the Label to the specified dimensions.
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
     * Sets whether the Label is enabled or disabled.
     *
     * @param enable Set to true to enable the Label, false to disable it.
     */
    public void toEnabled(boolean enable) {
        this.setEnabled(enable);
    }
    public void toVisible(boolean visible){
        this.setVisible(visible);
    }
    /**
     * Sets the icon for the Label if the icon file exists.
     *
     * @param pathToIcon The path to the icon image file.
     */
    public void setIcon(String pathToIcon) {
        File iconFile = new File(pathToIcon);
        if (iconFile.exists()) {
            ImageIcon iconImage = new ImageIcon(pathToIcon);
            this.setIcon(iconImage);
        }
    }

    /**
     * Sets the text color of the Label.
     *
     * @param hexaColor The text color in hexadecimal format (e.g., "#RRGGBB").
     */
    public void setColor(String hexaColor) {
        Color myColor = null;
        try {
            myColor = Color.decode(hexaColor);
        } catch (NumberFormatException e) {
            System.err.println("Label::setColor::The provided hexadecimal color code is not valid.");
        }
        if (myColor != null) {
            this.setForeground(myColor);
        }
    }

    /**
     * Sets the background color of the Label.
     *
     * @param hexaColor The background color in hexadecimal format.
     */
    public void setBackgroundColor(String hexaColor) {
        Color myBackgroundColor = null;
        try {
            myBackgroundColor = Color.decode(hexaColor);
        } catch (NumberFormatException e) {
            System.err.println("Label::setBackgroundColor::The provided hexadecimal color code is not valid.");
        }
        if (myBackgroundColor != null) {
            this.setBackground(myBackgroundColor);
            this.setOpaque(true);
        }
    }

    /**
     * Sets the font properties for the Label.
     *
     * @param fontSize   The font size.
     * @param fontFamily The font family.
     * @param isBold     Set to true for bold text.
     * @param isItalic   Set to true for italic text.
     */
    public void setFont(int fontSize, String fontFamily, boolean isBold, boolean isItalic) {
        boolean isFontAvailable = false;
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String name : fontNames) {
            if (name.equals(fontFamily)) {
                isFontAvailable = true;
                break;
            }
        }
        int fontStyle = Font.PLAIN;
        if (isBold && isItalic) {
            fontStyle = Font.ITALIC + Font.BOLD;
        } else if (isBold) {
            fontStyle = Font.BOLD;
        } else if (isItalic) {
            fontStyle = Font.ITALIC;
        }
        if (isFontAvailable) {
            this.setFont(new Font(fontFamily, fontStyle, fontSize));
        }else{
            this.setFont(new Font("Arial", fontStyle, fontSize));
        }
    }

    /**
     * Gets the background color of the Label.
     *
     * @return The background color in hexadecimal format.
     */

}
