package app;

import core.ScheduleManager; // Imports the core ScheduleManager class
import patterns.creational.TaskFactory; // Imports the Factory Pattern class
import infrastructure.Logger; // <<< THIS IS THE CRUCIAL FIX for Logger
import infrastructure.ApplicationException; // <<< THIS IS THE CRUCIAL FIX for ApplicationException
import java.util.Scanner;
import core.Task; // Required if Task object is directly referenced (good to keep)


public class ScheduleOrganizer {
    
    // Singletons for core services.
    private static final Logger log = Logger.getInstance();
    private static final ScheduleManager manager = ScheduleManager.getInstance();
    private static final TaskFactory factory = new TaskFactory();

    public static void main(String[] args) {
        log.info("Schedule Organizer started.");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            try {
                System.out.print("\nEnter Command (ADD, VIEW, EXIT): ");
                String command = scanner.nextLine().toUpperCase().trim();

                switch (command) {
                    case "ADD" -> handleAddTask(scanner);
                    case "VIEW" -> manager.viewTasks(); 
                    case "EXIT" -> running = false;
                    default -> log.error("Invalid command. Options are ADD, VIEW, EXIT.");
                }
            } 
            catch (ApplicationException e) {
                System.out.println("SCHEDULER ERROR: " + e.getMessage());
            } 
            catch (Exception e) {
                log.critical("A Critical System Error occurred: " + e.getClass().getSimpleName() + ".");
            }
        }
        log.info("Application shutting down.");
        scanner.close();
    }
    
    private static void handleAddTask(Scanner scanner) {
        System.out.print("Description: ");
        String desc = scanner.nextLine().trim();
        if (desc.isEmpty()) throw new ApplicationException("Description cannot be empty.");
        
        System.out.print("Start (HH:MM e.g., 09:00): ");
        String startStr = scanner.nextLine();
        System.out.print("End (HH:MM e.g., 10:00): ");
        String endStr = scanner.nextLine();
        System.out.print("Priority (H/M/L): ");
        String prioStr = scanner.nextLine();

        // Factory Pattern: Creation and initial validation.
        Task newTask = factory.create(desc, startStr, endStr, prioStr);
        // Singleton Manager: Scheduling and conflict check.
        manager.add(newTask); 
        System.out.println("SUCCESS: Task scheduled.");
    }
}