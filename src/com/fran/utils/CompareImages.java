package com.fran.utils;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/*Compares 2 images to see if the path calculated is the same length*/
public class CompareImages {

    private static File f = null;
    private static BufferedImage img;

    private static int length(String path){
        int r = 0;
        try {
            f = new File("C:/Users/Fran/Desktop/mazeSolver/mazes/" + path + ".png");
            img = ImageIO.read(f);

            for (int i = 0; i < img.getWidth(); ++i){
                for (int j = 0; j < img.getHeight(); ++j){
                    if (img.getRGB(i,j) != -1 && img.getRGB(i,j) != -16777216)
                        r++;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return r;
    }

    public static boolean sameLength(String mySolution, String knownSolution) {


        int mySolutionLength = length(mySolution);
        System.out.println("MySolutionLength: " + mySolutionLength);
        int knownSolutionLength = length(knownSolution);
        System.out.println("knownSolutionLength: " + knownSolutionLength);
        return mySolutionLength == knownSolutionLength;
    }
}
