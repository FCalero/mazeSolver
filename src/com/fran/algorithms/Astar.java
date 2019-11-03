package com.fran.algorithms;

import com.fran.graph.*;

import java.awt.image.*;
import java.util.*;

public class Astar implements Algorithm{

    private class AstarElement implements Comparable{

        int f;
        int g;
        Node n;

        AstarElement(int g, int f, Node n){
            this.g = g;
            this.f = f;
            this.n = n;
        }
        @Override
        public int compareTo(Object o) {
            return this.f - ((AstarElement)o).f;
        }

    }

    @Override
    public BufferedImage solve(Graph g) {

        System.out.println("Solving maze...");
        long init = System.currentTimeMillis();

        int nodeNumber = g.getNodeNumber();
        int dist[] = new int[nodeNumber];
        int parent[] = new int[nodeNumber];

        for (int i = 0; i < dist.length; ++i){
            dist[i] = 1000000000;
        }

        dist[g.getSource().getId()] = Math.abs (g.getSource().getX() - g.getTarget().getX()) + Math.abs (g.getSource().getY() - g.getTarget().getY());
        parent[g.getSource().getId()] = -1;

        PriorityQueue<AstarElement> q = new PriorityQueue<>();
        q.add(new AstarElement(0,dist[g.getSource().getId()], g.getSource()));

        while (!q.isEmpty()) {

            AstarElement qe = q.poll();

            if(qe.f > dist[qe.n.getId()])
                continue;
            if(qe.n.getId() == g.getTarget().getId())
                break;

            for (Edge e : qe.n.getAdjEdges())
            {
                int id = e.getNode().getId();
                int ge = qe.g + e.getWeight();
                int he = Math.abs (e.getNode().getX() - g.getTarget().getX()) + Math.abs (e.getNode().getY() - g.getTarget().getY()) ;
                int fe = ge + he;

                if (fe < dist[id]){
                    dist[id] = fe;
                    parent[id] = qe.n.getId();
                    q.add(new AstarElement(ge, fe, e.getNode()));
                }
            }
        }

        System.out.println("Time elapsed: " + (System.currentTimeMillis() - init) + "ms");
        System.out.println("Drawing path...");
        init = System.currentTimeMillis();
        BufferedImage img = g.redraw(parent);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - init + "ms"));
        return img;

    }
}
