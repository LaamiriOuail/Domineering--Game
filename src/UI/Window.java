/**
 * Hero is the main entity we'll be using to make a beautiful user interface
 *
 * Please see the {@link com.baeldung.javadoc.Person} class for true identity
 * @author Ouail Laamiri
 *
 */
package UI;

import javax.swing.*;
import java.io.File;
/**
 * A custom JFrame for creating a window with additional features, such as setting the title, icon, size, and layout.
 * @author Laamiri Ouail & Sadik Hajar
 * @version 1.0
 * @since 2023-12-01
 */
public class Window extends JFrame {
    private static Window instance;

    /**
     * Private constructor to create a Window with specified properties.
     *
     * @param width  The width of the window.
     * @param height The height of the window.
     * @param title  The title of the window.
     * @param icon   The path to the icon image file for the window.
     * @param fixed  Set to true to make the window non-resizable, false to allow resizing.
     */
    private Window(int width, int height, String title, String icon, boolean fixed) {
        this.setTitle(title);
        // Set Icon
        File iconFile = new File(icon);
        if (iconFile.exists()) {
            //extension images
            ImageIcon iconImage = new ImageIcon(icon);
            this.setIconImage(iconImage.getImage());
        }
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(!fixed);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void setAttribute(int width, int height, String title, String icon, boolean fixed) {
        this.setVisible(false);
        this.setTitle(title);
        // Set Icon
        File iconFile = new File(icon);
        if (iconFile.exists()) {
            //extension images
            ImageIcon iconImage = new ImageIcon(icon);
            this.setIconImage(iconImage.getImage());
        }
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(!fixed);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /**
     * Get or create the singleton instance of the Window.
     *
     * @param width  The width of the window.
     * @param height The height of the window.
     * @param title  The title of the window.
     * @param icon   The path to the icon image file for the window.
     * @param fixed  Set to true to make the window non-resizable, false to allow resizing.
     * @return The Window instance.
     */
    public static Window getInstance(int width, int height, String title, String icon, boolean fixed) {
        if (instance == null) {
            instance = new Window(width, height, title, icon, fixed);
        }else{
            instance.setAttribute(width, height, title, icon, fixed);
        }
        return instance;
    }

    /**
     * Adds a child Frame to the Window with specified properties.
     *
     * @param left         The left position of the child Frame.
     * @param top          The top position of the child Frame.
     * @param width        The width of the child Frame.
     * @param height       The height of the child Frame.
     * @param backgroundColor The background color of the child Frame in hexadecimal format.
     * @return The created child Frame component.
     */
    public Frame addFrame(int left, int top, int width, int height, String backgroundColor) {
        Frame frame = new Frame(left, top, width, height, backgroundColor);
        this.add(frame);
        return frame;
    }

    /**
     * Shows the Window, making it visible on the screen.
     */
    public void Show() {
        this.setVisible(true);
    }

    /**
     * Closes the Window, making it invisible on the screen.
     */
    public void Close() {
        this.dispose();
        System.exit(0);
    }
}
