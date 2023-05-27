//package Server;
//
//
//import algorithms.mazeGenerators.AMazeGenerator;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class Configurations {
//    private static Configurations instance = null;
//    private static final Properties prop = new Properties();
//
//    private Configurations() throws FileNotFoundException {
//        InputStream input = new FileInputStream("resources/config.properties");
//        try {
//            prop.load(input);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static Configurations getInstance() throws FileNotFoundException {
//        if (instance == null) {
//            instance = new Configurations();
//        }
//        return instance;
//    }
//
//    public static AMazeGenerator getMazeGenerator() {
//        try {
//            String mazeGeneratingAlgorithm = prop.getProperty("mazeGeneratingAlgorithm");
//            if (mazeGeneratingAlgorithm.equals("MyMazeGenerator")) {
//                return new algorithms.mazeGenerators.MyMazeGenerator();
//            } else if (mazeGeneratingAlgorithm.equals("SimpleMazeGenerator")) {
//                return new algorithms.mazeGenerators.SimpleMazeGenerator();
//            } else {
//                return new algorithms.mazeGenerators.EmptyMazeGenerator();
//            }
//        } catch (Exception e) {
//            return new algorithms.mazeGenerators.EmptyMazeGenerator();
//        }
//    }
//
//    public int getThreadPoolSize() {
//        return Integer.parseInt(prop.getProperty("threadPoolSize"));
//    }
//
//    public String getGenAlgorithm() throws Exception {
//        return prop.getProperty("mazeGeneratingAlgorithm");
//    }
//
//    public String getSolverAlgorithm() throws Exception {
//        return prop.getProperty("mazeSearchingAlgorithm");
//    }
//
//    public static void setThreadPoolSize(int size) throws Exception {
//        if (size < 1) {
//            throw new Exception("Thread pool size must be greater than 0");
//        }
//        FileOutputStream out = new FileOutputStream("config.properties");
//        prop.setProperty("threadPoolSize", Integer.toString(size));
//        prop.store(new FileOutputStream("resources/config.properties"), null);
//        out.close();
//    }
//
//    public static void setGenAlgorithm(String algorithm) throws Exception {
//        if (!algorithm.equals("MyMazeGenerator") && !algorithm.equals("SimpleMazeGenerator")) {
//            throw new Exception("Maze generating algorithm must be either MyMazeGenerator or SimpleMazeGenerator");
//        }
//        FileOutputStream out = new FileOutputStream("config.properties");
//        prop.setProperty("mazeGeneratingAlgorithm", algorithm);
//        prop.store(new FileOutputStream("resources/config.properties"), null);
//        out.close();
//    }
//}

//package Server;
//
//import algorithms.mazeGenerators.EmptyMazeGenerator;
//import algorithms.mazeGenerators.IMazeGenerator;
//import algorithms.mazeGenerators.MyMazeGenerator;
//import algorithms.mazeGenerators.SimpleMazeGenerator;
//import algorithms.search.BestFirstSearch;
//import algorithms.search.BreadthFirstSearch;
//import algorithms.search.DepthFirstSearch;
//import algorithms.search.ISearchingAlgorithm;
//
//import java.io.*;
//import java.util.Properties;
//
//public class Configurations {
//    private static Configurations configInstance = null;
//    private static Properties properties;
//    int threadPoolSize;
//    public Configurations() {
//        properties = new Properties();
//        start();
//    }
//
//    public static Configurations getInstance() {
//        if(configInstance==null){
//            configInstance = new Configurations();
//        }
////        else{
////            System.out.println("Configuration initiated..");
////
////        }
//        return configInstance;
//    }
//
//
//    public synchronized Properties start() {
//        try{
//            properties.load(new FileInputStream("resources/config.properties"));
//            return properties;
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static IMazeGenerator generateMazeAlgorithmConfig(){
//        return switch (properties.getProperty("mazeGeneratingAlgorithm")) {
//            case "EmptyMazeGenerator" -> new EmptyMazeGenerator();
//            case "SimpleMazeGenerator" -> new SimpleMazeGenerator();
//            default -> new MyMazeGenerator();
//        };
//    }
//
//    public static ISearchingAlgorithm searchingAlgorithmConfiguration(){
//        return switch (properties.getProperty("mazeSearchingAlgorithm")) {
//            case "BreadthFirstSearch" -> new BreadthFirstSearch();
//            case "DepthFirstSearch" -> new DepthFirstSearch();
//            default -> new BestFirstSearch();
//        };
//    }
//
//    public int getThreadPoolSize(){
//        assert false;
//        int size = Integer.parseInt(properties.getProperty("threadPoolSize"));
//        threadPoolSize = size;
//        return size;
//    }
//}


package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is a singleton class that reads the configurations from the config.properties file
 * and returns the values of the configurations.
 */
public class Configurations {
    private static Configurations configInstance = null;
    private Properties properties;
    private int threadPoolSize;

    private Configurations() {
        properties = new Properties();
        start();
    }

    public static Configurations getInstance() {
        if (configInstance == null) {
            configInstance = new Configurations();
        }
        return configInstance;
    }

    private void start() {
        try {
            properties.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IMazeGenerator generateMazeAlgorithmConfig() {
        String mazeAlgorithm = properties.getProperty("mazeGeneratingAlgorithm");
        switch (mazeAlgorithm) {
            case "EmptyMazeGenerator":
                return new EmptyMazeGenerator();
            case "SimpleMazeGenerator":
                return new SimpleMazeGenerator();
            default:
                return new MyMazeGenerator();
        }
    }

    public ISearchingAlgorithm searchingAlgorithmConfiguration() {
        String searchAlgorithm = properties.getProperty("mazeSearchingAlgorithm");
        switch (searchAlgorithm) {
            case "BreadthFirstSearch":
                return new BreadthFirstSearch();
            case "DepthFirstSearch":
                return new DepthFirstSearch();
            default:
                return new BestFirstSearch();
        }
    }

    public int getThreadPoolSize() {
        if (threadPoolSize == 0) {
            String sizeString = properties.getProperty("threadPoolSize");
            threadPoolSize = Integer.parseInt(sizeString);
        }
        return threadPoolSize;
    }
}
