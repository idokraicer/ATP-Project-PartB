package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * This class is the simple maze generator using DFS.
 * It extends the AMazeGenerator class.
 */
public class MyMazeGenerator extends AMazeGenerator {

    public MyMazeGenerator() {
        maze = null;
    }

    @Override
    public Maze generate(int rows, int columns) {
        maze = new Maze(rows, columns);
        if (rows < 2 || columns < 2) {
            throw new IllegalArgumentException("Maze dimensions must be at least 2x2");
        }

        // Set all cells as walls
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze.setWall(i, j);
            }
        }

        // Choose a random starting cell and carve a path
        int startRow = (int) (Math.random() * (rows - 2)) + 1;
        int startCol = (int) (Math.random() * (columns - 2)) + 1;
        maze.setStartPosition(new Position(0, startCol));
        iterativeDFS(0, startCol);

        // Choose a random goal position and ensure there is a safe path to it
        long startTime = System.currentTimeMillis();
        int goalRow, goalCol;
        do {
            if (System.currentTimeMillis() - startTime > 10000) {
                return generate(rows, columns);
            }
            goalRow = (int) (Math.random() * (rows - 2)) + 1;
            goalCol = (int) (Math.random() * (columns - 2)) + 1;
        } while (!isPathBetween(startRow, startCol, goalRow, goalCol));

        maze.setGoalPosition(new Position(goalRow, goalCol));

        return maze;
    }

    private boolean isPathBetween(int startRow, int startCol, int goalRow, int goalCol) { //Using DFS
        boolean[][] visited = new boolean[maze.getRows()][maze.getColumns()];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int currRow = curr[0];
            int currCol = curr[1];

            if (currRow == goalRow && currCol == goalCol) {
                return true; // Found a path to the goal
            }

            if (currRow > 0 && !visited[currRow - 1][currCol] && !maze.isWall(currRow - 1, currCol)) {
                stack.push(new int[]{currRow - 1, currCol});
                visited[currRow - 1][currCol] = true;
            }

            if (currRow < maze.getRows() - 1 && !visited[currRow + 1][currCol] && !maze.isWall(currRow + 1, currCol)) {
                stack.push(new int[]{currRow + 1, currCol});
                visited[currRow + 1][currCol] = true;
            }

            if (currCol > 0 && !visited[currRow][currCol - 1] && !maze.isWall(currRow, currCol - 1)) {
                stack.push(new int[]{currRow, currCol - 1});
                visited[currRow][currCol - 1] = true;
            }

            if (currCol < maze.getColumns() - 1 && !visited[currRow][currCol + 1] && !maze.isWall(currRow, currCol + 1)) {
                stack.push(new int[]{currRow, currCol + 1});
                visited[currRow][currCol + 1] = true;
            }
        }

        return false; // No path to the goal
    }


    private void iterativeDFS(int startRow, int startCol) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startRow, startCol});
        maze.setFree(startRow, startCol); // mark start cell as free

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int currRow = curr[0];
            int currCol = curr[1];

            // Create a random list of neighbors to visit
            List<int[]> neighbors = new ArrayList<>();
            if (currRow > 1 && maze.isWall(currRow - 2, currCol)) {
                neighbors.add(new int[]{currRow - 2, currCol});
            }
            if (currRow < maze.getRows() - 2 && maze.isWall(currRow + 2, currCol)) {
                neighbors.add(new int[]{currRow + 2, currCol});
            }
            if (currCol > 1 && maze.isWall(currRow, currCol - 2)) {
                neighbors.add(new int[]{currRow, currCol - 2});
            }
            if (currCol < maze.getColumns() - 2 && maze.isWall(currRow, currCol + 2)) {
                neighbors.add(new int[]{currRow, currCol + 2});
            }
            Collections.shuffle(neighbors);

            for (int[] neighbor : neighbors) {
                int nRow = neighbor[0];
                int nCol = neighbor[1];
                int midRow = (currRow + nRow) / 2;
                int midCol = (currCol + nCol) / 2;
                if (maze.isWall(nRow, nCol)) { // if neighbor is a wall cell
                    maze.setFree(midRow, midCol); // carve a path to it
                    maze.setFree(nRow, nCol); // mark it as free
                    stack.push(new int[]{nRow, nCol}); // add it to the stack
                }
            }
        }
    }
}
