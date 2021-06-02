package com.yangdi.leetcode.recursion;

import java.util.*;

/**
 * 332. Reconstruct Itinerary
 */
public class ReconstructItinerary {

    Map<String, List<String>> map = new HashMap<>();
    Map<String, boolean[]> isVisited = new HashMap<>();
    List<String> total = new ArrayList<>();
    int len = 0;

    public List<String> findItinerary(List<List<String>> tickets) {
        len = tickets.size();

        for (int i = 0; i < tickets.size(); i++) {
            String start = tickets.get(i).get(0);
            String end = tickets.get(i).get(1);

            List<String> endList = map.getOrDefault(start, new ArrayList<>());
            endList.add(end);
            map.put(start, endList);
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue(), (a, b) -> a.compareTo(b));
            isVisited.put(entry.getKey(), new boolean[entry.getValue().size()]);
        }

        total.add("JFK");
        backtracking("JFK");

        return total;
    }

    boolean backtracking(String start) {
        if (total.size() == len+1) {
            return true;
        }
        if (!map.containsKey(start)) {
            return false;
        }

        int index = 0;
        boolean[] isVisitedItem = isVisited.get(start);

        for (String end : map.get(start)) {
            if (!isVisitedItem[index]) {
                total.add(end);
                isVisitedItem[index] = true;

                if (backtracking(end)) {
                    return true;
                }

                total.remove(total.size()-1);
                isVisitedItem[index] = false;
            }
            index++;
        }

        return false;
    }

    public static void main(String[] args) {
        // {{"JFK","SFO"}, {"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}}
        // [["EZE","AXA"],["TIA","ANU"],["ANU","JFK"],
        // ["JFK","ANU"],["ANU","EZE"],["TIA","ANU"],
        // ["AXA","TIA"],["TIA","JFK"],["ANU","TIA"],
        // ["JFK","TIA"]]
        List<List<String>> tickets = new ArrayList<>();
        List<String> list0 = new ArrayList<>();
        list0.add("JFK");
        list0.add("SFO");
        tickets.add(list0);

        List<String> list1 = new ArrayList<>();
        list1.add("JFK");
        list1.add("ATL");
        tickets.add(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("SFO");
        list2.add("ATL");
        tickets.add(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("ATL");
        list3.add("JFK");
        tickets.add(list3);

        List<String> list4 = new ArrayList<>();
        list4.add("ATL");
        list4.add("SFO");
        tickets.add(list4);

        ReconstructItinerary itinerary = new ReconstructItinerary();
        List<String> list = itinerary.findItinerary(tickets);
        System.out.println(list.toString());
    }
}
