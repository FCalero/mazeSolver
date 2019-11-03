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
    private BufferedImage img;
    private Algorithm algorithm;
    private Graph graph;

    public Controller(){
        scanner = new Scanner(System.in);
    }

    public void menu(){

        while (img == null){
            try {
                System.out.println("Enter file name (inside maze directory, without extension): ");
                String name = scanner.nextLine();
                f = new File("C:/Users/Fran/Desktop/mazeSolver/mazes/" + name + ".png");
                img = ImageIO.read(f);
                img = ImageUtils.convertToARGB(img);
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        int opt = -1;

        while (opt < 0 ){
            try {
                System.out.println("Choose an algorithm");
                System.out.println("1- DFS");
                System.out.println("2- BFS");
                System.out.println("3- DIJKSTRA");
                System.out.println("0- Exit");

                opt = scanner.nextInt();

                if(opt > 3) opt = -1;
                else if(opt > 0) algorithm = AlgorithmFactory.getAlgorithm(opt);
            }
            catch (InputMismatchException e){
                opt = -1;
                scanner.next();
            }
        }

        if (opt > 0)
            executeAlgoritm();

    }

    private void executeAlgoritm() {
            graph = new Graph(img);
            algorithm.solve(graph);
    }


}
