package manager;

import tasks.*;

import java.util.List;

public interface TaskManager {

    List<Integer> getHistory();

    void printAllTask();

    void deleteAllTasks();

    Task getTaskById(int id);

    void createTask(SingleTask singleTask);

    void updateTask(SingleTask singleTask);

    Task deleteTaskById(int id);

    void deleteAllEpicTasks();

    Epic getEpicById(int id);

    void createEpic(Epic epic);

    void getStatus(Epic epic);

    void updateEpic(Epic epic);

    Epic deleteEpicById(int id);

    void deleteAllSubtask();

    Subtask getSubtaskById(int id);

    void createSubtask(Epic epic, Subtask subtask);

    void updateSubtask(Subtask subtask);

    Subtask deleteSubtaskById(int id);

}