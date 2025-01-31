package com.taskscheduler.service;

import com.taskscheduler.model.Project;
import com.taskscheduler.model.Task;
import com.taskscheduler.util.TopologicalSort;

import java.util.*;

public class TaskSchedulerService {

    public List<Task> generateSchedule(Project project, int availableHoursPerDay) {
        List<Task> tasks = project.getTasks();

        // Sort tasks topologically based on their dependencies
        List<Task> sortedTasks = TopologicalSort.sortTasks(tasks);

        List<Task> scheduledTasks = new ArrayList<>();
        int currentDay = 1; // Start from Monday

        for (Task task : sortedTasks) {
            if (task.isMandatory()) {
                // Try to schedule mandatory tasks on their preferred day
                scheduledTasks.add(task);
            } else {
                // Schedule optional tasks anytime between mandatory tasks
                scheduledTasks.add(task);
            }
        }

        return scheduledTasks;
    }
}
