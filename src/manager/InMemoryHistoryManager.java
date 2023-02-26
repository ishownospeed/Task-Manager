package manager;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Integer> ids;
    private final static int MAX_SIZE_LIST = 10;

    public InMemoryHistoryManager() {
        this.ids = new LinkedList<>();
    }

    @Override
    public void addTask(int taskId) {
        if (ids.size() >= MAX_SIZE_LIST) {
            ids.remove(0);
        }
        ids.add(taskId);
    }

    @Override
    public List<Integer> getHistoryIds() {
        return new LinkedList<>(ids);
    }

}
