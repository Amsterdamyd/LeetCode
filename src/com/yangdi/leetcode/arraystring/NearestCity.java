package com.yangdi.leetcode.arraystring;

import java.lang.reflect.Array;
import java.util.*;

/**
 * DoorDash面试题
 * Nearest Neighbour City
 * A number of cities are arranged on a graph that has been divided up like an ordinary Cartesian plane.
 * Each city is located at an integral (x, y) coordinate intersection.
 * City names and locations are given in the form of three arrays: c, x, and y, which are aligned by
 * the index to provide the city name (c[i]), and its coordinates, (x[i], y[i]).
 * Determine the name of the nearest city that shares either an x or a y coordinate with the queried city.
 * If no other cities share an x or y coordinate, return 'NONE'.
 * If two cities have the same distance to the queried city, q[i], consider the one with an alphabetically
 * shorter name (i.e. 'ab' < 'aba' < 'abb') as the closest choice. The distance is the Manhattan distance,
 * the absolute difference in x plus the absolute difference in y.
 */
 public class NearestCity {

    public List<String> closestStraightCity(List<String> cities, List<Integer> x,
                                                   List<Integer> y, List<String> queries) {
         Map<String, Integer> cityIndex = new HashMap<>();

         for (int i = 0; i < cities.size(); i++) {
             cityIndex.put(cities.get(i), i);
         }

         List<String> result = new ArrayList<>();
         for (String query : queries) {
             String nearest = getNearestCity(cities, x, y, cityIndex, query);
             result.add(nearest);
         }

         return result;
    }

    String getNearestCity(List<String> cities, List<Integer> x,
                          List<Integer> y, Map<String, Integer> cityIndex, String query) {
        if (!cityIndex.containsKey(query)) {
            return "NONE";
        }

        int row = x.get(cityIndex.get(query));
        int col = y.get(cityIndex.get(query));

        int index = -1;
        int smallestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < cities.size(); i++) {
            if (row == x.get(i) && col == y.get(i)) { // the queried city
                continue;
            } else if (row != x.get(i) && col != y.get(i)) {
                continue;
            }

            int curDistance = 0;
            if (row == x.get(i)) {
                curDistance = Math.abs(col - y.get(i));
            } else if (col == y.get(i)) {
                curDistance = Math.abs(row - x.get(i));
            }

            if (curDistance < smallestDistance) {
                smallestDistance = curDistance;
                index = i;
            } else if (curDistance == smallestDistance) {
                if (cities.get(i).compareTo(cities.get(index)) < 0) {
                    index = i;
                }
            }
        }

        return index == -1 ? "NONE" : cities.get(index);
    }

    public static void main(String[] args) {
        NearestCity near = new NearestCity();

        String[] cities = {"axx", "axy", "az", "axd", "aa", "abc", "abs", "p"};
        int[] xValues = {0,1,2,4,5,0,1,0};
        int[] yValues = {1,2,5,3,4,2,0,2};
        String[] queries = {"axx", "axy", "abs", "zmm"};

        List<Integer> x = new ArrayList<>();
        for (int xValue : xValues) {
            x.add(xValue);
        }

        List<Integer> y = new ArrayList<>();
        for (int yValue : yValues) {
            y.add(yValue);
        }

        List<String> results = near.closestStraightCity(Arrays.asList(cities), x, y, Arrays.asList(queries));
        for (String result : results) {
            System.out.println(result);
        }
    }
}
