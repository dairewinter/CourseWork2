import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleTask extends Task {
    public SingleTask(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(taskName, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.equals(this.getTaskDateTime().toLocalDate());
    }

    @Override
    public Periodicity getPeriodicityType() {
        return Periodicity.SINGLE;
    }
}
