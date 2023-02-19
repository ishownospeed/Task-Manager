package manager;

import tasks.*;

import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, SingleTask> tasksById = new HashMap<>();
    private HashMap<Integer, Epic> epicsById = new HashMap<>();
    private HashMap<Integer, Subtask> subtasksById = new HashMap<>();

    private int nextId = 0;
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

    public void deleteAllTasks() {
        tasksById.clear();
    }

    public Task getTaskById(int id) {
        return tasksById.get(id);
    }

    public void createTask(SingleTask singleTask) {
        singleTask.setId(nextId++);
        tasksById.put(singleTask.getId(), singleTask);
    }

    public void updateTask(SingleTask singleTask) {
        tasksById.put(singleTask.getId(), singleTask);
    }

    public Task deleteTaskById(int id) {
        return tasksById.remove(id);
    }

    public void deleteAllEpicTasks() {
        subtasksById.clear();
        epicsById.clear();
    }

    public Epic getEpicById(int id) {
        return epicsById.get(id);
    }

    public void createEpic(Epic epic) {
        epic.setId(nextId++);
        epicsById.put(epic.getId(), epic);
    }

    public void getStatus(Epic epic) {
        epic.getStatus();
    }

    public void updateEpic(Epic epic) {
        epicsById.put(epic.getId(), epic);
    }

    public Epic deleteEpicById(int id) {
        Epic epic = epicsById.get(id);
        for (Subtask subtask : epic.getSubtasks()) {
            subtasksById.remove(subtask.getId());
        }
        return epicsById.remove(id);
    }

    public void deleteAllSubtask() {
        for (int task : subtasksById.keySet()) {
            subtasksById.get(task).getEpic().getSubtasks().remove(subtasksById.get(task));
            // можно ли выше строчку, как-нибудь сократить?
        }
        subtasksById.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasksById.get(id);
    }

    public void createSubtask(Epic epic, Subtask subtask) {
        subtask.setId(nextId++);
        epic.getSubtasks().add(subtask);
        subtasksById.put(subtask.getId(), subtask);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
    }

    public Subtask deleteSubtaskById(int id) {
        subtasksById.get(id).getEpic().getSubtasks().remove(subtasksById.get(id));
        return subtasksById.remove(id);
    }

}