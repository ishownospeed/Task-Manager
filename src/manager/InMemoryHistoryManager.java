package manager;

import manager.customlinkedlist.CustomLinkedList;
import manager.customlinkedlist.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList<Integer> ids;
    private final Map<Integer, Node<Integer>> mapIds;

    public InMemoryHistoryManager() {
        this.ids = new CustomLinkedList<>();
        this.mapIds = new HashMap<>();
    }

    @Override
    public void add(int taskId) {
        if (!mapIds.containsKey(taskId)){
            Node<Integer> node1 = ids.addLast(taskId);
            ids.addLast(taskId);
            ids.removeNode(node1);
            mapIds.put(taskId, node1);
        }
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
