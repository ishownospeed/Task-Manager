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
        tasksById.put(singleTask.getId(), singleTask);
        singleTask.setStatus(TaskStatus.NEW);
        singleTask.setId(nextId++);
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
        epicsById.put(epic.getId(), epic);
        epic.setId(nextId++);
    }

    public void getStatus(Epic epic) {
        epic.getStatus();
    }

    public void updateEpic(Epic epic) {
        epicsById.put(epic.getId(), epic);
    }

    public Epic deleteEpicById(int id) {
        for (int task : epicsById.keySet()) {
            epicsById.get(id).getSubtasks().remove(task);
            subtasksById.remove(epicsById.get(id).getId());
        }

        /*for (Subtask task : subtasksById.values()) {
            epicsById.get(id).getSubtasks().remove(task);
        }*/
        // задачи сабтасков из списка эпика, я удалил, а вот из коллекции сабтасков я не понимаю как (((
        // если в цикле удалять, цикл с ошибкой выходит
        return epicsById.remove(id);
    }

    public void deleteAllSubtask(Epic epic) {
        for (Subtask task : subtasksById.values()) {
            epic.getSubtasks().remove(task);
        }
        subtasksById.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasksById.get(id);
    }

    public void createSubtask(Epic epic, Subtask subtask) {
        epic.getSubtasks().add(subtask);
        subtasksById.put(subtask.getId(),subtask);
        subtask.setId(nextId++);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
    }

    public Subtask deleteSubtaskById(int id) {
        for (Subtask task : subtasksById.values()) {
            subtasksById.get(id).getEpic().getSubtasks().remove(task);
        }
        return subtasksById.remove(id);
    }

}