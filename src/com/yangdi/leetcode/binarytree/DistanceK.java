package com.yangdi.leetcode.binarytree;

import java.util.*;

/**
 * 863. All Nodes Distance K in Binary Tree
 */
public class DistanceK {
    Map<TreeNode, TreeNode> parent;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        // DFS
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null);

        // BFS
        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n : queue) {
                        ans.add(n.val);
                    }
                    return ans;
                }
                queue.offer(null);
                dist++;
            } else {
                // down - left
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                // down - right
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                // up - parent
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node == null) {
            return;
        }

        parent.put(node, par);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}
