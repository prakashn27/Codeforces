package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

public class ConnectedComponents {
    private int count;
    private boolean[] marked;
   ConnectedComponents(Graph g) {
       marked = new boolean[g.V];
       count = 0;
       for (int i = 1; i < g.V; i++) {
           if(!marked[i]) {
               marked[i] = true;
               dfs(g, i);
               count++;
           }
       }
   }
   void dfs(Graph g, int u) {
       for(int v : g.adj[u]) {
           if(!marked[v]) {
               marked[v] = true;
               dfs(g, v);
           }
       }
   }

   public int getCount() {
       return count;
   }
}

