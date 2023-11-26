package Helper;

public class Move {
    private Position position1=null;
    private Position posotion2=null;
    public Move(Position position1,Position posotion2) {
        this.position1=position1;
        this.posotion2=posotion2;
    }

    public Position getPosition1() {
        return position1;
    }

    public Position getPosotion2() {
        return posotion2;
    }
}
