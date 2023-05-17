package algorithms.mazeGenerators;

import java.util.Arrays;
import java.util.Random;

/**
 * This class is the maze class.
 * It contains the maze, the start position and the goal position.
 */
public class Maze {
    private int[][] maze;
    private Position startPosition;
    private Position goalPosition;

    public Maze(int rows, int cols) {
        maze = new int[rows][cols];
    }
     public Maze(byte[] mazeInBytes) {
        int rows = mazeInBytes[0] * 256 + Byte.toUnsignedInt(mazeInBytes[1]);
        int cols = mazeInBytes[2] * 256 + Byte.toUnsignedInt(mazeInBytes[3]);
        startPosition = new Position(mazeInBytes[4] * 256 + Byte.toUnsignedInt(mazeInBytes[5]), mazeInBytes[6] * 256 + Byte.toUnsignedInt(mazeInBytes[7]));
        goalPosition = new Position(mazeInBytes[8] * 256 + Byte.toUnsignedInt(mazeInBytes[9]), mazeInBytes[10] * 256 + Byte.toUnsignedInt(mazeInBytes[11]));
        maze = new int[rows][cols];
        int index = 12;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = mazeInBytes[index];
                index++;
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setWall(int row, int col) {
        maze[row][col] = 1;
    }

    public void setFree(int row, int col) {
        maze[row][col] = 0;
    }

    public boolean isWall(int row, int col) {
        return maze[row][col] == 1;
    }

    public int getRows() {
        return maze.length;
    }

    public int getColumns() {
        return maze[0].length;
    }

    public void print() {

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {
                    System.out.println("S ");
                } else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()) {
                    System.out.println("E ");
                } else if (maze[i][j] == 1) {
                    System.out.print("□ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public byte[] toByteArray() {
        int rows = getRows();
        int cols = getColumns();
        byte[] mazeInBytes = new byte[rows * cols + 12]; //12 bytes for the maze properties
        mazeInBytes[0] = (byte) (rows / 256); //first 2 bytes are the number of rows
        mazeInBytes[1] = (byte) (rows % 256);
        mazeInBytes[2] = (byte) (cols / 256); //next 2 bytes are the number of columns
        mazeInBytes[3] = (byte) (cols % 256);
        mazeInBytes[4] = (byte) (startPosition.getRowIndex() / 256);
        mazeInBytes[5] = (byte) (startPosition.getRowIndex() % 256);
        mazeInBytes[6] = (byte) (startPosition.getColumnIndex() / 256);
        mazeInBytes[7] = (byte) (startPosition.getColumnIndex() % 256);
        mazeInBytes[8] = (byte) (goalPosition.getRowIndex() / 256);
        mazeInBytes[9] = (byte) (goalPosition.getRowIndex() % 256);
        mazeInBytes[10] = (byte) (goalPosition.getColumnIndex() / 256);
        mazeInBytes[11] = (byte) (goalPosition.getColumnIndex() % 256);
        int index = 12;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mazeInBytes[index] = (byte) maze[i][j];
                index++;
            }
        }
        return mazeInBytes;
    }

    public Position getStartPosition() {
        return this.startPosition;
    }

    public void setStartPosition(int row, int col) {
        this.startPosition = new Position(row, col);
    }


    public void setGoalPosition(int row, int col) {
        this.goalPosition = new Position(row, col);
    }

    public Position getGoalPosition() {
        return this.goalPosition;
    }

    public boolean isValidPosition(Position p) {
        if ((p.getColumnIndex() >= getRows()
                || p.getRowIndex() >= getColumns()
                || p.getColumnIndex() < 0
                || p.getRowIndex() < 0)
                && !isWall(p.getRowIndex(), p.getColumnIndex()))
            return false;
        return true;
    }

    public boolean isValidPosition(int x, int y) {
        if (x >= getRows()
                || y >= getColumns()
                || x < 0
                || y < 0
                || isWall(x, y))
            return false;
        return true;
    }

    public void setGoalPosition(Position position) {
        this.goalPosition = position;
    }

    public void setStartPosition(Position position) {
        this.startPosition = position;
    }
}
