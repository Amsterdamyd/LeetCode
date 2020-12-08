package com.yangdi.leetcode.recursion;

import com.yangdi.leetcode.binarytree.PreorderTraversal;
import com.yangdi.leetcode.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UniqueBST {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();

        if (n == 0) {
            return list;
        }

        if (n == 1) {
            TreeNode node = new TreeNode(1);
            list.add(node);
            return list;
        }

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> leftNumbers = creatSubList(numbers, 0, i - 1);
            List<Integer> rightNumbers = creatSubList(numbers, i + 1, numbers.size() - 1);

            List<TreeNode> itemList = helper(numbers.get(i), leftNumbers, rightNumbers);
            list.addAll(itemList);
        }

        return list;
    }

    List<TreeNode> helper(int rootNumber, List<Integer> leftNumbers, List<Integer> rightNumbers) {
        List<TreeNode> trees = new ArrayList<>();
        if (leftNumbers.isEmpty() && rightNumbers.isEmpty()) {
            TreeNode rootNode = new TreeNode(rootNumber);
            trees.add(rootNode);
            return trees;
        }

        List<TreeNode> leftNodes = new ArrayList<>();
        List<TreeNode> rightNodes = new ArrayList<>();

        for (int i = 0; i < leftNumbers.size(); i++) {
            List<Integer> leftSubNumbers = creatSubList(leftNumbers, 0, i - 1);
            List<Integer> rightSubNumbers = creatSubList(leftNumbers, i + 1, leftNumbers.size() - 1);
            List<TreeNode> itemList = helper(leftNumbers.get(i), leftSubNumbers, rightSubNumbers);
            leftNodes.addAll(itemList);
        }
        for (int i = 0; i < rightNumbers.size(); i++) {
            List<Integer> leftSubNumbers = creatSubList(rightNumbers, 0, i - 1);
            List<Integer> rightSubNumbers = creatSubList(rightNumbers, i + 1, rightNumbers.size() - 1);
            List<TreeNode> itemList = helper(rightNumbers.get(i), leftSubNumbers, rightSubNumbers);
            rightNodes.addAll(itemList);
        }

        if (leftNodes.isEmpty() || rightNodes.isEmpty()) {
            List<TreeNode> subNodes = leftNodes.isEmpty() ? rightNodes : leftNodes;
            for (int i = 0; i < subNodes.size(); i++) {
                TreeNode rootNode = new TreeNode(rootNumber);
                if (leftNodes.isEmpty()) {
                    rootNode.right = subNodes.get(i);
                } else {
                    rootNode.left = subNodes.get(i);
                }

                trees.add(rootNode);
            }
        } else {
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode rootNode = new TreeNode(rootNumber);
                    rootNode.left = leftNode;
                    rootNode.right = rightNode;

                    trees.add(rootNode);
                }
            }
        }

        return trees;
    }

    List<Integer> creatSubList(List<Integer> numbers, int begin, int end) {
        List<Integer> subList = new ArrayList<>();

        if (begin > end) {
            return subList;
        }

        for (int i = begin; i <= end; i++) {
            subList.add(numbers.get(i));
        }

        return subList;
    }

    /**
     * from LeetCode
     * concise and elegant
     */
    public List<TreeNode> generateTrees2(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
    }

    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
        if (start > end) {
            all_trees.add(null); // Linked list can add a null element.
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is chosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is chosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode leftNode : left_trees) {
                for (TreeNode rightNode : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = leftNode;
                    current_tree.right = rightNode;

                    all_trees.add(current_tree);
                }
            }
        }

        return all_trees;
    }



    public static void main(String[] args) {
        UniqueBST unique = new UniqueBST();
        List<TreeNode> list = unique.generateTrees2(4);
        System.out.println("size = "+list.size());

        for (TreeNode rootNode : list) {
            PreorderTraversal traversal = new PreorderTraversal();
            List<Integer> items = traversal.preorderTraversal(rootNode);
            System.out.println(items.toString());
        }
    }
}
