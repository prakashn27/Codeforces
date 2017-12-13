package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

import java.util.Arrays;
import java.util.LinkedList;

public class BFS {
    public static int INFINITY = Integer.MAX_VALUE;
    int[] distTo;
    boolean[] visited; // same as visited
    int[] edgeTo;
    int s; // source
    /*
    Computes the shortest path between the source vertex s and every other vertex in the graph G.
     */
    BFS(Graph g, int s) {
        distTo = new int[g.V];
        Arrays.fill(distTo, INFINITY);
        visited = new boolean[g.V];
        edgeTo = new int[g.V];
        Arrays.fill(edgeTo, -1);
        this.s = s;
        // compute BFS
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(s);
        visited[s] = true;
        distTo[s] = 0;
        while(!q.isEmpty()) {
            int cur = q.remove();
            System.out.println("processing " + cur);
            for(int w : g.adj[cur]) {
                if(!visited[w]) {
                    distTo[w] = distTo[cur] + 1;
                    edgeTo[w] = cur;
                    q.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    /*
    Returns the number of edges in a shortest path between the source vertex s (or sources) and vertex v?
     */
    int distTo(int v) {
        if(distTo[v] == INFINITY) return -1;
        return distTo[v];
    }
    /*
    Is there a path between the source vertex s (or sources) and vertex v?
     */
    boolean hasPathTo(int v) {
        if(v == s) return false;
        return distTo[v] != INFINITY;
    }

    /*
    returns iterable from path s to v
     */
    Iterable<Integer> 	pathTo(int v) {
        if(!hasPathTo(v)) return null;
        LinkedList<Integer> path = new LinkedList<>();
        while(v != -1) {
            path.addFirst(v);
            v = edgeTo[v];
        }
        return path;
    }
}

