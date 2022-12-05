import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static final Service SERVICE = new Service();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            printTasksForDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        System.out.println("Введите тип задачи: ");

        String taskName = scanner.next();
        System.out.println("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.println("Введите дату задачи: ");
        readTaskDate(scanner);
        // todo
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу \n 2. Удалить задачу \n 3. Получить задачу на указанный день \n 0. Выход");
    }

    public static LocalDate readTaskDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату задачи в формате d.mm.yyyy: ");
                String dateLine = scanner.nextLine();
                return LocalDate.parse(dateLine, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты!");
            }
        }
    }

    public static LocalTime readTaskTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите время задачи в формате HH:mm : ");
                String dateLine = scanner.nextLine();
                return LocalTime.parse(dateLine, TIME_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат!");
            }
        }
    }

    public static LocalDateTime readTaskDateTime(Scanner scanner) {
       LocalDate localDate = readTaskDate(scanner);
       LocalTime localTime = readTaskTime(scanner);
       return localDate.atTime(localTime);
    }


    public static String readTaskType(TaskType taskType) {
        switch (taskType) {
            case PERSONAL:
                return "Личная задача";
            case WORK:
                return "Рабочая задача";
        }
        return null;
    }

    public static String readPeriodicityType(PeriodicityType periodicityType) {
        switch (periodicityType){
            case SINGLE: return "Одиночная задача";
            case DAILY: return "Ежедневная задача";
            case WEEKLY: return "Еженедельная задача";
            case MONTHLY: return "Ежемесячная задача";
            case ANNUAL: return "Ежегодная задача";
        }
        return null;
    }

    public static String readString(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static void printTasksForDate(Scanner scanner) {
        LocalDate localDate = readTaskDate(scanner);
        Collection<Task> tasksForDate = SERVICE.getTasksForDate(localDate);
        System.out.println("Задачи на " + localDate.format(DATE_FORMAT));
        for (Task task : tasksForDate) {
            System.out.printf("(%s) %s: %s, дата: %s", readTaskType(task.getTaskType()), task.getTaskName(), task.getDescription(), task.getTaskDateTime().format(TIME_FORMAT));
        }
    }

    private static void addTask(Scanner scanner) {
        String taskName = readString("Введите название задачи: ", scanner);
        String description = readString("Введите описание задачи: ", scanner);
        LocalDateTime taskDateTime = readTaskDateTime(scanner);
        TaskType taskType = readType(scanner);
        PeriodicityType periodicityType = readPeriodicity(scanner);
    }

    private static void removeTask(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Какую задачу хотите удалить?");
                String idString = scanner.nextLine();
                int id = Integer.parseInt(idString);
                SERVICE.removeTask(id);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введен неверный id!");

            }
        }
    }

    private static TaskType readType(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип задачи: ");
                for (TaskType taskType : TaskType.values()) {
                    System.out.println(taskType.ordinal() + readTaskType(taskType));
                }
                System.out.println("Введите тип задачи: ");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return TaskType.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Неверный номер типа задачи");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Введен неверный тип задачи");
            }
        }
    }

    private static PeriodicityType readPeriodicity(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите тип повторяемости задачи: ");
                for (PeriodicityType periodicityType : PeriodicityType.values()) {
                    System.out.println(periodicityType.ordinal() + readPeriodicityType(periodicityType));
                }
                System.out.println("Введите тип задачи: ");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return PeriodicityType.values()[ordinal];
            } catch (NumberFormatException e) {
                System.out.println("Неверный номер типа задачи");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Введен неверный тип задачи");
            }
        }
    }
}