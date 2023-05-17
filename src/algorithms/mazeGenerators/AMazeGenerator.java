package algorithms.mazeGenerators;
/**
 * This class is the abstract class for all maze generators.
 * It implements the IMazeGenerator interface.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    public Maze maze;

    public abstract Maze generate(int rows, int columns);

    public long measureAlgorithmTimeMillis(int rows, int columns)
    {
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        return System.currentTimeMillis() - startTime;
    }
}
