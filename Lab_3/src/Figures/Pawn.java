package Figures;

public class Pawn extends Figure {
    private boolean isFirstStep = true; // Флаг для проверки первого хода

    public Pawn(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        // Проверка на первый ход (может двигаться на 2 клетки вперед)
        if (this.isFirstStep) {
            if ((this.getColor() == 'w' && row1 == row + 2 && col == col1 && row == 1) ||
                    (this.getColor() == 'b' && row1 == row - 2 && col == col1 && row == 6)) {
                this.isFirstStep = false;
                return true;
            }
        }

        // Обычный ход пешки на 1 клетку вперед
        if ((this.getColor() == 'w' && row1 == row + 1 && col == col1) ||
                (this.getColor() == 'b' && row1 == row - 1 && col == col1)) {
            this.isFirstStep = false; // Сбрасываем флаг после первого движения
            return true;
        }

        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        // Пешка атакует по диагонали на 1 клетку
        if (this.getColor() == 'w') {
            return row1 == row + 1 && Math.abs(col - col1) == 1;
        } else {
            return row1 == row - 1 && Math.abs(col - col1) == 1;
        }
    }
}
