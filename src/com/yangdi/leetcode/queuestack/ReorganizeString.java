package com.yangdi.leetcode.queuestack;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 767. Reorganize String
 */
public class ReorganizeString {

    public String reorganizeString(String S) {

        HashMap<Character,Integer> H = new HashMap<>();
        for (int i = 0; i < S.length(); ++i) {
            H.put(S.charAt(i),H.getOrDefault(S.charAt(i),0)+1);
        }

        // greatest one is at the top of priority queue
        PriorityQueue<Character> P = new PriorityQueue<>((a, b) -> H.get(b) - H.get(a));
        P.addAll(H.keySet());
        System.out.println(P.peek());

        String ans = "";
        while (P.size() >= 2) {
            char first = P.poll();
            H.put(first, H.get(first)-1);

            char second=P.poll();
            H.put(second,H.get(second)-1);

            ans += "" + first + "" + second;
            if (H.get(first) >= 1) {
                P.add(first);
            }
            if (H.get(second) >= 1) {
                P.add(second);
            }
        }

        if (P.size() == 1 && H.get(P.peek()) == 1) {
            ans += P.peek();
            return ans;
        } else if (ans.length() == S.length()) {
            return ans;
        } else {
            return "";
        }
    }
}
