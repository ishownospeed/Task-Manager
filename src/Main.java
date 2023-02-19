import manager.TaskManager;
import tasks.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        SingleTask st1 = new SingleTask("поднять 150 кг ","за три месяца ", TaskStatus.NEW);
        SingleTask st2 = new SingleTask("поднять 100 кг ","за один месяц ", TaskStatus.DONE);
        taskManager.createTask(st1);
        taskManager.createTask(st2);


        Epic e = new Epic("Упаковать кошку ", "у кошки есть переноска ");
        Epic e1 = new Epic("кошка ", "это тест ");

        Subtask s1 = new Subtask("Пройти 5 км ","лучше взять беговые кроссовки ", e, TaskStatus.NEW);
        Subtask s3 = new Subtask("Взять деньги "," у жены ", e,TaskStatus.DONE);
        Subtask s5 = new Subtask("Переехать в другой город ","купить билеты ", e, TaskStatus.IN_PROGRESS);
        Subtask s6 = new Subtask("город "," билеты ", e1,TaskStatus.IN_PROGRESS);

        taskManager.createSubtask(e, s1);
        taskManager.createSubtask(e, s3);
        taskManager.createSubtask(e, s5);

        taskManager.createSubtask(e1, s6);

        taskManager.createEpic(e);
        taskManager.createEpic(e1);


        taskManager.deleteEpicById(e.getId());

        //taskManager.deleteSubtaskById(s3.getId());
        //taskManager.deleteAllSubtask();


        taskManager.printAllTask();

    }
}