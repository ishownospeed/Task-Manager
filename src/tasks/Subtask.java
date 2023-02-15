package tasks;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(String title, String description, int id, Epic epic, TaskStatus status) {
        super(title, description, id);
        this.epic = epic;
        this.status = status;
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
