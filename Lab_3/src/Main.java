import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.init();
        board.setCurrentColor('w');

        System.out.println();

        boolean isGame = true;
        Scanner in = new Scanner(System.in);


        while (isGame){
            board.print_board();
            System.out.println();

            System.out.println("Управление:");
            System.out.println("----row ol row1 col1: Ход фигуры из клетки (row, col) в (row1, col1)");

            switch (board.getCurrentColor()){
                case 'w': System.out.println("Ход белых"); break;
                case 'b': System.out.println("Ход черных"); break;
            }

            System.out.print("Введите ход: ");

            String inputLine = in.nextLine();
            int row, col, row1, col1;
            String [] coords = inputLine.split(" ");
            row = Integer.parseInt(coords[0]);
            col = Integer.parseInt(coords[1]);
            row1 = Integer.parseInt(coords[2]);
            col1 = Integer.parseInt(coords[3]);

            while (!board.move_figure(row, col, row1, col1)){
                System.out.println("Ошибка! Повторите ход фигуры!");
                System.out.print("Введите ход: ");
                inputLine = in.nextLine();
                coords = inputLine.split(" ");
                row = Integer.parseInt(coords[0]);
                col = Integer.parseInt(coords[1]);
                row1 = Integer.parseInt(coords[2]);
                col1 = Integer.parseInt(coords[3]);
            }

            switch (board.getCurrentColor()){
                case 'w': board.setCurrentColor('b');break;
                case 'b': board.setCurrentColor('w');break;
            }
        }

    }
}
