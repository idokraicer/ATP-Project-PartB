package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * This class is the abstract class for all the states.
 * It contains the position of the state, the predecessor of the state, the cost of the state and the max depth of the state.
 */
public abstract class AState {

    protected Position cords;
    protected AState predecessor;
    protected int cost = 0;
    private static int maxDepth = 0;
    private int direction;

    protected abstract boolean isSameState(AState other);

    public AState(Position p, int direction) {
        this.cords = p;
        this.direction = direction;
    }

    public AState getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(AState predecessor) {
        this.predecessor = predecessor;
    }

    public Position getCords() {
        return cords;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setCords(Position cords) {
        this.cords = cords;
    }

    public static int getMaxDepth() {
        return maxDepth;
    }


    public static void setMaxDepth(int maxDepth) {
        AState.maxDepth = maxDepth;
    }

    public abstract String toString();

    public int getDirection() {
//        String dir = null;
//        switch (direction) {
//            case -1:
//                dir = "Goal";
//                break;
//            case 0:
//                dir = "Start";
//                break;
//            case 1:
//                dir = "Up";
//                break;
//            case 2:
//                dir = "UpRight";
//                break;
//            case 3:
//                dir = "Right";
//                break;
//            case 4:
//                dir = "DownRight";
//                break;
//            case 5:
//                dir = "Down";
//                break;
//            case 6:
//                dir = "DownLeft";
//                break;
//            case 7:
//                dir = "Left";
//                break;
//            case 8:
//                dir = "UpLeft";
//                break;
//        }
//        return dir;
        return direction;
    }
}
