package core;

import java.time.LocalTime;

/**
 * Core entity representing a daily task. Uses a Java record for concise,
 * immutable data.
 */
public record Task(String description, LocalTime startTime, LocalTime endTime, char priority) {
    @Override
    public String toString() {
        // Output format meets the viewing requirement[cite: 35].
        return String.format("[%s] %s-%s | %s", priority, startTime, endTime, description);
    }
}