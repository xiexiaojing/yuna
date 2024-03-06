package com.brmayi.yuna.solution;

public class MyLinkedList {
    class LinkedNode {
        LinkedNode next;
        int val;
        LinkedNode(int val) {
            this.val = val;
        }
    }
    LinkedNode head;
    int size;
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {
        if(index>=size ||index<0) {
            return -1;
        }
        int i = 0;
        LinkedNode p = head;
        while(i<index) {
            p = p.next;
        }
        return p.val;
    }

    public void addAtHead(int val) {
        LinkedNode newHead = new LinkedNode(val);
        newHead.next = head;
        ++size;
        head = newHead;
    }

    public void addAtTail(int val) {
        ++size;
        LinkedNode tail = new LinkedNode(val);
        if(head==null) {
            head = tail;
            return;
        }
        LinkedNode p = head;
        while(p.next != null) {
            p = p.next;
        }
        p.next = tail;
    }

    public void addAtIndex(int index, int val) {
        if(index<0) {
            return;
        }
        if(index>size) {
            return;
        }
        if(index==size) {
            addAtTail(val);
            return;
        }
        if(index==0) {
            addAtHead(val);
            return;
        }
        LinkedNode p = head;
        int i = 0;
        LinkedNode node = new LinkedNode(val);
        while(i<index) {
            p = p.next;
            i++;
        }
        LinkedNode next = p.next;
        p.next = node;
        node.next = next;
        ++size;
    }

    public void deleteAtIndex(int index) {
        if(index>=size || index<0) {
            return;
        }
        --size;
        int i = 0;
        LinkedNode dummy = new LinkedNode(-1);
        dummy.next = head;
        LinkedNode p = dummy;
        while(i<index) {
            p = p.next;
            i++;
        }
        p.next = p.next.next;
        head = dummy.next;
    }

    public static void main(String[] args) {
        MyLinkedList m = new MyLinkedList();
        m.addAtHead(1);
        m.addAtTail(3);
        m.addAtIndex(1,2);
        m.get(1);
    }
}
