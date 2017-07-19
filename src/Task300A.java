/*
Author: prakashn
ProblemLink: http://codeforces.com/problemset/problem/300/A
Thanks to uwi for the code template
*/

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class Task300A {
    InputStream is;
    PrintWriter out;
    String INPUT = "4\n" +
            "0 -1 -2 -3";
    LinkedList<Integer> a, b, c;

    void solve() {
        int n = ni();
        int nums[] = na(n);
        Arrays.sort(nums);
        int zer0 = Arrays.binarySearch(nums, 0);
        int noOfNegatives = zer0;
        a = new LinkedList<>(); // <
        b = new LinkedList<>(); // >
        c = new LinkedList<>(); // ==
        int start = 0, end = n-1;
        a.add(nums[start++]);
        if(nums[n-1] == 0) {
            // add two nega to make it positive
            b.add(nums[start++]);
            b.add(nums[start++]);
            for(int j = start; j <= end; j++) {
                c.add(nums[j]);
            }
        } else {
            b.add(nums[end--]);
            for(int j = start; j <= end; j++) {
                c.add(nums[j]);
            }
        }

//        int i = 0;
//        if(noOfNegatives == 1) {
//            a.add(nums[i++]);
//        }else if (noOfNegatives == 2) {
//            a.add(nums[i++]);
//            c.add(nums[i++]);
//        } else {
//            int temp = noOfNegatives / 3;
//            for (int j = 0; j < temp; j++) {
//                a.add(nums[i++]);
//                b.add(nums[i++]);
//                b.add(nums[i++]);
//            }
//            temp = noOfNegatives % 3;
//            while (temp-- >= 0) {
//                c.add(nums[i++]);
//            }
//        }
//        for(int j = 0; j < n - zer0 && i < n; j++) {
//            switch (j%3) {
//                case 0:
//                    b.add(nums[i++]);
//                    break;
//                case 1:
//                    c.add(nums[i++]);
//                    break;
//                case 3:
//                    a.add(nums[i++]);
//                    break;
//            }
//        }

        out.println(printFormat(a));
        out.println(printFormat(b));
        out.println(printFormat(c));

    }
    String printFormat(LinkedList<Integer> a) {
        String _a = Integer.toString(a.size());
        for(int x : a) {
            _a += " " + x;
        }
        return _a;
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
        new Task300A().run();
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
