package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) throws IOException {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeDimensions = (int[]) fromClient.readObject();
            if (mazeDimensions.length != 2) {
                throw new IOException("Maze dimensions are not valid");
            }
            AMazeGenerator mazeGenerator = Configurations.getMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeDimensions[0], mazeDimensions[1]);
            toClient.writeObject(maze.toByteArray());
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
