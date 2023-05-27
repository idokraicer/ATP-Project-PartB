package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.ISearchingAlgorithm;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    public static final String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze toSearchIn = new SearchableMaze(maze);
            Solution newSolution = handleSolution(maze, isSolutionExist(maze), toSearchIn);
            toClient.writeObject(newSolution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized boolean isSolutionExist(Maze maze) {
        File solutionFile = new File(tempDirectoryPath + "/" + maze.hashCode() + ".sol");
        return solutionFile.exists();
    }

    private synchronized Solution handleSolution(Maze maze, boolean exist, SearchableMaze toSearchIn) throws Exception {
        Solution solution;
        if (exist) {
            System.out.println("Solution exists!");
            try (ObjectInputStream inFromFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "/" + maze.hashCode() + ".sol"))) {
                solution = (Solution) inFromFile.readObject();
            }
        } else {
            try (ObjectOutputStream outToFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "/" + maze.hashCode() + ".sol"))) {
                ISearchingAlgorithm searchingAlg = Configurations.getInstance().searchingAlgorithmConfiguration();
                solution = searchingAlg.solve(toSearchIn);
                outToFile.writeObject(solution);
                outToFile.flush();
            }
        }
        return solution;
    }
}
