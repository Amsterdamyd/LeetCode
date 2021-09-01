package com.yangdi.leetcode.linkedlist;

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 * obj.deleteAtIndex(index);
 * ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead",
 * "addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
 * [[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]
 */
public class CreateMyLinkedList {
    public static void main(String[] args) {
        MyDoublyLinkedList obj = new MyDoublyLinkedList();
        obj.addAtHead(2);
        obj.deleteAtIndex(1);
        obj.addAtHead(2);
        obj.addAtHead(7);
        obj.addAtHead(3);
        obj.addAtHead(2);
        obj.addAtHead(5);
        obj.addAtTail(5);
        System.out.println(obj.get(5));
        obj.deleteAtIndex(6);
        obj.deleteAtIndex(4);
        obj.addAtIndex(2, 4);
        System.out.println(obj.get(4));
        System.out.println(obj.getHead());
        System.out.println(obj.getTail());
    }
}
