package manager.customlinkedlist;

public class Node<E> {
    E taskId;
    Node<E> prev;
    Node<E> next;

    public Node(Node<E> prev, E taskId, Node<E> next) {
        this.taskId = taskId;
        this.prev = prev;
        this.next = next;
    }
}
