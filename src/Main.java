import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            taskList.addTask(inputTask(scanner));
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Укажите id удаляемой задачи");
                            taskList.removeTask(scanner.nextInt());
                            break;
                        case 3:
                            System.out.print("Введите год: ");
                            int year = scanner.nextInt();
                            System.out.print("Введите месяц: ");
                            int month = scanner.nextInt();
                            System.out.print("Введите число: ");
                            int day = scanner.nextInt();
                            LocalDate localDate = LocalDate.of(year, month, day);
                            System.out.println(localDate);
                            taskList.getTaskByData(localDate);
                            break;
                        case 4:
                            System.out.println("Введите id: ");
                            int id = scanner.nextInt();
                            System.out.println(taskList.getTaskById(id));
                            break;
                        case 5:
                            System.out.println("Список удаленных задач: ");
                            System.out.println(TaskList.deletedTasks);
                            break;
                        case 6:
                            System.out.println("Введите id редактируемой задачи: ");
                            id = scanner.nextInt();
                            System.out.println("Введите новый заголовок задачи: ");
                            scanner.nextLine();
                            String title = scanner.nextLine();
                            System.out.println("Введите новое описание: ");
                            String description = scanner.nextLine();
                            taskList.getTaskById(id).setTitle(title);
                            taskList.getTaskById(id).setDescription(description);
                            System.out.println("Изменения сохранены");
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

    private static Task inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        scanner.nextLine();
        String taskName = scanner.nextLine();
        if (Objects.equals(taskName, null)) {
            while (Objects.equals(taskName, null)) {
                System.out.println("Некорректный заголовок задачи, введите еще раз: ");
                taskName = scanner.nextLine();
            }
        }
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        if (taskDescription == null) {
            while (taskDescription == null) {
                System.out.println("Некорректно введено описание задачи, введите еще раз");
                taskDescription = scanner.nextLine();
            }
        }
        System.out.print("Введите год: ");
        int year = scanner.nextInt();
        if (year < LocalDate.now().getYear()) {
            while (year < LocalDate.now().getYear()) {
                System.out.print("Год не может быть меньше текущего. Введите еще раз: ");
                year = scanner.nextInt();
            }
        }
        System.out.print("Введите номер месяца: ");
        int month = scanner.nextInt();
        if (month <= 0 || month > 12) {
            while (month <= 0 || month > 12) {
                System.out.print("Номера месяцев идут с 1 до 12. Введите еще раз: ");
                month = scanner.nextInt();
            }
        }
        System.out.print("Введите день: ");
        int day = scanner.nextInt();
        LocalDate ld = LocalDate.of(year, month, 1);
        if (day <= 0 || day > ld.lengthOfMonth()) {
            while ((day <= 0 || day > ld.lengthOfMonth())) {
                System.out.print("Некорректно введено число месяца. Введите еще раз: ");
                day = scanner.nextInt();
            }
        }
        System.out.print("Введите час: ");
        int hour = scanner.nextInt();
        while (hour < 0 || hour > 24) {
            System.out.print("Неверно указано время. Введите час еще раз: ");
            hour = scanner.nextInt();
        }
        System.out.print("Введите минуты: ");
        int minute = scanner.nextInt();
        while (minute < 0 || minute > 60) {
            System.out.print("Неверно указано время. Введите минуты еще раз: ");
            minute = scanner.nextInt();
        }

        LocalDateTime taskLocalDateTime = LocalDateTime.of(year, month, day, hour, minute);


        System.out.print("Задача личная или рабочая? ");
        String taskType = scanner.next();
        System.out.print("Введите периодичность задачи: ");
        String taskPeriodicity = scanner.next();

        try {
            return new Task(taskName, taskDescription, taskLocalDateTime, taskType, taskPeriodicity);
        } catch (IllegalArgumentException e) {
            System.out.println("Task " + Task.counter + " " + e.getMessage());
        }
      return null;
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("4. Получить задачу по id");
        System.out.println("5. Получить список удаленных задач");
        System.out.println("6. Изменить заголовок и описание задачи");
        System.out.println("0. Выход");
    }
}