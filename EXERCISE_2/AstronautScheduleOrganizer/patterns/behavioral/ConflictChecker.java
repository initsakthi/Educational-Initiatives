package patterns.behavioral;

import core.Task;
import infrastructure.ApplicationException;
import java.util.List;

/**
 * Concrete Observer that checks for time overlaps before a task is committed.
 */
public class ConflictChecker implements TaskObserver {
    @Override
    public void update(Task task, List<Task> existingTasks) throws ApplicationException {
        // Mandatory requirement: Validate that new tasks do not overlap[cite: 36].
        for (Task existing : existingTasks) {
            // Overlap check: A.start < B.end AND B.start < A.end
            if (task.startTime().isBefore(existing.endTime()) && existing.startTime().isBefore(task.endTime())) {
                // Throws an exception which prevents the task from being added[cite: 65].
                throw new ApplicationException("Conflict: Task overlaps with \"" + existing.description() + "\".");
            }
        }
    }
}