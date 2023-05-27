package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is the solution class.
 * It contains the solution path.
 */
public class Solution implements Serializable {
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
