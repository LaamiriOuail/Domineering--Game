package Game;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private int X;
    private int Y;
    public Position(int x,int y){
        this.X=x;
        this.Y=y;
    }
    public boolean isValid(){
        if(this.X>=0 && this.X<7 && this.Y>=0 && this.Y<8){
            return true;
        }
        return false;
    }
    public List<Position> getPossibleMove(int player){
        //One : left , right
        //Two : top,buttom
        List<Position> list=new ArrayList<>();
        Position pos=null;
        if(player==2){
            pos=new Position(this.X-1,Y);
            if(pos.isValid())
                list.add(pos);
            pos=new Position(this.X+1,Y);
            if(pos.isValid())
                list.add(pos);
        }else if(player==1){
            pos=new Position(this.X,Y-1);
            if(pos.isValid())
                list.add(pos);
            pos=new Position(this.X,Y+1);
            if(pos.isValid())
                list.add(pos);
        }
        return list;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
