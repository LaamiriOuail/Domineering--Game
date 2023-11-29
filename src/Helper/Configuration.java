package Helper;

import UI.Button;

public abstract class Configuration {
    public static short row=7;
    public static short column=7;
    public static String player1Color="#800080";
    public static String player2Color="#000000";
    public static String intermediateColor="#F5F5DC";
    public static String defaultColor="#ffffff";
    public static String mainBgColor="#00008b";
    public static String fileColors="./Data/colors.txt";
    public static  String fileSauvgarde="./Data/sauvgarde.txt";
    public static  String fileConfiguration="./Data/configurations.txt";
    public static String machineColor="#000000";
    public static Button[][] buttons=null;
    public static byte player=1;
    public static byte machine=0;

    public static void setRow(short row) {
        Configuration.row = row;
    }

    public static void setColumn(short column) {
        Configuration.column = column;
    }

    public static void setPlayer1Color(String player1Color) {
        Configuration.player1Color = player1Color;
    }

    public static void setPlayer2Color(String player2Color) {
        Configuration.player2Color = player2Color;
    }

    public static void setIntermediateColor(String intermediateColor) {
        Configuration.intermediateColor = intermediateColor;
    }

    public static void setDefaultColor(String defaultColor) {
        Configuration.defaultColor = defaultColor;
    }

    public static void setMainBgColor(String mainBgColor) {
        Configuration.mainBgColor = mainBgColor;
    }

    public static void setFileColors(String fileColors) {
        Configuration.fileColors = fileColors;
    }

    public static void setFileSauvgarde(String fileSauvgarde) {
        Configuration.fileSauvgarde = fileSauvgarde;
    }

    public static void setFileConfiguration(String fileConfiguration) {
        Configuration.fileConfiguration = fileConfiguration;
    }

    public static void setMachineColor(String machineColor) {
        Configuration.machineColor = machineColor;
    }

    public static void setButtons(Button[][] buttons) {
        Configuration.buttons = buttons;
    }

    public static void setPlayer(byte player) {
        Configuration.player = player;
    }

    public static void setMachine(byte machine) {
        Configuration.machine = machine;
    }
}
