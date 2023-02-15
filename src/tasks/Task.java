package tasks;

public abstract class Task {
    private String title;
    private String description;
    private int id;
    protected TaskStatus status;

    public Task(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
    }

    public abstract TaskStatus getStatus();

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}