package org.example;

public class TaskItem {
    private int id;
    private String title;
    private Progress progress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public TaskItem(int id, String title, Progress progress) {
        this.id = id;
        this.title = title;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", progress=" + progress +
                '}';
    }
}


enum Progress {DONE, IN_PROGRESS, NOT_DONE}