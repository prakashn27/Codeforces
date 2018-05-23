package Reference;
/*
Author: prakashn
Date  : 3/24/2018
*/

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
# Ticker|Shares
# IBM|1000
# AAPL|500
# NFLX|600
# AAPL|900
# GOOG|1200
# NFLX|100
# FB|2000

# Output:
# FB|2000
# AAPL|1400
# GOOG|1200
# IBM|1000
# NFLX|700
 */
public class pqImpl {
    HashMap<String, Integer> map;
    PriorityQueue<TS> pq;
    pqImpl() {
        map = new HashMap<>();
        pq = new PriorityQueue<TS>(3, new LessComparator());
    }

    public void addStock(String t, Integer s) {
        if(map.containsKey(t)) {
            map.put(t, map.get(t) + s);
            return;
        }
        map.put(t, s);
    }
    public void printTop(int n) {
        PriorityQueue<TS> pq = new PriorityQueue<TS>(3, new LessComparator());
        for(String k : map.keySet()) {
            pq.offer(createTS(k, map.get(k)));
            if(pq.size() > n) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
    private static TS createTS(String tick, Integer shares) {
        return new TS(tick, shares);
    }
    public static void main (String[] args) throws java.lang.Exception
    {

        pqImpl sol = new pqImpl();
        sol.addStock("IBM",1000);
        sol.addStock("AAPL",500);
        sol.addStock("NFLX",600);
        sol.addStock("AAPL",900);
        sol.addStock("GOOG",1200);
        sol.addStock("NFLX",100);
        sol.addStock("FB",2000);
        sol.printTop(3);
//

    }
}

class TS {
    public String ticker;
    public Integer shares;
    TS(String t, int s) {
        ticker = t;
        shares = s;
    }
    public String toString() {
        return "T:" + ticker + " S:" + shares;
    }

    @Override
    public int hashCode() {
        return ticker.hashCode();
    }
}
class LessComparator implements Comparator<TS>
{
    @Override
    public int compare(TS x, TS y)
    {
        return x.shares.compareTo(y.shares);
    }
}
