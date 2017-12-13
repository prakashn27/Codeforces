package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

import java.util.LinkedList;
import java.util.List;

public class Graph {
    public int V, E;
    public List<Integer>[] adj;

    Graph(int v) {
        this.V = v+1;
        this.E = 0;
        adj = new LinkedList[v+1]; // starts from 1
        for (int i = 0; i <= v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }
    void validate(int u) {
        assert (u > 0 && u < V);
    }


    public void addEdge(int u, int w) {
        validate(u);
        validate(w);
        adj[u].add(w);
        adj[w].add(u);
    }

    public void printAdjList() {
        System.out.println("printing adj list from Graph");
        for (int i = 1; i < V; i++) {
            System.out.print("vertex " + i + "=>");
            for(Integer w : adj[i]) {
                System.out.print(w + ",");
            }
            System.out.println();
        }
    }
}

