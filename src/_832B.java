/**
 * Created by prakashn on 7/24/2017.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class _832B {
    InputStream is;
    PrintWriter out;
    String INPUT = "a\n" +
            "aa\n" +
            "1\n" +
            "aaa";

    Set<Character> s = null;
    String pat = null;
    int starPat = 0;
    void solve() {
        String m = ns();
        pat = ns();
        starPat = pat.indexOf('*');
        int t = ni();
        s = new HashSet<>();
        for(char ch : m.toCharArray()) {
            s.add(ch);
        }
        while(t-- > 0) {
            String str = ns();
//            out.print(str + '\t');
            if(starPat == -1 && str.length() < pat.length()) {
                out.println("NO");
                continue;
            }
            if(starPat != -1 && str.length() < pat.length()-1) {
                out.println("NO");
                continue;
            }
            if(process(str)) out.println("YES");
            else out.println("NO");
        }
    }

    private boolean process(String str) {
        if(starPat == 0) {
            // check the last half
            return checkPattern(str.substring(str.length()-pat.length()+1), pat.substring(1, pat.length())) && checkBad(str.substring(0, str.length()-pat.length()+1));
        } else if(starPat == pat.length() - 1) {
            // check the first half
            return checkPattern(str.substring(0, starPat), pat.substring(0, starPat)) && checkBad(str.substring(starPat));
        } else if(starPat == -1) {
            // no star
            if(str.length() != pat.length()) return false;
            return checkPattern(str, pat);
        } else {
            // check in between
            String pat1 = pat.substring(0, starPat);
            String pat2 = pat.substring(starPat+1);
            String str1 = str.substring(0, starPat);
//            String str2 = str.substring(str.length()-starPat);
            String str2 = str.substring(str.length()-pat2.length());
//            String str3 = str.substring(starPat, str.length()- starPat);
            String str3 = str.substring(0+str1.length(), str.length()-str2.length());
            return checkBad(str3) && checkPattern(str1, pat1) && checkPattern(str2, pat2);
        }
    }
    private boolean checkBad(String str) {
        if(str.length() == 0) return true;
        for(char ch : str.toCharArray()) {
            if(s.contains(ch)) return false;
        }
        return true;
    }
    private boolean checkPattern(String str, String newPat) {
        for(int i = 0; i < str.length(); i++) {
            if(newPat.charAt(i) == '?') {
                if(!s.contains(str.charAt(i))) return false;
            } else {
                if(newPat.charAt(i) != str.charAt(i)) return false;
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
        new _832B().run();
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
