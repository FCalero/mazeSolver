package com.fran.algorithms;

import com.fran.graph.*;

import java.util.*;

public class Bfs implements Algorithm {

    @Override
    public void solve(Graph g) {

        System.out.println("Solving maze...");
        long init = System.currentTimeMillis();

        int nodeNumber = g.getNodeNumber();
        int dist[] = new int[nodeNumber];
        int parent[] = new int[nodeNumber];
        boolean visited[] = new boolean[nodeNumber];
        Queue<Node> q = new LinkedList<>();

        int id = g.getSource().getId();
        dist[id] = 1;
        parent[id] = -1;
        visited[id] = true;
        q.add(g.getSource());

        while (!q.isEmpty()) {
            Node p = q.poll();

            for (Edge e : p.getAdjEdges()) {
                Node n = e.getNode();
                int nid = n.getId();
                if (!visited[nid]) {
                    dist[nid] = dist[p.getId()] + 1;
                    visited[nid] = true;
                    parent[nid] = p.getId();
                    if (nid == g.getTarget().getId()) {
                        q.clear();
                        break;
                    }
                    q.add(n);
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
