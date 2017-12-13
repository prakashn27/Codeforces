package graph;
/*
Author: prakashn
Date  : 12/13/2017
Detecting bridge or cur vertices
*/

import java.util.LinkedList;

public class Bridge {
    LinkedList<Integer> cutVertices;
    int[] low, start, parent;
    boolean[] visited;
    int time;
    static int NIL = -1;
    Bridge(Graph G) {
        cutVertices = new LinkedList<>();
        low = new int[G.V];
        start = new int[G.V];
        parent = new int[G.V];
        time = 0;
        visited = new boolean[G.V];
        for (int i = 1; i < G.V; i++) {
            if(!visited[i]) {
                visited[i] = true;
                parent[i] = NIL;
                BridgeUtil(i, G);
            }
        }
    }
    void BridgeUtil(int u, Graph G) {
        time++;
        int children = 0;
        low[u] = start[u] = time;
        for(int v : G.adj[u]) {
            if(!visited[v]) {
                children++;
                visited[v] = true;
                parent[v] = u;
                BridgeUtil(v, G);
                low[u] = Math.min(low[v], low[u]);
                // check for two children and root node
                if(parent[u] == NIL && children > 1) {
                    cutVertices.add(u);
                }
                if(parent[u] != NIL && low[v] >= start[u]) {
                    cutVertices.add(u);
                }
            } else if(parent[u] != v){
                low[u] = Math.min(low[u], start[v]);
            }
        }
    }

    Iterable<Integer> getCutVertices() {
        return cutVertices;
    }
}

