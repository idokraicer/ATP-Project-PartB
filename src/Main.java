import algorithms.mazeGenerators.MyMazeGenerator;

public class Main {
    public static void main(String[] args) {

        MyMazeGenerator myMazeGenerator = new MyMazeGenerator();
        myMazeGenerator.generate(100,100);
        myMazeGenerator.maze.print();

        System.out.println(myMazeGenerator.measureAlgorithmTimeMillis(1000,1000)+" seconds");
    }
}