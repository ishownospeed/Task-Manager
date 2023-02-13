package manager;

import tasks.*;

import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, SingleTask> tasksById = new HashMap<>();
    private HashMap<Integer, Epic> epicsById = new HashMap<>();
    private HashMap<Integer, Subtask> subtasksById = new HashMap<>();

    private Integer nextId = 0;

    public void printAllTask() {
        if (!tasksById.isEmpty()) {
            System.out.println("Список задач: ");
            for (SingleTask tasks : tasksById.values()) {
                System.out.print("Задача: " + tasks.getTitle());
                System.out.print("Описание: " + tasks.getDescription());
                System.out.println("Статус: " + tasks.getStatus());
            }
        } else {
            System.out.println("Список задач пуст");
        }
        if (!epicsById.isEmpty()) {
            System.out.println("Список главных задач: ");
            for (Epic tasks : epicsById.values()) {
                System.out.print("Задача: " + tasks.getTitle());
                System.out.print("Описание: " + tasks.getDescription());
                System.out.println("Статус: " + tasks.getStatus());
            }
        } else {
            System.out.println("Список главных задач пуст");
        }
        if (!subtasksById.isEmpty()) {
            System.out.println("Список подзадач: ");
            for (Subtask tasks : subtasksById.values()) {
                System.out.print("Задача: " + tasks.getTitle());
                System.out.print("Описание: " + tasks.getDescription());
                System.out.println("Статус: " + tasks.getStatus());
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
        singleTask.setStatus(TaskStatus.NEW);
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

    public Epic deleteEpicById(Epic epic, int id) {
        epic.getSubtasks().clear();
        subtasksById.clear();
        return epicsById.remove(id);
    }

    public void deleteAllSubtask(Epic epic, int id) {
        if (epicsById.containsKey(id)) {
            for (int i = epic.getSubtasks().size(); i > 0; i--) {
                epic.getSubtasks().remove(i-1);
                subtasksById.remove(i+1);
            }
        }
    }

    public Subtask getSubtaskById(int id) {
        return subtasksById.get(id);
    }

    public void createSubtask(Epic epic, Subtask subtask) {
        subtask.setId(nextId++);
        epic.getSubtasks().add(subtask);
        subtasksById.put(subtask.getId(),subtask);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
    }

    public Subtask deleteSubtaskById(Epic epic, int id) {
        epic.getSubtasks().remove(id);
        return subtasksById.remove(id);
    }

}