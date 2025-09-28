package core;

import patterns.behavioral.ConflictChecker;
import patterns.behavioral.TaskObserver;
import infrastructure.ApplicationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Singleton Pattern implementation to manage the central list of tasks.
 * Acts as the Subject in the Observer Pattern.
 */
public final class ScheduleManager {
    private static final ScheduleManager INSTANCE = new ScheduleManager();
    private final List<Task> tasks = new ArrayList<>();
    // Observer Pattern: ConflictChecker is attached at initialization[cite: 54].
    private final List<TaskObserver> observers = List.of(new ConflictChecker());

    // Private constructor ensures Singleton integrity.
    private ScheduleManager() {
    }

    public static ScheduleManager getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a new task after validation (via Observer Pattern).
     * 
     * @throws ApplicationException if a conflict is found.
     */
    public void add(Task task) throws ApplicationException {
        // Defensive Programming: Validation happens BEFORE state change[cite: 13].
        notifyObservers(task);

        tasks.add(task);
        // Mandatory Requirement: Sorts by start time[cite: 35, 44].
        tasks.sort(Comparator.comparing(Task::startTime));
    }

    // Mandatory Requirement: View all tasks[cite: 35].
    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Schedule is currently empty. [cite: 69]");
            return;
        }
        System.out.println("\n--- Daily Schedule (" + tasks.size() + " Tasks) ---");
        tasks.forEach(System.out::println);
        System.out.println("-------------------------------------");
    }

    // (You would implement remove and edit here to complete CRUD [cite: 28, 34,
    // 39])

    private void notifyObservers(Task task) throws ApplicationException {
        List<Task> currentTasks = Collections.unmodifiableList(this.tasks);
        for (TaskObserver observer : observers) {
            // The observer is responsible for throwing a conflict exception[cite: 50, 54].
            observer.update(task, currentTasks);
        }
    }
}