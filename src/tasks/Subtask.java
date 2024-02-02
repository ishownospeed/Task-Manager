package tasks;

import manager.file.TypeTasks;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(int id, TypeTasks type, String title, TaskStatus status, String description) {
        super(id, type, title, status, description);
        this.status = status;
    }

    public Subtask(int id, TypeTasks type, String title, TaskStatus status, String description, Epic epic) {
        super(id, type, title, status, description);
        this.epic = epic;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "title='" + this.getTitle() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + this.getId() +
                ", status=" + status +
                '}';
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Epic getEpic() {
        return epic;
    }
}
