package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

public class Cycle {
    boolean containsCycle;
    static int WHITE = 0, GREY = 1, BLACK = 2;
    int[] marked; // 0 for white(undiscovered) , 1 for discovered, 2 for processed
    Cycle(DiGraph g) {
        containsCycle = false;
        marked = new int[g.V];
        for (int i = 0; i < g.V; i++) {
            if(marked[i] == WHITE) {
                marked[i] = GREY;
                dfs(g, i);
            }
        }
    }

    void dfs(DiGraph g, int s) {
        for(int w : g.adj[s]) {
            if(marked[w] == WHITE) {
                marked[w] = GREY;
                dfs(g, w);
            } else {
                // back or cross edge
                containsCycle = true;
            }
        }
        marked[s] = BLACK;
    }

    boolean hasCycle() {
        return containsCycle;
    }
}

