package Search;

import UI.Button;

public class DomineeringSearch {
    private Button buttons[][]=null;
    private static DomineeringSearch domineeringSearchInstance=null;

    public void setButtons(Button[][] buttons) {
        this.buttons = buttons;
    }

    private DomineeringSearch(Button[][] buttons) {
        this.buttons = buttons;
    }
    public static DomineeringSearch getInstance(Button buttons[][]){
        if(domineeringSearchInstance==null){
            domineeringSearchInstance=new DomineeringSearch(buttons);
        }
        return domineeringSearchInstance;
    }
}
