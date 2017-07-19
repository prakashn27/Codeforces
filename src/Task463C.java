/*
Author: prakashn
ProblemLink: 
Thanks to uwi for the code template
*/

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Task463C {
    InputStream is;
    PrintWriter out;
    String INPUT = "\n" +
            "4\n" +
            "1 1 1 1\n" +
            "2 1 1 0\n" +
            "1 1 1 0\n" +
            "1 0 0 1";

    void solve() {
        int n = ni();
        int[][] a = new int[n][n];
        for(int i = 0; i < n; i++) {
            a[i] = na(n);
        }
//        printa(a);
        long[][] l = new long[n][n];
        long[][] r = new long[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || j == 0) l[i][j] = a[i][j];
                else l[i][j] = l[i-1][j-1] + a[i][j];
            }
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = n-1; j >= 0; j--) {
                if(i == n-1 || j == n-1) continue;
                else l[i][j] = l[i+1][j+1];
            }
        }
//        printa(l);

        for(int i = 0; i < n; i++) {
            for(int j = n-1; j >= 0; j--) {
                if(i == 0 || j == n-1) r[i][j] = a[i][j];
                else r[i][j] = r[i-1][j+1] + a[i][j];
            }
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                if(i == n-1 || j == 0) continue;
                else r[i][j] = r[i+1][j-1];
            }
        }
//        printa(r);
        long[][] b = new long[n][n];
        long max = Integer.MIN_VALUE;
        int x1 = -1, y1 = -1;
        long max2 = Integer.MIN_VALUE;
        int x2 = -1, y2 = -1;
        boolean isEven = true;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                b[i][j] = l[i][j] + r[i][j] - a[i][j];
                isEven = (i+j) % 2 == 0;
                if(max < b[i][j] && isEven) {
                    max = b[i][j];
                    x1 = i;
                    y1 = j;
                }
                if(max2 < b[i][j] && !isEven) {
                    max2 = b[i][j];
                    x2 = i;
                    y2 = j;
                }
            }
        }
//        printa(b);
//
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < n; j++) {
//                if(max2 < b[i][j]) {
//                    if(i == x1 && j == y1) continue;
//                    max2 = b[i][j];
//                    x2 = i;
//                    y2 = j;
//                }
//            }
//        }
        out.println(max+max2);
        out.println((x1+1) + " " + (y1+1) + " " + (x2+1) + " " + (y2+1));

    }

    void printa(int[][] a) {
        out.printf("\t");
        for(int i = 0; i < a[0].length; i++) out.printf("%5d ", i);
        out.println();
        out.println("----------------");
        for(int i = 0; i < a.length; i++)
        {
            out.printf(i + " =>" );
            for(int j = 0; j < a[i].length; j++)
            {
                out.printf("%5d ", a[i][j]);
            }
            out.println();
        }
        out.println();
    }

    void run() throws Exception {
        is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        long s = System.currentTimeMillis();
        solve();
        out.flush();
        tr(System.currentTimeMillis() - s + "ms");
    }

    public static void main(String[] args) throws Exception {
        new Task463C().run();
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

    private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

    private void tr(Object... o) {
        if (!oj) System.out.println(Arrays.deepToString(o));
    }
}
