package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * This class is the searchable maze class.
 * It contains the maze and the grid.
 * It implements the ISearchable interface.
 */
public class SearchableMaze implements ISearchable {
    private int[][] grid;
    private Maze maze;

    public SearchableMaze(Maze maze) throws Exception {
        if (maze == null) throw new Exception("Maze does not exist");
        grid = new int[maze.getRows()][maze.getColumns()];
        this.maze = maze;
        this.init();
    }

    @Override
    public AState getStart_s() {
        return new MazeState(new Position(maze.getStartPosition().getRowIndex(),
                maze.getStartPosition().getColumnIndex()), -1);
    }

    @Override
    public AState getGoal_s() {
        return new MazeState(new Position(maze.getGoalPosition().getRowIndex(),
                maze.getGoalPosition().getColumnIndex()), 0);
    }


    public boolean isValidPosition(int row, int col) {
        return maze.isValidPosition(row, col);
    }

    @Override
    public boolean isVisited_s(AState state) {
        return this.grid[state.getCords().getRowIndex()][state.getCords().getColumnIndex()] == 1;
    }

    public void visit(AState state){
        this.grid[state.getCords().getRowIndex()][state.getCords().getColumnIndex()] = 1;
    }

    @Override
    public ArrayList<AState> getNeighbors(AState aState) {
        int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {1, -1}};
        ArrayList<AState> neighbors = new ArrayList<AState>();
        // Check positions clockwise starting from top right
        for (int i = 0; i < directions.length; i++) {
            int newRow = aState.cords.getRowIndex() + directions[i][0];
            int newCol = aState.cords.getColumnIndex() + directions[i][1];
            if (maze.isValidPosition(newRow, newCol)) {
                neighbors.add(new MazeState(new Position(newRow, newCol), i+1));
            }
        }
        return neighbors;
    }

    @Override
    public void init() {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                grid[i][j] = 0;
            }
        }
    }
}
