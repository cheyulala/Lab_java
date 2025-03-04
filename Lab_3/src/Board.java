import Figures.Figure;
import Figures.Pawn;
import Figures.Knight;
import Figures.Rook;
import Figures.Bishop;
import Figures.Queen;
import Figures.King;

public class Board {

    private char currentColor;
    private Figure[][] fields = new Figure[8][8];

    public void setCurrentColor(char currentColor) {
        this.currentColor = currentColor;
    }

    public char getCurrentColor() {
        return currentColor;
    }

    public void init() {
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w'), new Pawn("P", 'w')
        };

        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b'), new Pawn("P", 'b')
        };

        fields[0][0] = new Rook("R", 'w');
        fields[0][7] = new Rook("R", 'w');
        fields[0][1] = new Knight("N", 'w');
        fields[0][6] = new Knight("N", 'w');
        fields[0][2] = new Bishop("B", 'w');
        fields[0][5] = new Bishop("B", 'w');
        fields[0][3] = new Queen("Q", 'w');
        fields[0][4] = new King("K", 'w');

        fields[7][0] = new Rook("R", 'b');
        fields[7][7] = new Rook("R", 'b');
        fields[7][1] = new Knight("N", 'b');
        fields[7][6] = new Knight("N", 'b');
        fields[7][2] = new Bishop("B", 'b');
        fields[7][5] = new Bishop("B", 'b');
        fields[7][3] = new Queen("Q", 'b');
        fields[7][4] = new King("K", 'b');
    }

    public String getCell(int row, int col) {
        Figure figure = this.fields[row][col];
        if (figure == null) {
            return "    ";
        }
        return " " + figure.getColor() + figure.getName() + " ";
    }

    public void print_board() {
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1; row--) {
            System.out.print(row);
            for (int col = 0; col < 8; col++) {
                System.out.print("|" + getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for (int col = 0; col < 8; col++) {
            System.out.print("    " + col);
        }
        System.out.println();
    }

    public boolean move_figure(int row, int col, int row1, int col1) {
        Figure figure = this.fields[row][col];

        if (figure != null && figure.getColor() == this.currentColor) {
            if (figure.canMove(row, col, row1, col1) && this.fields[row1][col1] == null) {
                this.fields[row1][col1] = figure;
                this.fields[row][col] = null;
                return true;
            }
            else if (figure.canAttack(row, col, row1, col1) && this.fields[row1][col1] != null
                    && this.fields[row1][col1].getColor() != figure.getColor()) {
                this.fields[row1][col1] = figure;
                this.fields[row][col] = null;
                return true;
            }
        }
        return false;
    }

    public boolean isPathClear(int row, int col, int row1, int col1) {
        if (row == row1) {
            int step = (col1 > col) ? 1 : -1;
            for (int c = col + step; c != col1; c += step) {
                if (fields[row][c] != null) return false;
            }
        } else if (col == col1) {
            int step = (row1 > row) ? 1 : -1;
            for (int r = row + step; r != row1; r += step) {
                if (fields[r][col] != null) return false;
            }
        } else if (Math.abs(row1 - row) == Math.abs(col1 - col)) {
            int rowStep = (row1 > row) ? 1 : -1;
            int colStep = (col1 > col) ? 1 : -1;
            int r = row + rowStep, c = col + colStep;
            while (r != row1 && c != col1) {
                if (fields[r][c] != null) return false;
                r += rowStep;
                c += colStep;
            }
        }
        return true;
    }
}
