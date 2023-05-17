package algorithms.mazeGenerators;

import java.util.Random;

/**
 * This class is the simple maze generator where the cells are randomly set to be free or walls.
 * It extends the AMazeGenerator class.
 */
public class SimpleMazeGenerator extends AMazeGenerator{

    @Override
    public Maze generate(int rows, int columns) {
        maze = new Maze(rows, columns);
        maze.setStartPosition(0,0);
        maze.setGoalPosition(rows-1,columns-1);
        if (rows < 2 || columns < 2) {
            throw new IllegalArgumentException("Maze dimensions must be at least 2x2");
        }
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (random.nextInt(0, 2) == 0) {
                    maze.setFree(i, j);
                } else {
                    maze.setWall(i, j);
                }
            }
        }
        return maze;
    }
}
