package manager;

import tasks.*;

import java.util.ArrayList;
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
                System.out.print("Задача: " + tasks.getId() + tasks.getTitle());
                System.out.print("Описание: " + tasks.getDescription());
                System.out.println("Статус: " + tasks.getStatus());
            }
        } else {
            System.out.println("Список задач пуст");
        }
        if (!epicsById.isEmpty()) {
            System.out.println("Список главных задач: ");
            for (Epic tasks : epicsById.values()) {
                System.out.print("Задача: " + tasks.getId() + tasks.getTitle());
                System.out.print("Описание: " + tasks.getDescription());
                System.out.println("Статус: " + tasks.getStatus());
            }
        } else {
            System.out.println("Список главных задач пуст");
        }
        if (!subtasksById.isEmpty()) {
            System.out.println("Список подзадач: ");
            for (Subtask tasks : subtasksById.values()) {
                System.out.print("Задача: " + tasks.getId() + tasks.getTitle());
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
        singleTask.setStatus(TaskStatus.NEW);
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
        epicsById.clear();
    }

    public Epic getEpicById(int id) {
        return epicsById.get(id);
    }

    public void createEpic(Epic epic, Subtask subtask) {
        epic.setStatus(subtask.getStatus());
        epic.setId(nextId++);
        epicsById.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic) {
        epicsById.put(epic.getId(), epic);
    }

    public Epic deleteEpicById(int id) {
        return epicsById.remove(id);
    }

    public void deleteAllSubtask() {
        subtasksById.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasksById.get(id);
    }

    public void createSubtask(Epic epic, Subtask subtask ) {
        subtask.setStatus(subtask.getStatus());
        epic.getSubtaskIds().add(subtask);
        subtasksById.put(epic.getId(),subtask);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
    }

    public Subtask deleteSubtaskById(int id) {
        return subtasksById.remove(id);
    }

}