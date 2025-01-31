package com.taskscheduler.util;

import com.taskscheduler.model.Task;

import java.util.*;

public class TopologicalSort {

    public static List<Task> sortTasks(List<Task> tasks) {
        // Map to hold the number of prerequisites (in-degree) for each task.
        Map<Task, Integer> inDegree = new HashMap<>();
        // Build a graph mapping each task to the list of tasks that depend on it.
        Map<Task, List<Task>> graph = new HashMap<>();

        // Initialize the maps.
        for (Task task : tasks) {
            inDegree.put(task, 0);
            graph.put(task, new ArrayList<>());
        }

        // For every task, add edges from each dependency to the task.
        for (Task task : tasks) {
            if (task.getDependencies() != null) {
                for (Task dependency : task.getDependencies()) {
                    // Ensure dependency is in the map (it might be an external reference)
                    if (!inDegree.containsKey(dependency)) {
                        inDegree.put(dependency, 0);
                        graph.put(dependency, new ArrayList<>());
                    }
                    // Increase the in-degree for the task that depends on this dependency.
                    inDegree.put(task, inDegree.get(task) + 1);
                    // Add the task to the dependency's list.
                    graph.get(dependency).add(task);
                }
            }
        }

        // Initialize a queue with tasks that have no prerequisites.
        Queue<Task> queue = new LinkedList<>();
        for (Map.Entry<Task, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Task> sortedList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Task current = queue.poll();
            sortedList.add(current);
            // For every task that depends on the current task, reduce its in-degree.
            for (Task neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // If the sorted list doesn't include all tasks, there is a cycle.
        if (sortedList.size() != tasks.size()) {
            throw new RuntimeException("Cycle detected in task dependencies!");
        }

        return sortedList;
    }
}
