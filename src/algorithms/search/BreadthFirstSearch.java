package algorithms.search;

/**
 * This class is the breadth first search class.
 * It extends the ASearchingAlgorithm class.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    @Override
    public AState search(ISearchable searchable) throws Exception {
        if (searchable == null) throw new Exception("ISearchable does not exist");
        AState goal = searchable.getGoal_s();
        initialize(searchable);
        AState curr = searchable.getStart_s();
        unvisited.add(curr);
        searchable.visit(curr);
        while (!unvisited.isEmpty()) {
            AState state = popUnvisited();
            AState.setMaxDepth(AState.getMaxDepth() + 1);
            if (state.isSameState(goal)) return state;
            for (AState s :
                    searchable.getNeighbors(state)) {
                if (!searchable.isVisited_s(s)) {
                    s.setPredecessor(state);
                    setCost(s);
                    unvisited.add(s);
                    searchable.visit(s);
                }
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numChecked;
    }

    protected void setCost(AState state) {
        state.setCost(AState.getMaxDepth() + 10);
    }


}
