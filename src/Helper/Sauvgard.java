package Helper;

import java.util.Arrays;

public class Sauvgard {
    String[][] backgroundColors ;
    String title;
    String formattedDateTime;

    public byte getMachine() {
        return machine;
    }

    byte machine;
    public Sauvgard(String[][] backgroundColors,String title, String formattedDateTime,byte machine) {
        this.backgroundColors = new String[backgroundColors.length][backgroundColors[0].length];
        this.title = title;
        this.machine=machine;
        this.formattedDateTime = formattedDateTime;
        for(int i=0;i< backgroundColors.length;i++){
            for(int j=0;j< backgroundColors[0].length;j++) {
                this.backgroundColors[i][j] = backgroundColors[i][j];
            }
        }
    }

    public String[][] getBackgroundColors() {
        return backgroundColors;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDateTime() {
        return formattedDateTime;
    }
    @Override
    public String toString() {
        return "Sauvgard{" +
                "title='" + title + '\'' +
                ", formattedDateTime='" + formattedDateTime + '\'' +
                '}';

    }
}
