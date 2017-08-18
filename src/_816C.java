/*
Author: prakashn
Date  : 17/08/2017
*/

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class _816C {
    InputStream is;
    PrintWriter out;
    String INPUT = "3 5\n" +
            "2 2 2 3 2\n" +
            "0 0 0 1 0\n" +
            "1 1 1 2 1";
    int n, m;
    int[][] mat;
    LinkedList<String> res;
    void solve() {
        n = ni();
        m = ni();
        mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            mat[i] = na(m);
        }
        res = new LinkedList<String>();
        int err;
        while(true) {
            err = isMatDone();
            if(err == -1) {
                out.println(-1);
                return;
            }
            if(err == 0) {
                if(res.size() == 0){
                    out.println(-1);
                    return;
                } else {
//                    printRes();
                }
            }
            err = subNext();
            if(err == -1) {
                out.println(-1);
                return;
            }
        }
    }
    private int subNext() {
        Pair next = getNext();
        int direction = checkPair(next);
        if(direction == -1) {
            return direction;
        }
//        applyDirection(next, direction);
        return 0;
    }
    private int checkPair(Pair cur) {
        return 0;
    }
    private Pair getNext() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(mat[i][j] > 0) return new Pair(i,j);
            }
        }
        return null;
    }
    private int isMatDone() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(mat[i][j] < 0) return -1; // error occurrec
                if(mat[i][j] != 0) return 0;
            }
        }
        return 1;
    }

    class Pair {
        public int x, y;
        Pair(int a, int b) {
            x = a;
            y = b;
        }
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

    public static void main(String[] args) throws Exception {
        new _816C().run();
    }

    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;

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

    private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

    private void tr(Object... o) {
        if (!oj) System.out.println(Arrays.deepToString(o));
    }
}
