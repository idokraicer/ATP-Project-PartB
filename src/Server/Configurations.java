package Server;


import algorithms.mazeGenerators.AMazeGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private static final Properties prop = new Properties();

    private Configurations() throws FileNotFoundException {
        InputStream input = new FileInputStream("resources/config.properties");
        try {
            prop.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Configurations getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }

    public static AMazeGenerator getMazeGenerator() {
        try {
            String mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
            if (mazeGeneratingAlgorithm.equals("MyMazeGenerator")) {
                return new algorithms.mazeGenerators.MyMazeGenerator();
            } else if (mazeGeneratingAlgorithm.equals("SimpleMazeGenerator")) {
                return new algorithms.mazeGenerators.SimpleMazeGenerator();
            } else {
                return new algorithms.mazeGenerators.EmptyMazeGenerator();
            }
        } catch (Exception e) {
            return new algorithms.mazeGenerators.EmptyMazeGenerator();
        }
    }

    public int getThreadPoolSize() {
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }

    public String getGenAlgorithm() throws Exception {
        return prop.getProperty("mazeGeneratingAlgorithm");
    }

    public String getSolverAlgorithm() throws Exception {
        return prop.getProperty("mazeSearchingAlgorithm");
    }

    public static void setThreadPoolSize(int size) throws Exception {
        if (size < 1) {
            throw new Exception("Thread pool size must be greater than 0");
        }
        FileOutputStream out = new FileOutputStream("config.properties");
        prop.setProperty("threadPoolSize", Integer.toString(size));
        prop.store(new FileOutputStream("resources/config.properties"), null);
        out.close();
    }

    public static void setGenAlgorithm(String algorithm) throws Exception {
        if (!algorithm.equals("MyMazeGenerator") && !algorithm.equals("SimpleMazeGenerator")) {
            throw new Exception("Maze generating algorithm must be either MyMazeGenerator or SimpleMazeGenerator");
        }
        FileOutputStream out = new FileOutputStream("config.properties");
        prop.setProperty("mazeGeneratingAlgorithm", algorithm);
        prop.store(new FileOutputStream("resources/config.properties"), null);
        out.close();
    }
}
