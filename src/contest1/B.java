package contest1;/**
 * Created by prakashn on 8/14/2017.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class B {
    InputStream is;
    PrintWriter out;
    String INPUT = "10\n" +
            "R50C12\n" +
            "R23C47\n" +
            "Y96\n" +
            "R44C13\n" +
            "R19C21\n" +
            "R95C73\n" +
            "BK12\n" +
            "R51C74\n" +
            "AY34\n" +
            "R63C25";
    String CHARACTERS="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    void solve() {
        int t = ni();
        String s;
        while(t-- > 0) {
            s = ns();
            if(s.matches("[R]\\d+[C]\\d+")) {
                out.println(ok(s));
            } else {
                out.println(ok2(s));
            }
        }
    }
    public String ok(String s) {
        // R23C55 -> BC23
        int cIndex = s.indexOf('C');
        int row = Integer.parseInt(s.substring(1,cIndex));
        int col = Integer.parseInt(s.substring(cIndex+1));
        return convert(col)+row;
    }
    public String convert(int num) {
        StringBuilder sb = new StringBuilder();
        int clen = 1, cind = 26;
        while (num >= cind) {
            num -= cind;
            cind *= 26;
            clen++;
        }
        for (int i = 0; i < clen; i++) {
            sb.append(CHARACTERS.charAt(num%26));
            num /= 26;
        }
//        while(n > 0) {
//            sb.append(CHARACTERS.charAt(n%26));
//            n /= 26;
//        }
        return sb.reverse().toString();
    }
    public String ok2(String s) {
        // BC23 -> R23C55
        int i;
        for(i = 0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) break;
        }
        int c = convertBack(s.substring(0,i));
        return "R" + s.substring(i) + "C" + c;
    }
    public Integer convertBack(String s) {
        // BC -> 55
        int l = s.length();
        int n = 0;
        for (int i = 0; i < l; i++) {
            int t = CHARACTERS.indexOf(s.charAt(i));
            double p = Math.pow(26,l-i-1);
            n += (int)t*p;
        }
        return n;
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
        new B().run();
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
