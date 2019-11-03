package com.fran.graph;

import com.fran.utils.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Graph {

    private BufferedImage img;
    private List<Node> listOfNodes = new ArrayList<>();
    private Node source;
    private Node target;

    public Graph(BufferedImage img) {
        this.img = img;
        generateGraph();
    }

    private void generateGraph()
    {
        System.out.println("Number of rows: " + img.getHeight());
        System.out.println("Number of columns: " + img.getHeight());
        System.out.println("Generating graph...");
        long init = System.currentTimeMillis();

        Node columLast[] = new Node[img.getHeight()];

        for (int i = 0; i < img.getWidth(); ++i){
            Node lastNode =  null;
            for (int j = 0; j < img.getHeight(); ++j){

                if(img.getRGB(i,j) == -1) {

                    int sides = 0;
                    int upDown = 0;

                    for (DirectionUtils.Pair p : DirectionUtils.dirs) {
                        int ni = i + p.getX();
                        int nj = j + p.getY();
                        if (DirectionUtils.inRange(ni, nj, img.getWidth(), img.getHeight()) && img.getRGB(ni, nj) != Color.BLACK.getRGB()) {
                            if(p.isSide())
                                sides++;
                            else
                                upDown++;
                        }
                    }

                    if(!(sides == 0 && upDown == 2) && !(sides == 2 && upDown == 0)) {
                        Node nNode = new Node(i,j, listOfNodes.size());

                        if(j == 0)
                            source = nNode;
                        else if(j == img.getHeight()-1)
                            target = nNode;


                        if(lastNode == null){
                            lastNode = nNode;
                        }
                        else{
                            Edge e1 = new Edge(nNode.getY() - lastNode.getY(), nNode);
                            Edge e2 = new Edge(nNode.getY() - lastNode.getY(), lastNode);
                            lastNode.pushEdge(e1);
                            nNode.pushEdge(e2);
                            lastNode = nNode;
                        }

                        if(columLast[j] == null){
                            columLast[j] = nNode;
                        }
                        else{
                            Edge e1 = new Edge(nNode.getX() - columLast[j].getX(), nNode);
                            Edge e2 = new Edge(nNode.getX() - columLast[j].getX(), columLast[j]);
                            columLast[j].pushEdge(e1);
                            nNode.pushEdge(e2);
                            columLast[j] = nNode;
                        }
                        listOfNodes.add(nNode);

                    }
                }
                else{
                    columLast[j] = null;
                    lastNode = null;
                }
            }
        }

        System.out.println("Graph generated time elapsed: " + (System.currentTimeMillis() - init) + "ms");
        System.out.println("Nº of nodes calculated: " + listOfNodes.size());
        //System.out.println("Source node: ID: " + source.getId() + " X: " + source.getY() + " Y: " + source.getX());
        //System.out.println("Target node: ID: " + target.getId() + " X: " + target.getY() + " Y: " + target.getX());

        /*
        for (Node n : listOfNodes){

            System.out.println("Node " + n.getId() + " i: " + n.getX() + " j: " + n.getY());
            for (Edge e : n.getAdjEdges()){
                System.out.println("    Weight: " + e.getWeight() +" Node i: " + e.getNode().getX() + " j: " + e.getNode().getY());
            }
        }*/


        //Buscamos entrada y salida asumiendo que existen y se encuentran arriba/abajo del mapa
        /*for (int i = 0; i < img.getWidth(); ++i){

            if(img.getRGB(i, 0) == -1)
                img.setRGB(i, 0, Color.GREEN.getRGB());
            if(img.getRGB(i, img.getHeight()-1) == -1)
                img.setRGB(i, img.getHeight() - 1, Color.GREEN.getRGB());
        }*/


        /*
        try {

            File outputfile = new File("C:/Users/Fran/Desktop/mazeSolver/mazes/joins.png");
            ImageIO.write(img, "png", outputfile);
        }
        catch (IOException e){
            System.out.println("Y volo");
        }
        */
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public int getNodeNumber(){
        return this.listOfNodes.size();
    }

    public void redraw(int parent[]){

        int id;
        img.setRGB(this.target.getX(), this.target.getY(), Color.magenta.getRGB());
        Node previus = this.target;
        id = parent[this.target.getId()];
        while (id != -1){

            Node n = listOfNodes.get(id);
            int i;
            if(n.getX() == previus.getX()){
                i = n.getY();
                while (i != previus.getY()){
                    img.setRGB(n.getX(), i, Color.magenta.getRGB());
                    if(previus.getY() > n.getY())
                    i++;
                    else
                        i--;
                }
            }
            else if(n.getY() == previus.getY()){
                i = n.getX();
                while (i != previus.getX()){
                    img.setRGB(i, n.getY(), Color.magenta.getRGB());
                    if(previus.getX() > n.getX())
                        i++;
                    else
                        i--;
                }
            }
            previus = n;
            id = parent[n.getId()];
        }

        try {
            File outputfile = new File("C:/Users/Fran/Desktop/mazeSolver/mazes/solution.png");
            ImageIO.write(img, "png", outputfile);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
