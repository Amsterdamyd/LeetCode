package com.yangdi.leetcode.recursion;

import java.util.*;

/**
 * 721. Accounts Merge
 */
public class AccountsMerge {

    /**
     * Undirected graph connection problem
     * Create graph including all vertices and edges first
     * DFS search every vertex connected with current vertex
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList();
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();

        // Create graph including all vertices and edges first
        for (List<String> account: accounts) {
            String name = account.get(0);
            String vertex = account.get(1);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                // undirected graph edge needs two expression: N1->N2 & N2->N1
                graph.computeIfAbsent(vertex, x-> new ArrayList<>()).add(email);
                graph.computeIfAbsent(email, x-> new ArrayList<>()).add(vertex);
                emailToName.put(email, name);
            }
        }

        // DFS search every vertex connected with current vertex(iterative solution)
        Set<String> visited = new HashSet();

        for (String email: graph.keySet()) {
            if (!visited.contains(email)) {
                visited.add(email);

                Stack<String> stack = new Stack();
                stack.push(email);

                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String neighbor: graph.get(node)) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            stack.push(neighbor);
                        }
                    }
                }

                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }

        return ans;
    }

    /**
     * Union-Find Method
     */
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        DSU dsu = new DSU();
        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap();

        int id = 0;
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }

                emailToName.put(email, name);
                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++);
                }

                dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> ans = new HashMap();
        for (String email: emailToName.keySet()) {
            int index = dsu.find(emailToID.get(email));
            ans.computeIfAbsent(index, x-> new ArrayList()).add(email);
        }
        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(ans.values());
    }

    class DSU {
        int[] parent;
        public DSU() {
            parent = new int[10001];
            for (int i = 0; i <= 10000; ++i) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}
