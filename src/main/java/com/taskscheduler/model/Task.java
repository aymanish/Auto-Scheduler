package com.taskscheduler.model;

import java.util.List;
import java.util.Objects;

public class Task {
    private String name;
    private String day;
    private int duration; // in hours
    private String availableFrom; // available from a specific time (e.g., "9am")
    private boolean isMandatory; // true if it's mandatory, false if optional
    private List<Task> dependencies; // tasks that must be completed before this one

    public Task() {
    }

    public Task(String name, String day, int duration, String availableFrom, boolean isMandatory, List<Task> dependencies) {
        this.name = name;
        this.day = day;
        this.duration = duration;
        this.availableFrom = availableFrom;
        this.isMandatory = isMandatory;
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public List<Task> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Task> dependencies) {
        this.dependencies = dependencies;
    }

    // Override equals and hashCode (using name as a unique identifier for simplicity)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getName(), task.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
