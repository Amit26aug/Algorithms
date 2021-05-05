package com.august.gfg450.linkedlist1_reveselist;

public class ReverseLinkedList {

  static class Node {
    int data;
    Node next;
    Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(4);
    head.next.next.next.next = new Node(5);

    Node reverse = reverseList(head);
    while (reverse != null) {
      System.out.println(reverse.data);
      reverse = reverse.next;
    }
  }

  static Node reverseList(Node head) {
    return reverse(head, null);
  }

  static Node reverse(Node cur, Node pre) {
    if (cur == null) {
      return pre;
    }
    Node temp = reverse(cur.next, cur);
    cur.next = pre;
    return temp;
  }

}
