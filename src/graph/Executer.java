package graph;
/*
Author: prakashn
Date  : 12/10/2017
*/

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Executer {
    public int lenbuf = 0, ptrbuf = 0;
    InputStream is;
    PrintWriter out;
    String INPUT = "5 6\n" +
            "1 2\n" +
            "2 3\n" +
            "3 1\n" +
            "1 4\n" +
            "4 5\n" +
            "5 3";
    private byte[] inbuf = new byte[1024];
    private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

    public static void main(String[] args) throws Exception {
        new Executer().run();
    }

    void solve() {
        // Construct graph
        int v = ni();
        int e = ni();
        Graph g = new Graph(v);
        for (int i = 0; i < e; i++) {
            int u = ni();
            int w = ni();
            g.addEdge(u, w);
        }
        g.printAdjList();

        // process graph
//        int from = 1, to = 4;
//        BFS bfs = new BFS(g, from);
//        System.out.println(bfs.distTo(to));
//        if(bfs.hasPathTo(to)) {
//            System.out.println(bfs.pathTo(to));
//        }
//        DFS dfs = new DFS(g, from);
//        System.out.println(dfs.countChildren(from));
//        if(dfs.hasPathTo(to)) {
//            System.out.println(dfs.pathTo(to));
//        }
//        System.out.println("check ancester 1 4 :" + dfs.checkAncestor(1, 4));
//        System.out.println("check ancester 4 2 :" + dfs.checkAncestor(4, 2));
//        System.out.println("check ancester 3 4 :" + dfs.checkAncestor(3, 4));
//        DiGraph g = new DiGraph(v);
//        for (int i = 0; i < e; i++) {
//            int u = ni();
//            int w = ni();
//            g.addEdge(u, w);
//        }
//        g.printAdjList();
//        CycleUndirected c = new CycleUndirected(g);
//          System.out.println("contains cycle: " + (c.hasCycle()));
//        ConnectedComponents cc = new ConnectedComponents(g);
//        System.out.println("no of connected components:" + cc.getCount());
        Bridge b = new Bridge(g);
        System.out.println(b.getCutVertices());
    }

    /* TEMPLATED CODE BELOW */
    void run() throws Exception {
        is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        tr(System.currentTimeMillis() - s + "ms");
    }

    private int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private double nd() {
        return Double.parseDouble(ns());
    }

    private char nc() {
        return (char) skip();
    }

    private String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    // prints 2d array
    private void printa(int[][] a) {
        out.printf("\t");
        for (int i = 0; i < a[0].length; i++) out.printf("%5d ", i);
        out.println();
        out.println("----------------");
        for (int i = 0; i < a.length; i++) {
            out.printf(i + " =>");
            for (int j = 0; j < a[i].length; j++) {
                out.printf("%5d ", a[i][j]);
            }
            out.println();
        }
        out.println();
    }

    private void tr(Object... o) {
        if (!oj) System.out.println(Arrays.deepToString(o));
    }
}

