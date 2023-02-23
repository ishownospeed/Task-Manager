package manager;

import java.util.List;

public interface HistoryManager {

    void addTask(int taskId);

    List<Integer> getHistoryIds();

}
