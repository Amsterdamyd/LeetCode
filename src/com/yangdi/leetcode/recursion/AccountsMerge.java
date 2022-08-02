package com.yangdi.leetcode.recursion;

import java.util.*;

/**
 * 721. Accounts Merge
 */
public class AccountsMerge {

    /**
     * Undirected graph connection problem
     * dfs: iterative solution
     * Create graph including all vertices and edges first
     * Time Complexity: O(AlogA) (A = ∑ai)
     * Space Complexity: O(A)
     */
    public List<List<String>> accountsMerge1(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList();
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();

        // Create graph including all vertices and edges first
        for (List<String> account : accounts) {
            String name = account.get(0);
            String vertex = account.get(1);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                // undirected graph edge needs two expression: N1->N2 & N2->N1
                graph.computeIfAbsent(vertex, x -> new ArrayList<>()).add(email);
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(vertex);
                emailToName.put(email, name);
            }
        }

        // DFS search every vertex connected with current vertex(iterative solution)
        Set<String> visited = new HashSet();
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                visited.add(email);

                Stack<String> stack = new Stack();
                stack.push(email);

                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String neighbor : graph.get(node)) {
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
     * Undirected graph connection problem
     * dfs: recursive solution
     * time complexity: O(nklognk) (n:numbers of accounts; k:maximum length of an account)
     */
    Map<String, List<String>> graph = new HashMap<>();
    Map<String, String> emailName = new HashMap<>();
    List<List<String>> results = new ArrayList<>();
    Set<String> visited = new HashSet<>();

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        // build the graph
        // undirected graph edge needs two expression: N1->N2 & N2->N1
        for (List<String> account : accounts) {
            String name = account.get(0);
            String vertex = account.get(1);//all emails will be connected with the first one, including itself

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                graph.computeIfAbsent(vertex, x -> new ArrayList<>()).add(email); // N1->N2, N1->N3, N1-> N4
                graph.computeIfAbsent(email, x -> new ArrayList<>()).add(vertex); // N2->N1, N3-> N1, N4->N1
                emailName.put(email, name);
            }
        }

        // connect all emails in an entry (dfs solution)
        for (String email : graph.keySet()) {
            if (visited.contains(email)) {
                continue;
            }

            List<String> result = new ArrayList<>();
            dfs(email, result);

            Collections.sort(result);
            result.add(0, emailName.get(email));
            results.add(result);
        }

        return results;
    }

    void dfs(String email, List<String> result) {
        visited.add(email);
        result.add(email);

        for (String neighbor : graph.get(email)) {
            if (visited.contains(neighbor)) {
                continue;
            }

            dfs(neighbor, result);
        }
    }

    /**
     * Union-Find Method
     */
    class DSU {
        int[] root;

        public DSU(int size) {
            root = new int[size];
            for (int i = 0; i < size; ++i) {
                root[i] = i;
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                root[rootY] = rootX;
            }
        }
    }

    // <J1, 0> <J2, 1> <J3, 2>...
    public List<List<String>> accountsMerge3(List<List<String>> accounts) {
        DSU dsu = new DSU(10001);

        Map<String, String> emailToName = new HashMap();
        Map<String, Integer> emailToID = new HashMap();

        int id = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            String vertex = account.get(1);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);

                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, id++); // every email has its unique id
                }

                // union every email to its vertex email
                dsu.union(emailToID.get(vertex), emailToID.get(email));
            }
        }

        Map<Integer, List<String>> results = new HashMap();
        for (String email : emailToID.keySet()) {
            int index = dsu.find(emailToID.get(email)); // find the root vertex for each email
            results.computeIfAbsent(index, x -> new ArrayList()).add(email);
        }

        for (List<String> component : results.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }

        return new ArrayList(results.values());
    }

    // <J1, 0> <J2, 0> <J3, 0> <J4, 1> <J5, 1>...
    public List<List<String>> accountsMerge4(List<List<String>> accounts) {
        DSU dsu = new DSU(accounts.size());
        Map<String, Integer> emailToID = new HashMap();

        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);

                if (!emailToID.containsKey(email)) {
                    emailToID.put(email, i); // every email has its group id: <email, groupId>
                } else {
                    dsu.union(i, emailToID.get(email)); // union the current group to previous group
                }
            }
        }

        Map<Integer, List<String>> results = new HashMap(); // <groupId, List<email>>
        for (String email : emailToID.keySet()) {
            int index = dsu.find(emailToID.get(email)); // find the root vertex for each email
            results.computeIfAbsent(index, x -> new ArrayList()).add(email);
        }

        for (Map.Entry<Integer, List<String>> entry : results.entrySet()) {
            String name = accounts.get(entry.getKey()).get(0);
            List<String> component = entry.getValue();
            Collections.sort(component);
            component.add(0, name);
        }

        return new ArrayList(results.values());
    }

    public static void main(String[] args) {
        List<List<String>> total = new ArrayList<>();
        /*List<String> list1 = new ArrayList<>(Arrays.asList(new String[]{"John", "J1", "J2"}));
        List<String> list2 = new ArrayList<>(Arrays.asList(new String[]{"John", "J1", "J3"}));
        List<String> list3 = new ArrayList<>(Arrays.asList(new String[]{"Marry", "M1"}));
        List<String> list4 = new ArrayList<>(Arrays.asList(new String[]{"John", "J4"}));
        total.add(list1);
        total.add(list2);
        total.add(list3);
        total.add(list4);*/
        List<String> list1 = new ArrayList<>(Arrays.asList(new String[]{"John", "J1", "J2","J3"}));
        List<String> list2 = new ArrayList<>(Arrays.asList(new String[]{"John", "J4", "J5", "J1", "J6"}));
        List<String> list3 = new ArrayList<>(Arrays.asList(new String[]{"Marry", "J7"}));
        List<String> list4 = new ArrayList<>(Arrays.asList(new String[]{"John", "J8", "J9"}));
        List<String> list5 = new ArrayList<>(Arrays.asList(new String[]{"John", "J4", "J8"}));
        total.add(list1);
        total.add(list2);
        total.add(list3);
        total.add(list4);
        total.add(list5);

        AccountsMerge merge = new AccountsMerge();
        System.out.println(merge.accountsMerge4(total));
    }
}
