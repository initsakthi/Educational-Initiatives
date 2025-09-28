package patterns.behavioral;

import core.Task;
import infrastructure.ApplicationException;
import java.util.List;

/**
 * Observer Pattern Interface. Defines the contract for validation and
 * notification mechanisms.
 */
public interface TaskObserver {
    /**
     * Executes validation logic against the proposed task and the current schedule.
     * 
     * @throws ApplicationException if any rule (like conflict) is violated.
     */
    void update(Task task, List<Task> existingTasks) throws ApplicationException;
}