package educational;/**
 * Created by prakashn on 8/3/2017.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class _26B {
    InputStream is;
    PrintWriter out;
    String INPUT = "6 1\n" +
            "R\n" +
            "B\n" +
            "G\n" +
            "R\n" +
            "B\n" +
            "G";

    void solve() {
        int n = ni(), m = ni();
        Color r = new Color();
        Color g = new Color();
        Color b = new Color();
        boolean isError = false;
        for(int i = 0; i < n; i++) {
            char[] chs = ns().toCharArray();
            for(int j = 0; j < m; j++) {
                switch(chs[j]){
                    case 'R':
                        r.set(i, j);
                        break;
                    case 'G':
                        g.set(i,j);
                        break;
                    case 'B':
                        b.set(i,j);
                        break;
                    default:
                        //error
                        isError = true;
                }
            }
        }
//        out.println(r);
//        out.println(g);
//        out.println(b);
        if(check(r, g) && check(r,b) && check(g,b)) {
            out.println("YES");
        }
//        if(r.diagonal() == g.diagonal() && g.diagonal() == b.diagonal() && !isError) {
//            out.println("YES");
//        }
        else {
            out.println("NO");
        }

    }
    boolean check(Color a, Color b) {
        return checkx(a, b) && checky(a, b) && a.diagonal() == b.diagonal();
    }
    class Color {
        public int minx,miny,maxx,maxy;
        public Color() {
            minx = 101;
            miny = 101;
            maxx = -2;
            maxy = -2;
        }
        public void set(int x, int y) {
            minx = Math.min(minx, x);
            miny = Math.min(miny, y);
            maxx = Math.max(maxx, x);
            maxy = Math.max(maxy, y);
        }
        public String toString() {
            return minx + "," + miny + " -> " + maxx + "," + maxy + " d:" + diagonal();
        }
        public long diagonal() {
            long ans = (maxx - minx)*(maxx - minx) + (maxy - miny)*(maxy - miny);
            return ans;
        }
        public int xdiff() {
            return maxx - minx;
        }
        public int ydiff() {
            return maxy - miny;
        }
    }
    boolean checkx(Color a, Color b) {
        return a.xdiff() == b.xdiff();
    }
    boolean checky(Color a, Color b) {
        return a.ydiff() == b.ydiff();
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
        new _26B().run();
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
