package Server;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private final IServerStrategy strategy;
    private final int port;
    private final int listeningIntervalMS;
    private volatile boolean stop; // volatile - for thread safety
    private final ExecutorService threadPool; // Thread pool

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) throws FileNotFoundException{
        Configurations conf = Configurations.getInstance();
        this.strategy = strategy;
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.threadPool = Executors.newFixedThreadPool(conf.getThreadPoolSize() /* reading from config */);

    }

    public void start() {
        new Thread(() -> {
            runServer();
        }).start();

    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    threadPool.submit(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                    // do nothing - catch with LOG later
                }

            }
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }

}
