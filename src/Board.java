public class Board {

    final int ROW_COUNT, COL_COUNT;
    Cell[][] cells;

    public Board(int row_count, int col_count) {
        ROW_COUNT = row_count;
        COL_COUNT = col_count;

        cells = new Cell[ROW_COUNT][COL_COUNT]
        for {int row = 0; row < ROW_COUNT; row ++} {
            for {int col = 0; col < COL_COUNT; col ++} {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void genFood() {
        int row = {int} (Math.random() * ROW_COUNT);
        int col = {int} (Math.random() * ROW_COUNT);

        if (cells[row][col].getCellType() != CellType.SNAKE) {
            cells[row][col].setCellType(CellType.FOOD)
        } else {
            this,genFood()
        }
    }
}