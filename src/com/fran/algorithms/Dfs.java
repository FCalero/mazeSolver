package com.fran.algorithms;

import com.fran.graph.*;

import java.util.*;

public class Dfs implements Algorithm {

    @Override
    public void solve(Graph g) {

        System.out.println("Solving maze...");
        long init = System.currentTimeMillis();

        int nodeNumber = g.getNodeNumber();
        int dist[] = new int[nodeNumber];
        int parent[] = new int[nodeNumber];
        boolean visited[] = new boolean[nodeNumber];
        Stack<Node> s = new Stack<>();

        int id = g.getSource().getId();
        dist[id] = 1;
        parent[id] = -1;
        s.add(g.getSource());

        while (!s.isEmpty()) {
            Node p = s.pop();

            if(!visited[p.getId()]) {
                visited[p.getId()] = true;
                for (Edge e : p.getAdjEdges()) {
                    Node n = e.getNode();
                    int nid = n.getId();
                    if (!visited[nid]) {
                        dist[nid] = dist[p.getId()] + 1;
                        parent[nid] = p.getId();
                        if (nid == g.getTarget().getId()) {
                            s.clear();
                            break;
                        }
                        s.add(n);
                    }
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
