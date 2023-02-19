package tasks;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(String title, String description, Epic epic, TaskStatus status) {
        super(title, description);
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
