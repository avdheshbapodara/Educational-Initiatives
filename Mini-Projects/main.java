import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Task Class
class Task {
    public String description;
    public String startTime;
    public String endTime;
    public String priority;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }
}

// TaskFactory Class
class TaskFactory {
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        return new Task(description, startTime, endTime, priority);
    }
}

// Observer Interface
interface Observer {
    void notify(String message);
}

// ScheduleManager Class (Singleton)
class ScheduleManager {
    private static ScheduleManager instance = null;
    private List<Task> tasks = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private ScheduleManager() {}

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.notify(message);
        }
    }

    public String addTask(Task task) {
        if (!isValidTime(task.startTime) || !isValidTime(task.endTime)) {
            return "Error: Invalid time format.";
        }

        if (isTaskOverlapping(task)) {
            Task conflictingTask = tasks.stream().filter(t -> isOverlapping(t, task)).findFirst().orElse(null);
            return "Error: Task conflicts with existing task \"" + conflictingTask.description + "\".";
        }

        tasks.add(task);
        notifyObservers("Task \"" + task.description + "\" added successfully.");
        return "Task added successfully. No conflicts.";
    }

    public String removeTask(String description) {
        int index = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.equals(description)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "Error: Task not found.";
        }
        tasks.remove(index);
        notifyObservers("Task \"" + description + "\" removed successfully.");
        return "Task removed successfully.";
    }

    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No tasks scheduled for the day.";
        }

        tasks.sort((a, b) -> a.startTime.compareTo(b.startTime));
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.startTime).append(" - ").append(task.endTime).append(": ")
              .append(task.description).append(" [").append(task.priority).append("]\n");
        }
        return sb.toString();
    }

    private boolean isTaskOverlapping(Task newTask) {
        return tasks.stream().anyMatch(existingTask -> isOverlapping(existingTask, newTask));
    }

    private boolean isOverlapping(Task task1, Task task2) {
        return task1.startTime.compareTo(task2.endTime) < 0 && task2.startTime.compareTo(task1.endTime) < 0;
    }

    private boolean isValidTime(String time) {
        return time.matches("^(2[0-3]|[01]?[0-9]):[0-5][0-9]$"); // Validates time in HH:MM format
    }
}

// Console Application
class ConsoleApp implements Observer {
    private ScheduleManager scheduleManager;

    public ConsoleApp() {
        this.scheduleManager = ScheduleManager.getInstance();
        this.scheduleManager.addObserver(this);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addTask(scanner);
                    break;
                case "2":
                    removeTask(scanner);
                    break;
                case "3":
                    viewTasks();
                    break;
                case "4":
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addTask(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter end time (HH:MM): ");
        String endTime = scanner.nextLine();
        System.out.print("Enter priority level [Easy, Medium, Hard]: ");
        String priority = scanner.nextLine();

        Task task = TaskFactory.createTask(description, startTime, endTime, priority);
        System.out.println(scheduleManager.addTask(task));
    }

    private void removeTask(Scanner scanner) {
        System.out.print("Enter task description to remove: ");
        String description = scanner.nextLine();
        System.out.println(scheduleManager.removeTask(description));
    }

    private void viewTasks() {
        System.out.println(scheduleManager.viewTasks());
    }

    // @Override
    public void notify(String message) {
        System.out.println(message);
    }
}

// Start the application
public class main {
    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();
        app.run();
    }
}
