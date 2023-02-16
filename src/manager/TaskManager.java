package manager;

import com.sun.jdi.Value;
import tasks.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskManager {
    private HashMap<Integer, SingleTask> tasksById = new HashMap<>();
    private HashMap<Integer, Epic> epicsById = new HashMap<>();
    private ConcurrentHashMap<Integer, Subtask> subtasksById = new ConcurrentHashMap<>();

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
        /* Прочитал я тут документацию по хэшмапам, а именно про удаление и вот что нашел)))
         * про параллельные хэшмапы, с помощью их результат как и задумывался
         * можно мне объяснить как можно по другому сделать, я правда не понимаю как можно сделать иначе?
         *
         * */
        for (Map.Entry<Integer, Subtask> task : subtasksById.entrySet()) {
            if (task.getKey() != null && task.getValue() == task.getValue()) {
                    subtasksById.remove(task.getKey());
            }
            for (int epic : epicsById.keySet()) {
                if (epicsById.values().equals(epicsById.get(id))) {
                    epicsById.get(id).getSubtasks().remove(epic);
                }
            }
        }

        return epicsById.remove(id);
    }

    public void deleteAllSubtask(Epic epic) {
        // это метод я понял так. У конкретного эпика удаляем все его задачи, но сам эпик оставляем.
        // а вот метод выше на 93 строке deleteEpicById(int id), мы удаляем и эпик и все его задачи
        // если в этом методе вызвать просто "subtasksById.clear();" получается все его задачи останутся в эпиках
        // в коллекции ArrayList<Subtask> subtasks; они же оттуда удалиться не могут? или могут)))?

        for (Map.Entry<Integer, Subtask> task : subtasksById.entrySet()) {
            if (task.getKey() != null && task.getValue() == task.getValue()) {
                subtasksById.remove(task.getKey());
            }
            for (Subtask subtask : subtasksById.values()) {
                epic.getSubtasks().remove(subtask);

            }
        }
    }

    public Subtask getSubtaskById(int id) {
        return subtasksById.get(id);
    }

    public void createSubtask(Epic epic, Subtask subtask) {
        epic.getSubtasks().add(subtask);
        subtasksById.put(subtask.getId(), subtask);
        subtask.setId(nextId++);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksById.put(subtask.getId(), subtask);
    }

    public Subtask deleteSubtaskById(int id) {
        for (Subtask task : subtasksById.values()) {
            if (subtasksById.get(id).equals(task)) {
                subtasksById.get(id).getEpic().getSubtasks().remove(task);
            }
        }
        return subtasksById.remove(id);
    }

}