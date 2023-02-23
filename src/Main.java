import manager.*;
import tasks.*;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getTaskManager();
        HistoryManager historyManager = Managers.getHistoryManager();

        SingleTask st1 = new SingleTask("поднять 150 кг ","за три месяца ", TaskStatus.NEW);
        SingleTask st2 = new SingleTask("поднять 100 кг ","за один месяц ", TaskStatus.DONE);

        taskManager.createTask(st1);
        taskManager.getTaskById(st1.getId());

        taskManager.createTask(st2);
        taskManager.getTaskById(st2.getId());

        historyManager.addTask(st2.getId());
        historyManager.addTask(st1.getId());

        System.out.println(historyManager.getHistoryIds());

        Epic e = new Epic("Упаковать кошку ", "у кошки есть переноска ");
        Epic e1 = new Epic("кошка ", "это тест ");

        Subtask s1 = new Subtask("Пройти 5 км ","лучше взять беговые кроссовки ", e, TaskStatus.NEW);
        Subtask s3 = new Subtask("Взять деньги "," у жены ", e,TaskStatus.DONE);
        Subtask s5 = new Subtask("Переехать в другой город ","купить билеты ", e, TaskStatus.IN_PROGRESS);
        Subtask s6 = new Subtask("город "," билеты ", e1,TaskStatus.IN_PROGRESS);
        taskManager.createEpic(e);
        taskManager.createEpic(e1);
        taskManager.getEpicById(e.getId());
        taskManager.getEpicById(e1.getId());

        taskManager.createSubtask(e,s1);
        taskManager.createSubtask(e,s3);
        taskManager.createSubtask(e,s5);
        taskManager.createSubtask(e1,s6);

        historyManager.addTask(e.getId());
        historyManager.addTask(e1.getId());
        historyManager.addTask(e.getId());
        System.out.println(historyManager.getHistoryIds());
        historyManager.addTask(s6.getId());
        historyManager.addTask(s5.getId());
        historyManager.addTask(s1.getId());
        historyManager.addTask(s3.getId());
        historyManager.addTask(s3.getId());
        historyManager.addTask(s6.getId());

        System.out.println(historyManager.getHistoryIds());
    }
}