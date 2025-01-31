package com.taskscheduler.controller;

import com.taskscheduler.model.Project;
import com.taskscheduler.model.Task;
import com.taskscheduler.service.TaskSchedulerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ScheduleController {

    // In a real application you might inject this service via @Autowired.
    private final TaskSchedulerService taskSchedulerService = new TaskSchedulerService();

    @GetMapping("/generateSchedule")
    public String generateSchedule(Model model) {
        // Create sample project data
        Project project = createSampleProject();

        // Generate the schedule with an 8-hour work day constraint.
        List<Task> schedule = taskSchedulerService.generateSchedule(project, 8);
        model.addAttribute("schedule", schedule);
        return "schedule";
    }

    private Project createSampleProject() {
        // Create sample tasks
        Task lecture1 = new Task("Complete Lecture 1", "Monday", 2, "9am", true, null);
        Task lecture2 = new Task("Complete Lecture 2", "Monday", 2, "9am", true, null);
        Task exerciseSheet = new Task("Complete Exercise Sheet", "Tuesday", 4, "9am", true, null);
        Task reviseNotes = new Task("Revise and take notes", "Anyday", 4, "", true, null);
        Task labwork = new Task("Complete labwork", "Friday", 2, "12pm", true, null);

        // Define dependencies
        // For example, "Revise and take notes" must be done after Lecture 1, Lecture 2, and Exercise Sheet.
        reviseNotes.setDependencies(Arrays.asList(lecture1, lecture2, exerciseSheet));
        // Labwork depends on reviseNotes being completed.
        labwork.setDependencies(Arrays.asList(reviseNotes));

        // For tasks without dependencies, ensure the dependency list is not null.
        lecture1.setDependencies(new ArrayList<>());
        lecture2.setDependencies(new ArrayList<>());
        exerciseSheet.setDependencies(new ArrayList<>());

        List<Task> tasks = new ArrayList<>(Arrays.asList(lecture1, lecture2, exerciseSheet, reviseNotes, labwork));
        return new Project("Project 1", tasks);
    }
}
