/**
 * Created by prakashn on 7/31/2017.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class _835C {
    InputStream is;
    PrintWriter out;
    String INPUT = "3 4 5\n" +
            "3 3 1\n" +
            "1 1 2\n" +
            "2 3 0\n" +
            "0 1 1 100 100\n" +
            "1 2 2 4 4\n" +
            "2 2 1 4 7\n" +
            "1 50 50 51 51";
    void solve() {
        int n = ni(), q = ni(), c = ni();
        LinkedList<Star> stars = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            stars.add(new Star(ni(), ni(), ni(), c));
        }
//        Collections.sort(stars, new Comparator<Star>() {
//            @Override
//            public int compare(Star o1, Star o2) {
//                if(o1.x == o2.x)
//                    return o1.y.compareTo(o2.y);
//                else return o1.x.compareTo(o2.x);
//            }
//        });
        int t, x1, y1, x2, y2;
        long res = 0;
        for(int i = 0; i < q; i++) {
            t = ni();
            x1 = ni();
            y1 = ni();
            x2 = ni();
            y2 = ni();
            res = 0;
//            int index = Collections.binarySearch(stars,
//                    new Star(x1,y1,0,0),
//                    new Comparator<Star>() {
//                        @Override
//                        public int compare(Star o1, Star o2) {
//                            if(o1.x == o2.x)
//                                return o1.y.compareTo(o2.y);
//                            else return o1.x.compareTo(o2.x);
//                        }
//                    });
//            int lastindex = Collections.binarySearch(stars,
//                    new Star(x2,y2,0,0),
//                    new Comparator<Star>() {
//                        @Override
//                        public int compare(Star o1, Star o2) {
//                            if(o1.x == o2.x)
//                                return o2.y.compareTo(o1.y);
//                            else return o2.x.compareTo(o1.x);
//                        }
//                    });
//            out.println(index + ":" + lastindex);

            for(Star s: stars) {
                if(s.x >= x1 && s.y >= y1 && s.x <= x2 && s.y <= y2) {
                    res += s.currentBrightness(t);
                }
            }
            out.println(res);
        }
    }

    class Star {
        public Integer x,y,b, c;
        Star(int x1, int y1, int brig, int max) {
            x = x1;
            y = y1;
            b = brig;
            c = max;
        }
        public int currentBrightness(int t) {
            return (b+t)%(c+1);
        }
        public String toString() {
            return x+":"+y+"  ";
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
        new _835C().run();
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
