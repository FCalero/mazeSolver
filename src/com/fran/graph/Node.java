package com.fran.graph;

import java.util.*;

public class Node
{
    int id;
    int x;
    int y;

    List<Edge> adjEdges;

    public Node(int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
        this.adjEdges = new ArrayList<>();
    }

    public void pushEdge(Edge nEdge){
        adjEdges.add(nEdge);
    }

    public List<Edge> getAdjEdges() {
        return adjEdges;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
