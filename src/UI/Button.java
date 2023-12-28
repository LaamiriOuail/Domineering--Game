package UI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * A custom JButton with additional features for setting text, tooltip, icon, enabled state, color, background color, and font.
 * @author Laamiri Ouail & Hajar Sadik
 * @version 1.0
 * @since 2023-12-01
 */
public class Button extends JButton {
    /**
     * Background color of the button.
     */
    public String backgroundColor;

    /**
     * Constructs a Button with specified properties.
     *
     * @param left           The left position of the Button.
     * @param top            The top position of the Button.
     * @param width          The width of the Button.
     * @param height         The height of the Button.
     * @param text           The text to display on the Button.
     * @param icon           The path to the icon image file for the Button.
     * @param toolTip        The tooltip to display when hovering over the Button.
     * @param enable         Set to true to enable the Button, false to disable it.
     * @param color          The text color in hexadecimal format (e.g., "#RRGGBB").
     * @param backgroundColor The background color in hexadecimal format.
     * @param fontSize       The font size.
     * @param fontFamily     The font family.
     * @param isBold         Set to true for bold text.
     * @param isItalic       Set to true for italic text.
     */
    public Button(int left, int top, int width, int height, String text, String icon, String toolTip, boolean enable, String color, String backgroundColor, int fontSize, String fontFamily, boolean isBold, boolean isItalic) {
        this.setBorder(null);
        this.setText(text);
        this.setTooltip(toolTip);
        this.setIconButton(icon);
        this.toEnabled(enable);
        this.resize(left, top, width, height);
        this.setColor(color);
        this.setBackgroundColor(backgroundColor);
        this.setFont(fontSize, fontFamily, isBold, isItalic);
        this.backgroundColor=backgroundColor;
    }


    /**
     * Sets the tooltip for the Button if the tooltip is not empty.
     *
     * @param tooltip The tooltip to set for the Button.
     */
    public void setTooltip(String tooltip) {
        if (!"".equals(tooltip)) {
            this.setToolTipText(tooltip);
        }
    }

    /**
     * Resizes the Button to the specified dimensions.
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
     * Sets whether the Button is enabled or disabled.
     *
     * @param enable Set to true to enable the Button, false to disable it.
     */
    public void toEnabled(boolean enable) {
        this.setEnabled(enable);
    }

    /**
     * Sets the icon for the Button if the icon file exists.
     *
     * @param pathToIcon The path to the icon image file.
     */
    public void setIconButton(String pathToIcon) {
        File iconFile = new File(pathToIcon);
        if (iconFile.exists()) {
            //add extension files images
            ImageIcon iconImage = new ImageIcon(pathToIcon);
            this.setIcon(iconImage);
        }
    }

    /**
     * Sets the text color of the Button.
     *
     * @param hexaColor The text color in hexadecimal format (e.g., "#RRGGBB").
     */
    public void setColor(String hexaColor) {
        Color myColor = null;
        try {
            myColor = Color.decode(hexaColor);
            this.setForeground(myColor);
        } catch (NumberFormatException e) {
            System.err.println("Button::setColor::The provided hexadecimal color code is not valid.");
        }
    }

    /**
     * Sets the background color of the Button.
     *
     * @param hexaColor The background color in hexadecimal format.
     */
    public void setBackgroundColor(String hexaColor) {
        if (hexaColor == null) {
            // Handle the case where hexaColor is null, e.g., set a default color
            System.err.println("Button::setBackgroundColor::The provided hexadecimal color code is null.");
            return;
        }

        Color myBackgroundColor = null;
        try {
            myBackgroundColor = Color.decode(hexaColor);
            this.setBackground(myBackgroundColor);
            this.backgroundColor = hexaColor;
        } catch (NumberFormatException e) {
            System.err.println("Button::setBackgroundColor::The provided hexadecimal color code is not valid.");
        }
    }


    /**
     * Sets the font properties for the Button.
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
     * Gets the background color of the Button.
     *
     * @return The background color in hexadecimal format.
     */
    public String getBackgroundColor() {
        if (this.backgroundColor != null)
            return this.backgroundColor;
        return "";
    }

}
