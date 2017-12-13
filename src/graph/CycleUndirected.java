package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

public class CycleUndirected {
    boolean containsCycle;
    static int WHITE = 0, GREY = 1, BLACK = 2;
    int[] marked; // 0 for white(undiscovered) , 1 for discovered, 2 for processed
    CycleUndirected(Graph g) {
        containsCycle = false;
        marked = new int[g.V];
        for (int i = 0; i < g.V; i++) {
            if(marked[i] == WHITE) {
                marked[i] = GREY;
                dfs(g, -1, i);
            }
        }
    }

    void dfs(Graph g, int u, int v) {
        for(int w : g.adj[v]) {
            if(u == w) continue; //same edge
            if(marked[w] == WHITE) {
                marked[w] = GREY;
                dfs(g, v, w);
            } else {
                // back or cross edge
                containsCycle = true;
            }
        }
        marked[v] = BLACK;
    }

    boolean hasCycle() {
        return containsCycle;
    }
}

