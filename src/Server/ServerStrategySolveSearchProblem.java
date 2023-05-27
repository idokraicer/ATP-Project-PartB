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

    /**
     * checks if we already write a solution for this maze in a file
     * @param maze - Maze
     * @return true/false
     */
    private synchronized boolean isSolutionExist(Maze maze) {
        File solutionFile = new File(tempDirectoryPath + "/" + maze.hashCode() + ".sol");
        return solutionFile.exists();
    }

    /**
     * return Solution for the maze, if exist and if not find a new one and write it to a file
     * @param maze maze
     * @param exist true/false
     * @param toSearchIn searchable maze
     * @return Solution
     * @throws IOException e
     * @throws ClassNotFoundException e
     */
    private synchronized Solution handleSolution(Maze maze, boolean exist, SearchableMaze toSearchIn) throws Exception {
        Solution solution;
        if (exist) {
            System.out.println("Solution exists!");
            try (ObjectInputStream inFromFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "/" + maze.hashCode() + ".sol"))) {
                solution = (Solution) inFromFile.readObject();
            }
            catch (Exception e){
                throw new Exception("Solution file not found");
            }
        } else {
            try (ObjectOutputStream outToFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "/" + maze.hashCode() + ".sol"))) {
                ISearchingAlgorithm searchingAlg = Configurations.getInstance().searchingAlgorithmConfiguration();
                solution = searchingAlg.solve(toSearchIn);
                outToFile.writeObject(solution);
                outToFile.flush();
            }
            catch (Exception e){
                throw new Exception("Solution file not found");
            }
        }
        return solution;
    }
}
