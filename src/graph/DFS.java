package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

import java.util.Arrays;
import java.util.LinkedList;

public class DFS {
    public static int INFINITY = Integer.MAX_VALUE;
    int[] startTime, finishTime, distTo, edgeTo;
    boolean[] marked;
    int time, s;
    Graph g;
    DFS(Graph g, int source) {
        this.s = source;
        this.g = g;
        marked = new boolean[g.V];
        Arrays.fill(marked, false);
        startTime = new int[g.V];
        Arrays.fill(startTime, INFINITY);
        finishTime = new int[g.V];
        Arrays.fill(finishTime, INFINITY);
        distTo = new int[g.V];
        Arrays.fill(distTo, INFINITY);
        edgeTo = new int[g.V];
        Arrays.fill(edgeTo, -1);
        time = 0;

        marked[source] = true;
        distTo[source] = 0;
        DFSRec(source);
    }

    void DFSRec(int source) {
        startTime[source] = time++;
        for(int w : g.adj[source]) {
            if(!marked[w]) {
                marked[w] = true;
                edgeTo[w] = source;
                distTo[w] = distTo[source] + 1;
                DFSRec(w);
            }
        }
        finishTime[source] = time++;
    }

    /*
    Return number of children on w
    */
    int countChildren(int w) {
        if(distTo[w] == INFINITY) return -1;
        return (finishTime[w] - startTime[w] - 1)/2;
    }

    /*
    Returns the path from source to v
    */
    Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)) return null;
        LinkedList<Integer> path = new LinkedList<>();
        while(v != -1) {
            path.addFirst(v);
            v = edgeTo[v];
        }
        return path;
    }

    boolean hasPathTo(int v) {
        if(v == s) return false;
        return distTo[v] != INFINITY;
    }

    boolean checkAncestor(int parent, int child) {
        return finishTime[parent] > finishTime[child] && startTime[parent] < startTime[child];
    }
}

