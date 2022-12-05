import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {


    public MonthlyTask(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime, PeriodicityType periodicityType) {
        super(taskName, description, taskType, taskDateTime, periodicityType.MONTHLY);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getTaskDateTime().toLocalDate();
        return localDate.equals(taskDate) || (taskDate.isBefore(localDate) && taskDate.getDayOfMonth() == localDate.getDayOfMonth());
    }

    @Override
    public PeriodicityType getPeriodicityType() {
        return PeriodicityType.MONTHLY;
    }
}

