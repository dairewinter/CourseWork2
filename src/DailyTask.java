import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {


    public DailyTask(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime, PeriodicityType periodicityType) {
        super(taskName, description, taskType, taskDateTime, periodicityType.DAILY);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getTaskDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate);
    }

    @Override
    public PeriodicityType getPeriodicityType() {
        return PeriodicityType.DAILY;
    }


}
