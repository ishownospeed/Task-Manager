import manager.TaskManager;
import tasks.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        SingleTask st1 = new SingleTask("поднять 150 кг ","за три месяца ", 5,TaskStatus.NEW);
        SingleTask st2 = new SingleTask("поднять 100 кг ","за один месяц ", 6,TaskStatus.DONE);
        taskManager.createTask(st1);
        taskManager.createTask(st2);


        Epic e = new Epic("Упаковать кошку ", "у кошки есть переноска ", 0);
        //Epic e1 = new Epic("кошка ", "это тест ", 5);

        Subtask s1 = new Subtask("Пройти 5 км ","лучше взять беговые кроссовки ",76, e,TaskStatus.NEW);
        Subtask s3 = new Subtask("Взять деньги "," у жены ",9, e,TaskStatus.DONE);
        Subtask s5 = new Subtask("Переехать в другой город ","купить билеты ",34, e,TaskStatus.IN_PROGRESS);

        taskManager.createSubtask(e, s1);
        taskManager.createSubtask(e, s3);
        taskManager.createSubtask(e, s5);

        //taskManager.createSubtask(e1, s5);

        taskManager.createEpic(e);
        //taskManager.createEpic(e1);


        //taskManager.deleteEpicById(0);

        taskManager.deleteSubtaskById(34);
        //taskManager.deleteAllSubtask(e);

        taskManager.printAllTask();

    }
}