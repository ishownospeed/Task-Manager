package manager;

import manager.customlinkedlist.CustomLinkedList;
import manager.customlinkedlist.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList<Integer> ids = new CustomLinkedList<>();
    private final Map<Integer, Node<Integer>> mapIds = new HashMap<>();

    @Override
    public void add(int taskId) {
        if (mapIds.containsKey(taskId)) {
            ids.removeNode(mapIds.get(taskId));
        }
        Node<Integer> node = ids.addLast(taskId);
        mapIds.put(taskId, node);
    }

    @Override
    public void remove(int id) {
        if (mapIds.containsKey(id)) {
            ids.removeNode(mapIds.get(id));
            mapIds.remove(id);
        }
    }

    @Override
    public List<Integer> getHistory() {
        return ids.getTasks();
    }

}