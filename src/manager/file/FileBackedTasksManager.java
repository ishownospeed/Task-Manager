package manager.file;

import manager.*;
import tasks.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    public static void main(String[] args) throws IOException {
        FileBackedTasksManager tm = new FileBackedTasksManager(Paths.get("resources/file.csv").toFile());

        SingleTask tk1 = new SingleTask(1, TypeTasks.SINGLETASK, "sdfs",
                TaskStatus.NEW, "dsfs");
        tm.createTask(tk1);

        File file = new File("resources/file.csv");
        loadFromFile(file);

    }


    private final Path path;

    public FileBackedTasksManager(File file) {
        this.path = file.toPath();
    }

    /*public static FileBackedTasksManager loadFromFile(Path file) {
        String[] reader = Files.readAllLines(Paths.get(file));

    }*/

 /*public static FileBackedTasksManager loadFromFile(File file) {

        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager("C:\1\file2.csv");
        String fileContent = Files.readString(Path.of(path));
        String[] lines = fileContent.split("\r?\n");
        for (String line : lines) {
            if (!line.isBlank()) {
                Task task = fromString(line);
                if (task instanceof Epic){
                    fileBackedTasksManager.epics.put(task.getId(), (Epic) task);
                }
                if (task instanceof Subtask) {
                    fileBackedTasksManager.subtasks.put(task.getId(), (Subtask) task);
                }
                if(task instanceof Task){
                    fileBackedTasksManager.tasks.put(task.getId(), task);
                }
            }
        }
        return fileBackedTasksManager;
    }*/


    public void getTaskSuper(int id) {
        super.getTaskById(id);
    }
    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        if (Files.exists((file.toPath()))) {
            try (Reader reader = new FileReader(file.toPath().toString(), StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    if (str != null && str.isEmpty()) {
                        String history = bufferedReader.readLine();
                        List<Integer> tasks = fileBackedTasksManager.getHistory();
                        for (Integer task : tasks) {
                            if (super.tasksById.containsKey(task)) {

                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileBackedTasksManager;
    }
    /*public seBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);
        if (Files.exists(file.toPath())) {
            try (Reader reader = new FileReader(file.toPath().toString(), StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                bufferedReader.readLine();
                while (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    if (str != null && str.isEmpty()) {
                        String history = bufferedReader.readLine();
                        List<Integer> tasks = fromString(history);
                        Map<Integer, Task> taskMap = fileBackedTasksManager.getTasksMap();
                        Map<Integer, SubTask> subTaskMap = fileBackedTasksManager.getSubTasksMap();
                        if (tasks != null) {
                            for (Integer taskId : tasks) {
                                if (taskMap.containsKey(taskId)) {
                                    fileBackedTasksManager.getTaskSuper(taskId);
                                } else if (subTaskMap.containsKey(taskId)) {
                                    fileBackedTasksManager.getSubTaskSuper(taskId);
                                } else {
                                    fileBackedTasksManager.getEpicSuper(taskId);
                                }
                            }
                        }
                    } else {
                        fileBackedTasksManager.addTaskType(str);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Files.createFile(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileBackedTasksManager;
    }*/


    public void save() {
        try (BufferedWriter writer = getWriter()) {
            saveTasksToFile(writer);
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка выполнения программы");
        }
    }

    private BufferedWriter getWriter() throws IOException {
        return new BufferedWriter(
                new FileWriter(path.toFile())
        );
    }

    private void saveTasksToFile(BufferedWriter writer) throws IOException {
        writer.write("id,type,name,status,description,epic");
        writer.write(System.lineSeparator());
        writer.write("Hello");
        writer.write(" Danil");

        /*for (SingleTask task : tasksById.values()) {
            writer.write(task.getId());
            writer.write(",");
            writer.write(task.getTitle());
            writer.write(",");
        }*/

        /*File save = new File("file");
        String graphsTasks = toString(getTaskById(1));
        String qwe = historyToString(historyManager);
        String graphs = "id,type,name,status,description,epic";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(save));
            BufferedReader br = new BufferedReader(new FileReader(save));
            if (br.readLine() == null) {
                bw.write(graphs);
                bw.write("\n");
            }
            bw.write( graphsTasks+"\n"+ qwe);
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ManagerSaveException("Ошибка в сохранении");
        }*/
    }


    public static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for (Integer task : manager.getHistory()) {
            sb.append(task).append(",");
        }
        return sb.toString();
    }

    public static List<Integer> historyFromString(String value) {
        List<Integer> history = new ArrayList<>();
        String[] content = value.split(",");
        for (String s : content) {
            int id = Integer.parseInt(s);
            history.add(id);
        }
        return history;
    }

    public String toString(Task task) {
        StringBuilder sb = new StringBuilder(task.getId() + "," +
                task.getType() + "," +
                task.getTitle() + "," +
                task.getStatus() + "," +
                task.getDescription() + ","
        );
        if (task.getType().equals(TypeTasks.SUBTASK)) {
            sb.append(((Subtask) task).getEpic());
        }
        return sb.toString();
    }

    public Task fromString(String value) {
        String[] content = value.split(",");
        Task task = null;
        for (String e : content) {
            if (TypeTasks.valueOf(content[1]) == TypeTasks.SINGLETASK) {
                task = new SingleTask(Integer.parseInt(content[0]), TypeTasks.valueOf(content[1]),
                        content[2], TaskStatus.valueOf(content[3]), content[4]);
            } else if (TypeTasks.valueOf(content[1]) == TypeTasks.EPIC) {
                task = new Epic(Integer.parseInt(content[0]), TypeTasks.valueOf(content[1]),
                        content[2], TaskStatus.valueOf(content[3]), content[4]);
            } else {
                task = new Subtask(Integer.parseInt(content[0]), TypeTasks.valueOf(content[1]),
                        content[2], TaskStatus.valueOf(content[3]), content[4]);
            }
        }
        return task;
    }

    @Override
    public void createTask(SingleTask singleTask) {
        super.createTask(singleTask);
        save();
    }

}
