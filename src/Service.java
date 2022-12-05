import java.time.LocalDate;
import java.util.*;

public class Service {

    private final Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        this.tasks.put(task.getId(), task);
    }

    public void removeTask(int id) {
        this.tasks.remove(id);
    }

    public Collection<Task> getTasksForDate(LocalDate date) {
        List<Task> tasksForDate = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.appearsIn(date)) {
                tasksForDate.add(task);

            }
        }
        return tasksForDate;
    }
}