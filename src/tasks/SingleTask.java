package tasks;

public class SingleTask extends Task {

    public SingleTask(String title, String description, TaskStatus status) {
        super(title, description);
        this.status = status;
    }

    public SingleTask setStatus(TaskStatus status) {
        return new SingleTask(
                this.getTitle(),
                this.getDescription(),
                status
        );
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
