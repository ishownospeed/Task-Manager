package tasks;

public class SingleTask extends Task {

    public SingleTask(String title, String description, int id, TaskStatus status) {
        super(title, description, id);
        this.status = status;
    }

    public SingleTask setStatus(TaskStatus status) {
        return new SingleTask(
                this.getTitle(),
                this.getDescription(),
                getId(),
                status
        );
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

}
