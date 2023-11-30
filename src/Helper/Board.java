package Helper;

public class Board {
    public String[][] board=null;

    public Board(String[][] board){
        if(board.length==Configuration.row && board[0].length==Configuration.column){
            this.setBoard(board);
        }else{
            System.out.println("Board:Size problem");
        }

    }
    public Board(){
        this.setBoardFromButtons();
    }
    public void setBoard(String[][] board){
        board=new String[Configuration.row][Configuration.column];
        for(short i=0;i<board.length;i++){
            for(short j=0;j<board[0].length;j++){
                this.board[i][j]=board[i][j];
            }
        }
    }
    public void setBoardFromButtons(){
        board=new String[Configuration.row][Configuration.column];
        for(short i=0;i<board.length;i++){
            for(short j=0;j<board[0].length;j++){
                this.board[i][j]=Configuration.buttons[i][j].getBackgroundColor();
            }
        }
    }
    public static String[][] generateBoard(String[][] lastBoard,int x,int y,int player){
        String[][] board=new String[lastBoard.length][lastBoard[0].length];
        Position position;
        for(short i=0;i<board.length;i++){
            for(short j=0;j<board[0].length;j++){
                board[i][j]=lastBoard[i][j];
            }
        }
        if(player==1){
            position=new Position(x,y);
            if(position.isValid()){
                board[x][y]=Configuration.player1Color;
            }
            position=new Position(x,y+1);
            if(position.isValid()){
                board[x][y+1]=Configuration.player1Color;
            }
        }else if(player==2){
            position=new Position(x,y);
            if(position.isValid()){
                board[x][y]=Configuration.player2Color;
            }
            position=new Position(x-1,y);
            if(position.isValid()){
                board[x-1][y]=Configuration.player2Color;
            }
        }
        return board;
    }
    public String[][] getBoard() {
        return board;
    }
}
