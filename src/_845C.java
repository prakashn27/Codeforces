/**
 * Created by prakashn on 8/21/2017.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.InputMismatchException;

public class _845C {
    InputStream is;
    PrintWriter out;
    String INPUT = "4\n" +
            "1 2\n" +
            "2 3\n" +
            "2 3\n" +
            "1 2";
    class Pair {
        public Integer x, y;
        Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return x + ":" + y;
        }
    }
    void solve() {
        int n = ni();
        LinkedList<Pair> ll = new LinkedList<Pair>();
        for (int i = 0; i < n; i++) {
            ll.add(new Pair(ni(), ni()));
        }
        Collections.sort(ll, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.x.compareTo(o2.x);
            }
        });
//        for(Pair p : ll) {
//            out.println(p);
//        }
        if(checkList(ll)) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
    public boolean checkList(LinkedList<Pair> ll) {
        LinkedList<Pair> p1 = new LinkedList<Pair>();
        LinkedList<Pair> p2 = new LinkedList<Pair>();
        for(Pair p : ll) {
            if(p1.isEmpty()) {
                p1.add(p);
                continue;
            }
            if(p2.isEmpty()) {
                p2.add(p);
                continue;
            }
            Pair a1 = p1.peek();
            Pair a2 = p2.peek();
            if(a1.y < p.x) {
                p1.add(p);
                continue;
            } else if(a2.y < p.x) {
                p2.add(p);
                continue;
            } else {
                return false;
            }
        }
        return true;
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
        new _845C().run();
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
