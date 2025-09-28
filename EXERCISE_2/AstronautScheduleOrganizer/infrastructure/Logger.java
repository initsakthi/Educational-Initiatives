package infrastructure;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Gold Standard: Logging Mechanism implemented as a Singleton.
 * In a production app, this would use a library like Log4j or SLF4J.
 */
public final class Logger {
    private static final Logger INSTANCE = new Logger();
    private Logger() {} 
    public static Logger getInstance() { return INSTANCE; }

    private void log(String level, String msg) {
        // Use System.err for ERROR/CRITICAL logs to separate from app output.
        System.err.printf("[%s] %s | %s%n", 
            level, 
            LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), 
            msg
        );
    }
    
    public void info(String msg) { log("INFO", msg); }
    public void error(String msg) { log("ERROR", msg); }
    public void critical(String msg) { 
        // Handles transient error logging or major failures[cite: 13].
        log("CRITICAL", msg); 
    }
}