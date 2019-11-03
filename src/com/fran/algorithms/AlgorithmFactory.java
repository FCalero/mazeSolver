package com.fran.algorithms;

public class AlgorithmFactory {

    public static Algorithm getAlgorithm(int algorithm){

        switch (algorithm){
            case 1 : return new Dfs();
            case 2 : return new Bfs();
            case 3 : return new Dijkstra();
            case 4: return new Astar();
            default: return null;
        }
    }

}
