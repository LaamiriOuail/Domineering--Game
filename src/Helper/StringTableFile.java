package Helper;

import UI.Button;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringTableFile {
    private String[][] backgroundColors;
    private static  StringTableFile instance;
    private StringTableFile(Button[][] buttons){
       this.setBackgroundColors(buttons);
    }
    public static  StringTableFile getInstance(Button[][] buttons){
        if(instance==null){
            instance=new StringTableFile(buttons);
        }
        return  instance;
    }
    public void setBackgroundColors(Button[][] buttons){
        if(buttons!=null){
            backgroundColors=new String[buttons.length][buttons[0].length];
            for (byte i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    backgroundColors[i][j]=buttons[i][j].getBackgroundColor();
                }
            }
        }
    }
    public void saveVoid(String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveBackgroundColorToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < backgroundColors.length; i++) {
                for (byte j = 0; j < backgroundColors[0].length; j++) {
                    writer.print(backgroundColors[i][j]);
                    // Add a delimiter (e.g., comma) between color values
                    if (j < backgroundColors.length ) {
                        writer.print(",");
                    }
                }
                writer.println(); // Move to the next line for the next row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadBackgroundColorFromFile(String fileName,Button[][] buttons) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < buttons.length) {
                String[] colors = line.split(",");
                for (byte col = 0; col < colors.length && col < buttons[row].length; col++) {
                    backgroundColors[row][col] = colors[col];
                }
                row++;
            }
            for(byte i=0;i<buttons.length;i++){
                for(byte j=0;j<buttons.length;j++){
                    buttons[i][j].setBackgroundColor(backgroundColors[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}