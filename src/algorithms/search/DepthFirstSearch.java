package algorithms.search;

/**
 * This class is the breadth first search class.
 * It extends the ASearchingAlgorithm class.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {


    @Override
    public AState search(ISearchable searchable) throws Exception {
        if (searchable == null) throw new Exception("ISearchable does not exist");
        initialize(searchable);
        AState curr = searchable.getStart_s();
        searchable.visit(curr);
        for (AState state :
                searchable.getNeighbors(curr)) {
                state.setPredecessor(curr);
                unvisited_stack.push(state);
                searchable.visit(state);
        }
        while(!unvisited_stack.isEmpty()){
            AState temp = popUnvisited_stack();
            if(temp.isSameState(searchable.getGoal_s())){
                return temp; // This is the solution
            }
            else {
                for (AState state :
                        searchable.getNeighbors(temp)) {
                    if(!searchable.isVisited_s(state)){
                        state.setPredecessor(temp);
                        searchable.visit(state);
                        unvisited_stack.push(state);
                    }

                }
            }
        }
        return null;
    }


    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numChecked;
    }

}
