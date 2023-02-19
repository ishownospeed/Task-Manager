package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks;

    public Epic(String title, String description) {
        super(title, description);
        this.subtasks = new ArrayList<>();
    }

    @Override
    public TaskStatus getStatus() {
        if (subtasks.isEmpty()) {
            return TaskStatus.NEW;
        } else {
            for (Subtask task : subtasks) {
                if (TaskStatus.NEW.equals(task.getStatus())) {
                    if (TaskStatus.DONE.equals(status) || TaskStatus.IN_PROGRESS.equals(status)) {
                        status = TaskStatus.IN_PROGRESS;
                    } else {
                        status = TaskStatus.NEW;
                    }
                } else if (TaskStatus.DONE.equals(task.getStatus())) {
                    if (TaskStatus.NEW.equals(status) || TaskStatus.IN_PROGRESS.equals(status)) {
                        status = TaskStatus.IN_PROGRESS;
                    } else {
                        status = TaskStatus.DONE;
                    }
                } else {
                    status = TaskStatus.IN_PROGRESS;
                }
            }
        }
        return status;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + this.getTitle() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + this.getId() +
                ", status=" + status +
                '}';
    }
    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}