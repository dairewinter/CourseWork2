import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task {
    private String taskName;
    private final String description;
    private TaskType taskType;
    private PeriodicityType periodicityType;
    private LocalDateTime taskDateTime;
    private static int counter = 0;
    private final int id;

    public Task(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime, PeriodicityType periodicityType) {
        this.taskName = taskName;
        this.description = description;
        this.taskType = taskType;
        this.taskDateTime = taskDateTime;
        this.periodicityType = periodicityType;
        this.id = counter++;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }


    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public int getId() {
        return id;
    }

    public abstract boolean appearsIn (LocalDate localDate);

    public abstract PeriodicityType getPeriodicityType();
}
