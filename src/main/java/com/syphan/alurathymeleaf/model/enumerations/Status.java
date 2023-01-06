package com.syphan.alurathymeleaf.model.enumerations;

public enum Status {
    PENDING(1, "Pending"),
    IN_PROGRESS(2, "In progress"),
    FINISHED(3, "Finished");

    private final int id;
    private final String description;

    Status(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Status getById(int id) {
        for (Status status : Status.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }
}
