import UI.Button;
import UI.Frame;
import UI.Label;
import UI.Window;


public class Main {
    public static void main(String[] args) {
        Window window=Window.getInstance(300,300,"AI-GAME","",true);
        Frame frame1=window.addFrame(10,10,200,200,"#ff0000");//#ff0000 : red color en hexadecimal
        Label label1=frame1.addLabel(10,10,100,30,"I am a label","","Click me tooltip",true,"#ffffff","",14,"serif",true,true);
        window.Show();
    }
}