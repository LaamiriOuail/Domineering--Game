package UI;

import javax.swing.*;
import java.util.Vector;

public class ComboBox<T> extends JComboBox<T> {
    public ComboBox(int left, int top, int width, int height,T[] items,boolean visible) {
        super(items);
        this.setVisible(visible);
        this.resize(left,top,width,height);
    }
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
}