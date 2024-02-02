package tasks;

import manager.file.TypeTasks;

public class SingleTask extends Task {

    public SingleTask(int id, TypeTasks type, String title, TaskStatus status, String description) {
        super(id, type, title, status, description);
        this.status = status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SingleTask{" +
                "title='" + this.getTitle() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", id=" + this.getId() +
                ", status=" + status +
                '}';
    }

}
