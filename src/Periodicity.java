public enum Periodicity {
    SINGLE("Однократная"),
    DAILY("Ежедневная"),
    WEEKLY("Еженедельная"),
    MONTHLY("Ежемесячная"),
    YEARLY("Ежегодная");
    private final String description;

    Periodicity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
