package patterns.creational;

import core.Task;
import infrastructure.ApplicationException;
import java.time.LocalTime;
import java.time.format.*;

/**
 * Factory Pattern implementation for creating valid Task objects.
 */
public class TaskFactory {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Task create(String desc, String startStr, String endStr, String prioStr) {
        LocalTime start, end;

        // Validation 1: Time format (Defensive Programming)[cite: 67].
        try {
            start = LocalTime.parse(startStr, TIME_FORMATTER);
            end = LocalTime.parse(endStr, TIME_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new ApplicationException("Invalid time format. Please use the HH:MM format (e.g., 09:30).");
        }

        // Validation 2: Time logic
        if (end.compareTo(start) <= 0)
            throw new ApplicationException("End time must be after start time.");

        // Validation 3: Priority level
        char prio = switch (prioStr.toUpperCase().charAt(0)) {
            case 'H', 'M', 'L' -> prioStr.toUpperCase().charAt(0);
            default -> throw new ApplicationException("Invalid priority. Must be H, M, or L.");
        };

        return new Task(desc, start, end, prio);
    }
}