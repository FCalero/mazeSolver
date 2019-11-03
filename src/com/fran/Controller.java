package com.fran;

import com.fran.algorithms.*;
import com.fran.graph.*;
import com.fran.utils.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Controller {

    private Scanner scanner;
    private File f;
    private Algorithm algorithm;
    private Graph graph;
    private int opt;
    String name;

    public Controller(){
        scanner = new Scanner(System.in);
    }

    public void menu(){


        BufferedImage img = null;

        while (img == null){
            try {
                System.out.println("Enter .PNG file name (inside maze directory, without extension): ");
                name = scanner.nextLine();
                f = new File("mazes/" + name + ".png");
                img = ImageIO.read(f);
                img = ImageUtils.convertToARGB(img);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        opt = -1;

        while (opt < 0 ){
            try {
                System.out.println("Choose an algorithm");
                System.out.println("1- DFS");
                System.out.println("2- BFS");
                System.out.println("3- DIJKSTRA");
                System.out.println("4- ASTAR");
                System.out.println("5- ALL ALGORITHMS");
                System.out.println("0- Exit");

                opt = scanner.nextInt();

                if(opt > 5) opt = -1;
                else if (opt == 5){
                    graph = new Graph(img);
                    for (int i = 1; i < 5; ++i)
                    {
                        executeAlgorithm(i);
                    }
                }
                else if(opt > 0) {
                    graph = new Graph(img);
                    executeAlgorithm(opt);
                }
            }
            catch (InputMismatchException e){
                opt = -1;
                scanner.next();
            }
        }


    }

    private String getAlgorithm(int i){
        switch (i){
            case 1: return "DFS";
            case 2: return "BFS";
            case 3: return "DIJKSTRA";
            case 4: return "ASTAR";
            default: return null;
        }
    }

    private void executeAlgorithm(int i) {
        try {
            System.out.println("------------------------------ SOLVING " + name + " " + getAlgorithm(i) + " ------------------------------");
            algorithm = AlgorithmFactory.getAlgorithm(i);

            BufferedImage sol = algorithm.solve(graph);
            System.out.println("Saving image...");
            long init = System.currentTimeMillis();
            File directory = new File("mazes/" + name + "sol/");
            if (! directory.exists()){
                directory.mkdir();
            }
            File outputfile = new File("mazes/" + name + "sol/" + name + getAlgorithm(i) + "solution.png");
            ImageIO.write(sol, "png", outputfile);
            System.out.println("Time elapsed: " + (System.currentTimeMillis() - init + "ms"));
        }
        catch (IOException e){
                System.out.println(e.getMessage());
        }
    }


}
