package algorithms.search;

import java.util.*;

/**
 * This class is the abstract searching algorithm class.
 * It implements the ISearchingAlgorithm interface.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected PriorityQueue<AState> unvisited;
    protected Stack<AState> unvisited_stack;
    protected int numChecked;
    private Comparator<AState> comparator = new Comparator<AState>(){
        public int compare(AState state1, AState state2) {
            if (state1.getCost() < state2.getCost())
                return -1;
            else if (state1.getCost() > state2.getCost())
                return 1;
            return 0;
        }
    };


    public ASearchingAlgorithm() {
        numChecked = 0;
        unvisited = new PriorityQueue<AState>(comparator);
        unvisited_stack = new Stack<AState>();
    }

    public AState popUnvisited(){
        numChecked++;
        return unvisited.poll();
    }

    public AState popUnvisited_stack(){
        numChecked++;
        return unvisited_stack.pop();
    }

    public PriorityQueue<AState> getUnvisited() {
        return unvisited;
    }

    public Stack<AState> getUnvisited_stack() {
        return unvisited_stack;
    }

    public int getNumChecked() {
        return numChecked;
    }

    public Solution solve(ISearchable searchable) throws Exception {
        if (searchable == null) throw new Exception("ISearchable does not exist");
        AState goal = search(searchable);
        AState curr = goal;
        ArrayList<AState> path = new ArrayList<AState>();
        while(!curr.isSameState(searchable.getStart_s())){
            path.add(curr);
            curr = curr.getPredecessor();
        }
        path.add(searchable.getStart_s());
        Collections.reverse(path);
        Solution sol = new Solution(path);
        return sol;
    }

    @Override
    public void initialize(ISearchable searchable) {
        searchable.init();
    }
}
