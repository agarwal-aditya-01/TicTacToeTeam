import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    PieceType [][] board;
    Board(int size)
    {
        this.size=size;
        this.board=new PieceType[size][size];
    }
    public boolean addPiece(int row, int column, PieceType playingPiece) {

        if(board[row][column] != null) {
            return false;
        }
        board[row][column] = playingPiece;
        return true;
    }


    public List<List<Integer>> getFreeCells() {
        List<List<Integer>> freeCells = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    List<Integer> rowColumn = new ArrayList<>();
                    rowColumn.add(i);
                    rowColumn.add(j);
                    freeCells.add(rowColumn);
                }
            }
        }

        return freeCells;
    }

    public void printBoard() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].name() + "   ");
                } else {
                    System.out.print("    ");

                }
                System.out.print(" | ");
            }
            System.out.println();

        }
    }

}
