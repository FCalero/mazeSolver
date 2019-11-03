package com.fran.algorithms;

import com.fran.graph.*;

import java.util.*;

public class Dijkstra implements Algorithm {

    class queueElement implements Comparable{

        public int weight;
        public Node n;

        public queueElement(int weight, Node n){
            this.weight = weight;
            this.n = n;
        }
        @Override
        public int compareTo(Object o) {
            return this.weight - ((queueElement)o).weight;
        }

    }


    @Override
    public void solve(Graph g) {
        System.out.println("Solving maze...");
        long init = System.currentTimeMillis();

        int nodeNumber = g.getNodeNumber();
        int dist[] = new int[nodeNumber];
        int parent[] = new int[nodeNumber];

        for (int i = 0; i < dist.length; ++i){
                dist[i] = 1000000000;
        }

        dist[g.getSource().getId()] = 0;
        parent[g.getSource().getId()] = -1;

        PriorityQueue<queueElement> q = new PriorityQueue<>();
        q.add(new queueElement(0, g.getSource()));

        while (!q.isEmpty()) {

            queueElement qe = q.poll();

            if(qe.weight > dist[qe.n.getId()])
                continue;

            for (Edge e : qe.n.getAdjEdges())
            {
                Node n = e.getNode();
                int nid = n.getId();

                int nDist = dist[qe.n.getId()] + e.getWeight();
                if(nDist < dist[nid]){
                    dist[nid] = nDist;
                    parent[nid] = qe.n.getId();
                    q.add(new queueElement(nDist, n));
                }
            }
        }
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - init) + "ms");
        System.out.println("Drawing path...");
        init = System.currentTimeMillis();
        g.redraw(parent);
        System.out.println("Time elapsed: " + (System.currentTimeMillis() - init + "ms"));
    }
}
