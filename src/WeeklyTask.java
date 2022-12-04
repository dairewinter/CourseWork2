import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(taskName, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getTaskDateTime().toLocalDate();
        return localDate.equals(taskDate) || (localDate.isAfter(taskDate) && localDate.getDayOfWeek().equals(taskDate.getDayOfWeek()));
    }

    @Override
    public Periodicity getPeriodicityType() {
        return Periodicity.WEEKLY;
    }


}
