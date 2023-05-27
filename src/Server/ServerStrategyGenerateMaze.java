package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    /**
     * @param inFromClient  - input stream from client
     * @param outToClient   - output stream to client
     * @throws IOException  - if there is a problem with the input or output streams
     * @throws ClassNotFoundException - if the class of a serialized object cannot be found
     */
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] mazeDimensions = (int[]) fromClient.readObject();

            Configurations configurations = Configurations.getInstance();
            IMazeGenerator mazeGenerator = configurations.generateMazeAlgorithmConfig();
            Maze newMaze = mazeGenerator.generate(mazeDimensions[0], mazeDimensions[1]);

            ByteArrayOutputStream outClient = new ByteArrayOutputStream();
            MyCompressorOutputStream compressedOutput = new MyCompressorOutputStream(outClient);
            compressedOutput.write(newMaze.toByteArray());

            toClient.writeObject(outClient.toByteArray());
            toClient.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
