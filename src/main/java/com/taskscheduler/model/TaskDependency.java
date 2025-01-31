package com.taskscheduler.model;

public class TaskDependency {
    private Task task;
    private Task dependsOn; // Task that should be done before this one

    public TaskDependency() {
    }

    public TaskDependency(Task task, Task dependsOn) {
        this.task = task;
        this.dependsOn = dependsOn;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Task dependsOn) {
        this.dependsOn = dependsOn;
    }
}
