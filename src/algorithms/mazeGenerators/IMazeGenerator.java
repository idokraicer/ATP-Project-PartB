package algorithms.mazeGenerators;

/**
 * This interface is the maze generator interface.
 * It contains the generate and measureAlgorithmTimeMillis methods.
 */
public interface IMazeGenerator {
    Maze generate(int rows, int columns);

    long measureAlgorithmTimeMillis(int rows, int columns);


}
