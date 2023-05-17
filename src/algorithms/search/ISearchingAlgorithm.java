package algorithms.search;

/**
 * This interface is the searching algorithm interface.
 * It contains the methods that are needed to be implemented in order to be a searching algorithm.
 */
public interface ISearchingAlgorithm {
    AState search(ISearchable s) throws Exception;
    Solution solve(ISearchable problem) throws Exception;
    String getName();
    int getNumberOfNodesEvaluated();
    void initialize(ISearchable s);
}
