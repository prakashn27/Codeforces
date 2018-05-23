package Reference;
/*
Author: prakashn
Date  : 3/25/2018
*/

public class UF {
    int[] parent;
    int[] rank;
    UF(int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int a) {
        return parent[a] == a ? a : (find(parent[a]));
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        if (!isConnected(p, q)) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
            else {
                parent[rootP] = rootQ;
                if (rank[rootP] == rank[rootQ]) rank[rootQ]++;
            }
        }
    }

    public static void main(String[] args) {
        UF uf = new UF(5);
        System.out.println("0,1:" + uf.isConnected(0,1));
        uf.union(0,1);
        System.out.println("0,1:" + uf.isConnected(0,1));
        System.out.println("0,1:" + uf.isConnected(1,2));
    }
}

