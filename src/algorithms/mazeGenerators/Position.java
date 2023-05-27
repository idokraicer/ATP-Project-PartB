package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * This class is the position class.
 * It contains the row and column of the position.
 */
public class Position implements Serializable {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean compare(Position other){
        return this.row == other.row && this.column == other.column;
    }

    public int getRowIndex() {
        return row;
    }

    public void setRowIndex(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumnIndex(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "{" +
                row +
                "," + column +
                '}';
    }
}
