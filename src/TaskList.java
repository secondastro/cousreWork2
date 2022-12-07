import java.time.LocalDate;
import java.util.*;


public class TaskList {

    private final Set<Task> taskSet = new HashSet<>();

    static List<Task> deletedTasks = new ArrayList<>();

    public void addTask(Task task) {
        if (task != null) {
            taskSet.add(task);
            System.out.println("Задача добавлена");
        } else {
            throw new IllegalArgumentException("Поля заполнены некорректно. Задача не добавлена");
        }
    }

    //    public void removeTask(String s) {
//        if (s != null && !s.isEmpty() && !s.isBlank()) {
//            Iterator<Task> taskIterator = taskSet.iterator();
//            while (taskIterator.hasNext()) {
//                if (taskIterator.next().getTitle().equals(s)) {
//                    taskIterator.remove();
//                }
//            }
//        }
//    }
    public void removeTask(int i) {
        if (i >= 0) {
            for (Task n : taskSet) {
                if (n.getId() == i) {
                    n.setDeletedTask();
                    deletedTasks.add(n);
                    System.out.println("Задача удалена");
                }
            }
        }
    }

    public void getTaskByData(LocalDate date) {
        Iterator<Task> taskIterator = taskSet.iterator();
        Task n;
        while (taskIterator.hasNext()) {
            n = taskIterator.next();
            if (!date.isBefore(n.getDate())) {
                switch (n.getPeriodicity()) {
                    case SINGLE:
                        if (n.getDate() == date) {
                            System.out.println(n);
                        }
                        break;
                    case DAILY:
                        System.out.println(n);
                        break;
                    case WEEKLY:
                        if (n.getDate().getDayOfWeek() == date.getDayOfWeek()) {
                            System.out.println(n);
                        }
                        break;
                    case MONTHLY:
                        if (n.getDate().getDayOfMonth() == date.getDayOfMonth()) {
                            System.out.println(n);
                        }
                        break;
                    case YEARLY:
                        if (n.getDate().getDayOfMonth() == date.getDayOfMonth() &&
                                n.getDate().getMonth() == date.getMonth()) {
                            System.out.println(n);
                        }
                }
            }
        }
    }

    public Task getTaskById(int i) {
        Iterator<Task> taskIterator = taskSet.iterator();
        Task n;
        while (taskIterator.hasNext()) {
            n = taskIterator.next();
            if (n.getId() == i) {
                return n;
            }
        }
        return null;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    @Override
    public String toString() {
        return taskSet.toString();
    }
}
