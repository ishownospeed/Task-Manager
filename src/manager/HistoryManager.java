package manager;

import java.util.List;

public interface HistoryManager {

    void addTask(int taskId);

    void remove(int id);

    List<Integer> getHistoryIds();

}
