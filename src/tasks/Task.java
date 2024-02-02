package tasks;

import manager.file.TypeTasks;

public abstract class Task {
    private int id;
    private final TypeTasks type;
    private final String title;
    protected TaskStatus status;
    private final String description;

    public Task(int id, TypeTasks type, String title, TaskStatus status, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.status = status;
        this.description = description;
    }

    public abstract TaskStatus getStatus();

    @Override
    public abstract String toString();

    public int getId() {
        return id;
    }

    public TypeTasks getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

}