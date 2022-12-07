
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private String title;

    private String description;

    private final LocalDate date;

    private final LocalTime time;

    private TaskType taskType;

    private final Periodicity periodicity;

    private boolean deletedTask = false;
    private final int id;

    public static int counter;


    public Task(String title, String description, LocalDateTime dateTime, String taskType, String periodicity) {
        this.id = counter;
//        try {
            if (title == null) {
                throw new IllegalArgumentException("Некорректное название задачи");
            } else {
                this.title = title;
            }
            if (description == null) {
                throw new IllegalArgumentException("Некорректное описание задачи");
            } else {
                this.description = description;
            }
            if (dateTime.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Слишком поздно что-то менять...");
            } else {
                this.date = dateTime.toLocalDate();
                this.time = dateTime.toLocalTime();
            }
            if (taskType.toLowerCase().contains("раб")) {
                this.taskType = TaskType.WORK;
            } else if (taskType.toLowerCase().contains("личн")) {
                this.taskType = TaskType.PERSONAL;
            } else if (taskType.equals("1")) {
                this.taskType = TaskType.PERSONAL;
            } else {
                throw new IllegalArgumentException("Некорректно указан тип задачи");
            }

            switch (periodicity.toLowerCase()) {
                case "однократная":
                    this.periodicity = Periodicity.SINGLE;
                    break;
                case "ежедневная":
                    this.periodicity = Periodicity.DAILY;
                    break;
                case "еженедельная":
                    this.periodicity = Periodicity.WEEKLY;
                    break;
                case "ежемесячная":
                    this.periodicity = Periodicity.MONTHLY;
                    break;
                case "ежегодная":
                    this.periodicity = Periodicity.YEARLY;
                    break;
                default:
                    this.periodicity = Periodicity.SINGLE;
                    break;
            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Task " + id + " " + e.getMessage());
//        }
        counter++;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IllegalArgumentException("Некорректно введен заголовок");
        } else {
            this.title = title;
        }
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Некорректно введено описание");
        } else {
            this.description = description;
        }
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public int getId() {
        return id;
    }

    public boolean isDeletedTask() {
        return deletedTask;
    }

    public void setDeletedTask() {
        this.deletedTask = true;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void changeType() {
        if (this.taskType == TaskType.PERSONAL) {
            this.taskType = TaskType.WORK;
        } else {
            this.taskType = TaskType.PERSONAL;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id && title.equals(task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }

    @Override
    public String toString() {
        return "Задача: " + title + ", id: " + getId() + '\n' +
                "статус: " + (isDeletedTask() ? "удалена" : "активна") +
                ", Описание: " + description +
                ", тип задачи: " + taskType.getDescription() +
                ", периодичность: " + periodicity.getDescription() + ", время: " + time + '\n';
    }
}
