package com.yangdi.leetcode.binarytree;

import java.util.*;

public class ParticipantRate {
    class EmployeeNode {
        String name;
        String manager;
        boolean status;
        int partiNum = 0;
        int allNum = 1;

        EmployeeNode(String name, boolean status) {
            this.name = name;
            this.status = status;
        }
        EmployeeNode(String name, String manager, boolean status) {
            this.name = name;
            this.manager = manager;
            this.status = status;
        }
    }

    Map<String, List<EmployeeNode>> managerOrg;
    ArrayList<EmployeeNode> allManager;
    Map<String, Double> partiRate;
    HashSet<String> partiEmployee;

    public List<String> maxPartRateManager(List<List<String>> orgEmployee, List<String> participants) {
        managerOrg = new HashMap<>();
        allManager = new ArrayList<>();
        partiRate = new HashMap<>();
        partiEmployee = new HashSet<>(participants);

        // create the org tree
        for (List<String> employRelation : orgEmployee) {
            String manager = employRelation.get(0);
            String employee = employRelation.get(1);

            EmployeeNode managerNode = new EmployeeNode(manager, partiEmployee.contains(manager));
            EmployeeNode employeeNode = new EmployeeNode(employee, manager, partiEmployee.contains(employee));
            allManager.add(managerNode);

            List<EmployeeNode> list = managerOrg.getOrDefault(manager, new ArrayList<>());
            list.add(employeeNode);
            managerOrg.put(manager, list);
        }

        // find the root of the tree
        EmployeeNode rootManager = null;
        for (EmployeeNode node : allManager) {
            if (null == node.manager) {
                rootManager = node;
                break;
            }
        }

        // post order traversal of the tree
        postOrderTraversal(rootManager);

        // find the managers with max rate
        List<String> result = new ArrayList<>();
        double maxRate = Collections.max(partiRate.values());
        for (Map.Entry<String, Double> entry : partiRate.entrySet()) {
            if (entry.getValue() == maxRate) {
                result.add(entry.getKey());
            }
        }

        // sort result in Lexicographical order
        Collections.sort(result);
        return result;
    }

    void postOrderTraversal(EmployeeNode root) {
        if (!managerOrg.containsKey(root.name)) {
            root.partiNum = root.status ? 1 : 0;
            return;
        }

        int partiNum = root.status ? 1 : 0;
        int allNum = 1;
        for (EmployeeNode node : managerOrg.get(root.name)) {
            postOrderTraversal(node);

            partiNum += node.partiNum;
            allNum += node.allNum;
        }

        root.partiNum = partiNum;
        root.allNum = allNum;
        partiRate.put(root.name, (double)partiNum/allNum);
    }

    public static void main(String[] orgs) {
        List<List<String>> orgEmployee = new ArrayList<>();
        List<String> participants = new ArrayList<>();

        orgEmployee.add(Arrays.asList("A", "B"));
        orgEmployee.add(Arrays.asList("A", "C"));
        orgEmployee.add(Arrays.asList("B", "D"));
        orgEmployee.add(Arrays.asList("B", "E"));
        orgEmployee.add(Arrays.asList("C", "F"));
        orgEmployee.add(Arrays.asList("C", "G"));
        orgEmployee.add(Arrays.asList("C", "H"));
        orgEmployee.add(Arrays.asList("C", "I"));
        orgEmployee.add(Arrays.asList("C", "J"));

        participants.add("B");
        participants.add("C");
        participants.add("D");
        participants.add("G");
        participants.add("I");
        participants.add("J");

        ParticipantRate pRate = new ParticipantRate();
        List<String> result = pRate.maxPartRateManager(orgEmployee, participants);
        System.out.println(result.toString());
    }
}
