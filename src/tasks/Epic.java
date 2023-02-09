package tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private TaskStatus status;
    private ArrayList<Subtask> subtaskIds;

    public Epic(String title, String description, int id) {
        super(title, description, id);
        this.subtaskIds = new ArrayList<>();
    }

    @Override
    public TaskStatus getStatus() {
        if (TaskStatus.NEW.equals(status) || subtaskIds.isEmpty()) {
            return TaskStatus.NEW;
        } else if (TaskStatus.DONE.equals(status)) {
            return TaskStatus.DONE;
        } else {
            return TaskStatus.IN_PROGRESS;
        }
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public ArrayList<Subtask> getSubtaskIds() {
        return subtaskIds;
    }

    public void setSubtaskIds(ArrayList<Subtask> subtaskIds) {
        this.subtaskIds = subtaskIds;
    }
}