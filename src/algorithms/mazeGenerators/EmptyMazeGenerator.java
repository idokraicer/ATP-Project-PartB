package algorithms.mazeGenerators;

import java.util.Arrays;

/**
 * This class is the empty maze generator where all the cells are free.
 * It extends the AMazeGenerator class.
 */
public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        Maze emptyMaze = new Maze(rows, columns);
        emptyMaze.setStartPosition(0,0);
        emptyMaze.setGoalPosition(rows-1,columns-1);
        for (int i = 0; i < rows; i++) {
            Arrays.fill(emptyMaze.getMaze()[i], 0);
        }
        return emptyMaze;
    }
}
