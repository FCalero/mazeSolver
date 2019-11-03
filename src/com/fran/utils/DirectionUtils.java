package com.fran.utils;

public class DirectionUtils {

    public static class Pair{

        private int x;
        private int y;
        private boolean side = true;

        Pair(int x, int y, boolean side){
            this.x = x;
            this.y = y;
            this.side = side;
        }

        public int getX() {
            return x;
        }

        public int getY(){
            return y;
        }

        public boolean isSide() {
            return side;
        }

    }

    public static Pair dirs[] = {
            new Pair(-1,0, true),
            new Pair(0,1, false),
            new Pair(1,0, true),
            new Pair(0,-1, false)
    };

    public static boolean inRange(int ni, int nj, int mi, int mj){
        return ni >= 0 && nj >= 0 && ni < mi && nj < mj;
    }
}
