package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * Stackline online assessment
 * valid condition: path sum % k == 0
 * return numbers of validation
 */
public class PathSum2 {

    public static int solution(List<Integer> list, List<Integer> edgeFrom, List<Integer> edgeTo, int k) {
        int count = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> nodeCost = new HashMap<>();

        for (int i = 0; i < edgeFrom.size(); i++) {
            int n1 = edgeFrom.get(i);
            int n2 = edgeTo.get(i);
            graph.computeIfAbsent(n1, x -> new ArrayList<>()).add(n2);
            graph.computeIfAbsent(n2, x -> new ArrayList<>()).add(n1);

            nodeCost.put(n1, list.get(n1-1));
            nodeCost.put(n2, list.get(n2-1));
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> pathSum = new HashMap<>();

        for (int i = 1; i <= list.size(); i++) {
            int root = i;
            if (!visited.contains(root)) {
                visited.add(root);

                Stack<Integer> stack = new Stack();
                stack.push(root);
                pathSum.computeIfAbsent(root, x -> new ArrayList<>()).add(nodeCost.get(root));

                while (!stack.isEmpty()) {
                    int node = stack.pop();
                    for (int cost : pathSum.get(node)) {
                        if (cost % k == 0) {
                            count++;
                        }
                    }

                    for (int neighbor : graph.get(node)) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            stack.push(neighbor);

                            int neighborCost = nodeCost.get(neighbor);
                            pathSum.computeIfAbsent(neighbor, x -> new ArrayList<>()).add(neighborCost);
                            for (int parentCost : pathSum.get(node)) {
                                int currentCost = neighborCost + parentCost;
                                pathSum.get(neighbor).add(currentCost);
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2,3,0,3,0));
        List<Integer> edgeFrom = new ArrayList<>(Arrays.asList(2,3,3,3));
        List<Integer> edgeTo = new ArrayList<>(Arrays.asList(3,1,4,5));
        int k = 3;
        System.out.println(solution(list,edgeFrom, edgeTo, k));
    }
}
