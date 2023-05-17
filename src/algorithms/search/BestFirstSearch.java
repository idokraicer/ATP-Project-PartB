package algorithms.search;

/**
 * This class is the best first search class.
 * It extends the BreadthFirstSearch class.
 */
public class BestFirstSearch extends BreadthFirstSearch {

    @Override
    protected void setCost(AState state) {
        int dir = state.getDirection();
        if (dir == 1 || dir == 3 || dir == 5 || dir == 7) {
            state.setCost(state.getPredecessor().getCost() + 10);
        } else {
            state.setCost(state.getPredecessor().getCost() + 15);

        }
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
