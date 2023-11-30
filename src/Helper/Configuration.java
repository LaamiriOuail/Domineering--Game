package Helper;

import Search.DomineeringSearch;
import UI.Button;
import UI.Label;

public abstract class Configuration {
    public static short helpPlayer1=3;
    public static short helpPlayer2=3;
    public static Button helpBtn=null;
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
    public static byte move=0;
    public static Label labelMessage=null;
    public static int asymPercentFloor = 62;
    public static int asymPercentCeil = 63;
    public static int asymMove;
    public static int safeMovesCoef = 10;
    public static Position intermediatePosition=null;
    public static Move helpMove=null;

    public static void setMove(byte move) {
        Configuration.move = move;
    }
    public static DomineeringSearch domineeringSearch=DomineeringSearch.getInstance();
    public static StringTableFile file=StringTableFile.getInstance();

    public static void helpPlayer(){
        testHelpButton();
        if(Configuration.player==1){
            Configuration.helpPlayer1--;
            int[] best=Configuration.domineeringSearch.minmax((new Board()).getBoard(),4,true,new int[]{0},new int[]{10000000});
            Configuration.domineeringSearch.playHelpMove(best[1],best[2]);
        }else if(Configuration.player==2){
            Configuration.helpPlayer2--;
            int[] best=Configuration.domineeringSearch.minmax((new Board()).getBoard(),4,true,new int[]{0},new int[]{10000000});
            Configuration.domineeringSearch.playHelpMove(best[1],best[2]);
        }
        //Configuration.testHelpButton();
        Configuration.helpBtn.toEnabled(false);
    }
    public static void initializeHelpPlayerParameters(){
        Configuration.helpPlayer1=3;
        Configuration.helpPlayer2=3;
        Configuration.player=1;
    }
    public static void testHelpButton(){
        if(Configuration.move==0){
            if(Configuration.player==1){
                if(Configuration.helpPlayer1>0&&Configuration.helpPlayer1<=3){
                    Configuration.helpBtn.toEnabled(true);
                }else{
                    Configuration.helpBtn.toEnabled(false);
                }
            }else if(Configuration.player==2){
                if(Configuration.helpPlayer2>0&&Configuration.helpPlayer2<=3){
                    Configuration.helpBtn.toEnabled(true);
                }else{
                    Configuration.helpBtn.toEnabled(false);
                }
            }
        }
    }
}

