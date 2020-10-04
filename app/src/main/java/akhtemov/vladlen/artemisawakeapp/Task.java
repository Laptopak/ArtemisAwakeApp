package akhtemov.vladlen.artemisawakeapp;

public class Task {

    public String taskId;
    public String taskName;
    public String taskNote;
    public String startTime;
    public String startDate;
    public String endTime;
    public String endDate;

    // Don't delete
    public Task() {

    }

    public Task(String taskId, String taskName, String taskNote, String startTime, String startDate, String endTime, String endDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskNote = taskNote;
        this.startTime = startTime;
        this.startDate = startDate;
        this.endTime = endTime;
        this.endDate = endDate;
    }
}
