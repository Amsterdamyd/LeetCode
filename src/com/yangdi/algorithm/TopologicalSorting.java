package com.yangdi.algorithm;

import java.util.*;

/**
 * 拓扑排序：将入度为0的顶点加入队列，依次poll出这些顶点，并将他们的临近结点入度减1，如出现新的入度为0结点，再入队列，并依次处理
 * 只适用于有向无环图
 * 典型应用：210. Course Schedule II
 */
public class TopologicalSorting {

    public int[] topologicalSort(int numCourses, int[][] prerequisites) {
        int[] sortResult = new int[numCourses];

        Map<Integer, List<Integer>> neighbors = new HashMap<>();
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int[] prerequisite : prerequisites) {
            int destination = prerequisite[0];
            int resource = prerequisite[1];

            //Create the adjacency list representation of the graph
            neighbors.computeIfAbsent(resource, x -> new ArrayList<>()).add(destination);
            // Record in-degree of each vertex
            indegree[destination]++;
        }

        // Add all vertices with 0 in-degree to the queue
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // Process all 0 in-degree vertex until the queue becomes empty
        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            sortResult[index++] = node; // get an element of sorted result

            for (int neighbor :  neighbors.getOrDefault(node, new ArrayList<>())) {
                indegree[neighbor]--; // Reduce the in-degree of each neighbor by 1
                if (indegree[neighbor] == 0) { // If in-degree of a neighbor becomes 0, add it to the Q
                    queue.add(neighbor);
                }
            }
        }

        if (index != numCourses) { // circle exists
            return new int[0];
        } else {
            return sortResult;
        }
    }
}
