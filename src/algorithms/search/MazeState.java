package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * This class is the maze state class.
 * It contains the position of the state, the predecessor of the state, the cost of the state and the max depth of the state.
 */
public class MazeState extends AState {
    @Override
    protected boolean isSameState(AState other) {
        return this.cords.compare(other.cords);
    }

    public MazeState(Position pos, int direction) {
        super(pos, direction);
    }

    @Override
    public String toString() {
        return this.cords.toString();
    }


}
