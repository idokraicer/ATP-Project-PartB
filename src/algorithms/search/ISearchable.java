package algorithms.search;

import java.util.ArrayList;

/**
 * This interface is the searchable interface.
 * It contains the methods that are needed to be implemented in order to be searchable.
 */
public interface ISearchable  {
    AState getStart_s();
    AState getGoal_s();
    void visit(AState state);
    boolean isVisited_s(AState state);
    ArrayList<AState> getNeighbors(AState aState);
    void init();
}
