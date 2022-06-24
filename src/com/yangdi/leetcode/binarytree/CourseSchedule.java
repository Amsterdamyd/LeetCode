package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 207. Course Schedule
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // build the graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            int before = pre[1];
            int after = pre[0];

            List<Integer> list = graph.containsKey(before) ? graph.get(before) : new ArrayList<>();
            list.add(after);
            graph.put(before, list);
        }

        // search each vertex if there is a circle in its path
        boolean[] visited = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (detectCircle(i, graph, visited, path)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Detect Cycle in a Directed Graph
     */
    boolean detectCircle(int i, Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] path) {
        // The vertex exits in the path. That is a circle!
        if (path[i]) {
            return true;
        }

        // The vertex has been visited and processed.
        // If there is a circle, the function has been returned; If not, there is no circle
        if (visited[i]) {
            return false;
        }

        visited[i] = true;

        path[i] = true;

        for (int course : graph.getOrDefault(i, new ArrayList<>())) {
            if (detectCircle(course, graph, visited, path)) {
                return true;
            }
        }

        // No circle has been detected. Remove the vertex from the path
        path[i] = false;
        return false;
    }
}
