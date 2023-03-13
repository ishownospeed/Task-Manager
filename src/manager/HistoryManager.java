package manager;

import java.util.List;

public interface HistoryManager {

    void add(int taskId);

    void remove(int id);

    List<Integer> getHistory();

}
