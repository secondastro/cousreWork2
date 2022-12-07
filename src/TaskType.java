public enum TaskType {
    PERSONAL("Личная задача"), WORK("Рабочая задача");
    private final String description;

   TaskType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
