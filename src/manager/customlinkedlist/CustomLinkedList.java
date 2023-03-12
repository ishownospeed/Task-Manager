package manager.customlinkedlist;

import java.util.LinkedList;
import java.util.List;

public class CustomLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;

    public Node<E> addLast(E element) {
        final Node<E> last = tail;
        final Node<E> newNode = new Node<>(last, element, null);
        tail = newNode;
        if (last == null)
            head = newNode;
        else
            last.next = newNode;
        return newNode;

    }

    public void removeNode(Node<E> nodeToRemove) {
        nodeToRemove.prev.next = nodeToRemove.next;
        nodeToRemove.next.prev = nodeToRemove.prev;
        nodeToRemove.next = null;
        nodeToRemove.prev = null;
    }

    public List<E> getTasks() {
        List<E> toReturn = new LinkedList<>();
        Node<E> current = head;
        while (current != tail.next) {
            toReturn.add(current.taskId);
            current = current.next;
        }
        return toReturn;
    }

}
