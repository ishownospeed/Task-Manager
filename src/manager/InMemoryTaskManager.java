package manager;

import tasks.*;

import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    protected final HashMap<Integer, SingleTask> tasksById = new HashMap<>();
    protected final HashMap<Integer, Epic> epicsById = new HashMap<>();

    protected final HashMap<Integer, Subtask> subtasksById = new HashMap<>();

    private int nextId = 0;
    protected HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    public InMemoryTaskManager() {}

    @Override
    public List<Integer> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void printAllTask() {
        if (!tasksById.isEmpty()) {
            System.out.println("Список задач: ");
            for (SingleTask task : tasksById.values()) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("Список задач пуст");
        }
        if (!epicsById.isEmpty()) {
            System.out.println("Список главных задач: ");
            for (Epic task : epicsById.values()) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("Список главных задач пуст");
        }
        if (!subtasksById.isEmpty()) {
            System.out.println("Список подзадач: ");
            for (Subtask task : subtasksById.values()) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("Список подзадач пуст");
        }
    }

    @Override
    public void deleteAllTasks() {
        for (int task : tasksById.keySet()) {
            historyManager.remove(task);
        }
        tasksById.clear();
    }

    @Override
    public Task getTaskById(int id) {
        if (tasksById.containsKey(id)) {
            historyManager.add(id);
        }
        return tasksById.get(id);
    }

    @Override
    public void createTask(SingleTask singleTask) {
        singleTask.setId(nextId++);
        tasksById.put(singleTask.getId(), singleTask);
    }

    @Override
    public void updateTask(SingleTask singleTask) {
        tasksById.put(singleTask.getId(), singleTask);
    }

    @Override
    public Task deleteTaskById(int id) {
        historyManager.remove(id);
        return tasksById.remove(id);
    }

    @Override
    public void deleteAllEpicTasks() {
        subtasksById.clear();
        epicsById.clear();
    }

    @Override
    public Epic getEpicById(int id) {
        if (epicsById.containsKey(id)) {
            historyManager.add(id);
        }
        return epicsById.get(id);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epicsById.put(epic.getId(), epic);
    }

    @Override
    public void getStatus(Epic epic) {
        epic.getStatus();
    }

    @Override
    public void updateEpic(Epic epic) {
        epicsById.put(epic.getId(), epic);
    }

    @Override
    public Epic deleteEpicById(int id) {
        historyManager.remove(id);
        Epic epic = epicsById.get(id);
        for (Subtask subtask : epic.getSubtasks()) {
            subtasksById.remove(subtask.getId());
        }
        return epicsById.remove(id);
    }

    @Override
    public void deleteAllSubtask() {
        for (int task : subtasksById.keySet()) {
            historyManager.remove(task);
            subtasksById.get(task).getEpic().getSubtasks().remove(subtasksById.get(task));
        }
        subtasksById.clear();
    }

    @Override
    public Subtask getSubtaskById(int id) {
        if (subtasksById.containsKey(id)) {
            historyManager.add(id);
        }
        return subtasksById.get(id);
    }

    @Override
    public void createSubtask(Epic epic, Subtask subtask) {
        subtask.setId(nextId++);
        epic.getSubtasks().add(subtask);
        subtasksById.put(subtask.getId(), subtask);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
    }

    @Override
    public Subtask deleteSubtaskById(int id) {
        historyManager.remove(id);
        subtasksById.get(id).getEpic().getSubtasks().remove(subtasksById.get(id));
        return subtasksById.remove(id);
    }

}