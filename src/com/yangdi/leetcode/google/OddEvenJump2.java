package com.yangdi.leetcode.google;

import java.util.*;

public class OddEvenJump2 {

    public int oddEvenJumps(int[] A) {
        List<JumpNode> list1 = new ArrayList<>();
        List<JumpNode> list2 = new ArrayList<>();

        for (int value : A) {
            JumpNode node = new JumpNode(value);
            list1.add(node);
            list2.add(node);
        }
        Collections.sort(list2);

        // Calculate every node's odd node and even node
        for (JumpNode node : list1) {
            for (int i = 0; i < list2.size(); i++) {
                if (node == list2.get(i)) {
                    if (i - 1 >= 0) {
                        node.evenNode = list2.get(i - 1);
                    }

                    if (i + 1 <= list2.size() -1) {
                        node.oddNode = list2.get(i + 1);
                    }

                    list2.remove(i);
                    break;
                }
            }
        }

        for (int i = list1.size() - 1; i >= 0; i--) {
            JumpNode node = list1.get(i);

            if (i == list1.size() - 1) {
                node.oddJumpResult = true;
                node.evenJumpResult = true;
            } else {
                JumpNode oddNode = node.oddNode;
                JumpNode evenNode = node.evenNode;

                // odd jump
                if (oddNode != null && oddNode.evenJumpResult) {
                    node.oddJumpResult = true;
                } else {
                    node.oddJumpResult = false;
                }

                // even jump
                if (evenNode != null && evenNode.oddJumpResult) {
                    node.evenJumpResult = true;
                } else {
                    node.evenJumpResult = false;
                }
            }
        }

        int goodNumber = 0;
        for (JumpNode node : list1) {
            if (node.oddJumpResult) {
                goodNumber++;
            }
        }

        return goodNumber;
    }

    public static void main(String[] args) {
        //int[] A = {10,13,12,14,15}; //2
        //int[] A = {2,3,1,1,4}; //3
        int[] A = {5,1,3,4,2}; //3
        OddEvenJump jump = new OddEvenJump();
        System.out.println(jump.oddEvenJumps(A) + "");
    }
}
