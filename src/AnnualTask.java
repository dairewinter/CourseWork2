import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnnualTask extends Task {
    public AnnualTask(String taskName, String description, TaskType taskType, LocalDateTime taskDateTime) {
        super(taskName, description, taskType, taskDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getTaskDateTime().toLocalDate();
        return localDate.equals(taskDate) || (localDate.isAfter(taskDate) && localDate.getDayOfMonth() == taskDate.getDayOfMonth() && localDate.getMonth().equals(taskDate.getMonth()));
    }

    @Override
    public Periodicity getPeriodicityType() {
        return Periodicity.ANNUAL;
    }


}
