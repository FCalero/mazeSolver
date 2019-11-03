package com.fran.graph;

public class Edge {

    int weight;
    Node node;

    public Edge(int weight, Node node){
        this.weight = weight;
        this.node = node;
    }

    public int getWeight() {
        return weight;
    }

    public Node getNode() {
        return node;
    }
}
