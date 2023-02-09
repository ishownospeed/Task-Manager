import manager.TaskManager;
import tasks.*;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        SingleTask st1 = new SingleTask(" поднять 150 кг ","за три месяца ", 5,TaskStatus.NEW);
        SingleTask st2 = new SingleTask(" поднять 100 кг ","за один месяц ", 5,TaskStatus.DONE);
        taskManager.createTask(st1);
        taskManager.createTask(st2);


        Epic e = new Epic(" Упаковать кошку ", "у кошки есть переноска ", 0);

        Subtask s3 = new Subtask(" Пройти 5 км ","лучше взять беговые кроссовки ",9, e,TaskStatus.IN_PROGRESS);

        taskManager.createEpic(e,s3);
        taskManager.createSubtask(e, s3);

        taskManager.printAllTask();

    }
}