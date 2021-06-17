package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * 210. Course Schedule II
 * Topological sorting of directed graphs
 * Note if there are circles in the graphs.
 */
public class CourseSchedule {
    static int WHITE = 1;
    static int GRAY = 2;
    static int BLACK = 3;

    boolean isPossible;
    Map<Integer, Integer> color;
    Map<Integer, List<Integer>> adjList;
    Stack<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new Stack<>();

        // By default all vertices are WHITE
        for (int i = 0; i < numCourses; i++) {
            this.color.put(i, WHITE);
        }
    }

    private void dfs(int node) {
        // Don't recurse further if we found a cycle already
        if (!isPossible) {
            return;
        }

        // Start the recursion
        color.put(node, GRAY);

        // Traverse on neighboring vertices
        for (Integer neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (color.get(neighbor) == WHITE) {
                dfs(neighbor);
            } else if (color.get(neighbor) == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                isPossible = false;
            }
        }

        // Recursion ends. We mark it as black
        color.put(node, BLACK);
        topologicalOrder.push(node);
    }

    /**
     * Depth First Search
     * V represents the number of vertices and E represents the number of edges
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        init(numCourses);

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];

            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);
        }

        // If the node is unprocessed, then call dfs on it.
        for (int i = 0; i < numCourses; i++) {
            if (color.get(i) == WHITE) {
                dfs(i);
            }
        }

        int[] order;
        if (isPossible) {
            order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = topologicalOrder.pop();
            }
        } else {
            order = new int[0];
        }

        return order;
    }

    /**
     * Node Indegree
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        // Create the adjacency list representation of the graph
        for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);

            // Record in-degree of each vertex
            indegree[dest]++;
        }

        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        // Process until the Q becomes empty
        while (!q.isEmpty()) {
            int node = q.poll();
            topologicalOrder[i++] = node;

            // Reduce the in-degree of each neighbor by 1
            for (Integer neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
                indegree[neighbor]--;

                // If in-degree of a neighbor becomes 0, add it to the Q
                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        // Check to see if topological sort is possible or not.
        if (i == numCourses) {
            return topologicalOrder;
        }

        return new int[0];
    }
}
