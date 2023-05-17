package algorithms.search;

import java.util.ArrayList;

/**
 * This class is the solution class.
 * It contains the solution path.
 */
public class Solution {
    private ArrayList<AState> path;

    public Solution(ArrayList<AState> path) {
        this.path = path;
    }

    public void setPath(ArrayList<AState> path) {
        this.path = path;
    }

    public ArrayList<AState> getSolutionPath() {
        return path;
    }
}
