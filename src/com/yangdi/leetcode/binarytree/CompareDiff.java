package com.yangdi.leetcode.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DoorDash面试题
 * At DoorDash, menus are updated daily even hourly to keep them up-to-date.
 * Each menu can be regarded as a tree. A menu can have many categories;
 * each category can have many menu_items; each menu_item can have many item_extras;
 * An item_extra can have many item_extra_options…
 * We will compare the new menu sent from the merchant with our existing menu.
 * Each item can be considered as a node in the tree. The definition of a node is defined above.
 * Either value change or the active status change means the node has been changed.
 * There are times when the new menu tree structure is different from existing trees,
 * which means some nodes are set to null. In this case, we only do soft delete for any nodes in the menu.
 * If that node or its sub-children are null, we will treat them ALL as inactive.
 * There are no duplicate nodes with the same key.
 *
 * Return the number of changed nodes in the tree.
 */
public class CompareDiff {
    static class Node {
        String key;
        int value;
        boolean active;
        List<Node> children;

        public Node (String key, int value, boolean active) {
            this.key = key;
            this.value = value;
            this.active = active;
            children = new ArrayList<>();
        }

        public boolean equals(Node node) {
            return key.equals(node.key) && value == node.value && active == node.active;
        }
    }

    int count = 0;

    // DFS
    public void getModifiedItems(Node oldMenu, Node newMenu) {
        if (oldMenu == null && newMenu == null) {
            return;
        } else if (oldMenu == null || newMenu == null || !oldMenu.equals(newMenu)) {
            count++;
        }

        Map<String, Node> children1 = getChildren(oldMenu);
        Map<String, Node> children2 = getChildren(newMenu);

        // key exists both in old and new menu; key only exists in the old menu
        for (String key : children1.keySet()) {
            getModifiedItems(children1.get(key), children2.getOrDefault(key, null));
        }

        // key doesn't exit in old menu but exist in new menu
        for (String key : children2.keySet()) {
            if (!children1.containsKey(key)) {
                getModifiedItems(null, children2.get(key));
            }
        }
    }

    public Map<String, Node> getChildren(Node node) {
        Map<String, Node> childrenMap = new HashMap<>();

        if (node == null || node.children == null) {
            return childrenMap;
        }

        for (Node child : node.children) {
            String key = child.key;
            childrenMap.put(key, child);
        }

        return childrenMap;
    }

    public static void main(String[] args) {
        CompareDiff diff = new CompareDiff();

        // old menu
        Node a1 = new Node("a", 1, true);
        Node b1 = new Node("b", 2, true);
        Node c1 = new Node("c", 3, true);
        Node d1 = new Node("d", 4, true);
        Node e1 = new Node("e", 5, true);
        Node f1 = new Node("f", 6, true);
        a1.children.add(b1);
        a1.children.add(c1);
        b1.children.add(d1);
        b1.children.add(e1);
        c1.children.add(f1);

        // new menu
        Node a2 = new Node("a", 1, true);
        Node c2 = new Node("c", 3, false);
        Node f2 = new Node("f", 66, true);
        a2.children.add(c2);
        c2.children.add(f2);

        diff.getModifiedItems(a1, a2);
        System.out.println("" + diff.count);
    }
}
