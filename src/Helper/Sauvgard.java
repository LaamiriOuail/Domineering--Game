package Helper;

public class Sauvgard {
    String[][] backgroundColors ;
    byte player;
    String title;
    String formattedDateTime;

    public Sauvgard(String[][] backgroundColors,String title, String formattedDateTime) {
        this.backgroundColors = new String[backgroundColors.length][backgroundColors[0].length];
        this.player = player;
        this.title = title;
        this.formattedDateTime = formattedDateTime;
        for(int i=0;i< backgroundColors.length;i++){
            for(int j=0;j< backgroundColors.length;j++) {
                this.backgroundColors[i][j] = backgroundColors[i][j];
            }
        }
    }

    public String[][] getBackgroundColors() {
        return backgroundColors;
    }

    public byte getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    public String getFormattedDateTime() {
        return formattedDateTime;
    }
}
