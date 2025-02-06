public class Cell {

    public enum CellType {
        EMPTY,
        FOOD,
        SNAKE
    }

    private final int row, col;
    private CellType cellType;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public getCellType() {
        return this.cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public int getRow() {
        return self.row;
    }

    public int getCol() {
        return self.col;
    }
}