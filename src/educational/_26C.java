package educational;
/**
 * Created by prakashn on 8/3/2017.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class _26C {
    InputStream is;
    PrintWriter out;
    String INPUT = "30 100 100\n" +
            "60 34\n" +
            "29 82\n" +
            "89 77\n" +
            "39 1\n" +
            "100 100\n" +
            "82 12\n" +
            "57 87\n" +
            "93 43\n" +
            "78 50\n" +
            "38 55\n" +
            "37 9\n" +
            "67 5\n" +
            "100 100\n" +
            "100 100\n" +
            "82 47\n" +
            "3 71\n" +
            "100 100\n" +
            "19 26\n" +
            "25 94\n" +
            "89 5\n" +
            "100 100\n" +
            "32 1\n" +
            "100 100\n" +
            "34 3\n" +
            "40 99\n" +
            "100 100\n" +
            "36 12\n" +
            "100 100\n" +
            "100 100\n" +
            "100 100";
    Rec[] recs;
    int n,a,b;
    void solve() {
        n = ni();
        a = ni();
        b = ni();
        recs = new Rec[n];
        for(int i = 0; i < n; i++) {
            Rec t = new Rec();
            t.l = ni();
            t.b = ni();
            recs[i] = t;
        }
        int max = 0;
        for(int i = 0; i < n-1; i++) {
            for(int j = i+1; j < n; j++) {
                if(ok(recs[i], recs[j], a, b)) {
//                    out.println(recs[i] + "-" + recs[j]);
                    max = Math.max(max, cal(recs[i], recs[j]));
                }
            }
        }
        out.println(max);
    }
    int cal(Rec x, Rec y) {
        return x.l*x.b + y.l*y.b;
    }
    boolean ok(Rec x, Rec y, int a, int b) {
        return ok2(x,y,a,b) || ok2(x,y,b,a);
    }
    boolean ok2(Rec x, Rec y, int a, int b) {
        return ok3(x,y,a,b) || ok3(new Rec(x.b, x.l), y, a, b);
    }
    boolean ok3(Rec x, Rec y, int a, int b) {
        return ok4(x,y,a,b) || ok4(x, new Rec(y.b,y.l),a,b);
    }
    boolean ok4(Rec x, Rec y, int a, int b) {
        return (x.l + y.l) <= a && x.b <= b && y.b <= b;
    }
    public int cal(int ind, int remaining, int x, int y) {
        if(ind == n || remaining == 0) return 0;
        Rec t = recs[ind];
        boolean first = x - t.l > 0 && y - t.b > 0;
        boolean second = x - t.b > 0 && y - t.l > 0;
        int res = 0;
        if(first || second) {
            // seal taken
            int val1 = 0, val2 = 0;
            if(first)
                val1 = cal(ind + 1, remaining - 1, x - t.l, y - t.b);
            if(second)
                val2 = cal(ind + 1, remaining - 1, x - t.b, y - t.l);
            res += Math.max(val1, val2);

            // seal not taken
            res += Math.max(res, cal(ind+1, remaining, x, y));
            return res;
        } else {
            return 0;
        }

    }

    class Rec {
        public int l, b;
        Rec() {
        }
        Rec(int l, int b) {
            this.l = l;
            this.b = b;
        }
        public String toString() {
            return l + "," + b;
        }
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
        new _26C().run();
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
