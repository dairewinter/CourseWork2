import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleTask extends Task {

    public SingleTask(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime, PeriodicityType periodicityType) {
        super(taskName, description, taskType, taskDateTime, periodicityType.SINGLE);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.equals(this.getTaskDateTime().toLocalDate());
    }

    @Override
    public PeriodicityType getPeriodicityType() {
        return PeriodicityType.SINGLE;
    }
}
