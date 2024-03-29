package manager.customlinkedlist;

import java.util.LinkedList;
import java.util.List;

public class CustomLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;

    public Node<E> addLast(E element) {
        final Node<E> newNode = new Node<>(tail, element, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        return newNode;
    }

    public void removeNode(Node<E> nodeToRemove) {
        if (nodeToRemove == null) return;
        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
            if (nodeToRemove.next == null) {
                tail = nodeToRemove.prev;
            } else {
                nodeToRemove.next.prev = nodeToRemove.prev;
            }
        } else {
            head = nodeToRemove.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
        }
    }

    public List<E> getTasks() {
        List<E> toReturn = new LinkedList<>();
        Node<E> current = head;
        while (current != null) {
            toReturn.add(current.taskId);
            current = current.next;
        }
        return toReturn;
    }

}