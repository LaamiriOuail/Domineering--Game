package Helper;

import UI.Button;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StringTableFile {
    private String[][] backgroundColors; // Assuming you have a 2D array for background colors
    // ... other methods and constructor
    public StringTableFile(Button[][] buttons){
       this.setBackgroundColors(buttons);
    }
    public void setBackgroundColors(Button[][] buttons){
        backgroundColors=new String[buttons.length][buttons[0].length];
        for (byte i = 0; i < backgroundColors.length; i++) {
            for (byte j = 0; j < backgroundColors[0].length; j++) {
                backgroundColors[i][j]=buttons[i][j].getBackgroundColor();
            }
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
    public byte getMoveFromFile(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read the move
            line = reader.readLine();
            byte move = Byte.parseByte(line.trim());
            return move;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Position> loadMovePositionsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read the move
            reader.readLine();

            // Read the list of valid positions
            List<Position> listOfValidPosition = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.split(",");
                int x = Integer.parseInt(coordinates[0].trim());
                int y = Integer.parseInt(coordinates[1].trim());
                listOfValidPosition.add(new Position(x, y));
            }

            return listOfValidPosition;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void saveMoveAndPositionsTOFile(String fileName,byte move,List<Position> listOfValidPosition){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.print(move);
            writer.println(); // Move to the next line for the next row
            for (int i = 0; i < listOfValidPosition.size(); i++) {
                writer.print(listOfValidPosition.get(i).getX()+","+listOfValidPosition.get(i).getY());
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