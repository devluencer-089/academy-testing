package com.senacor.testing.granularity;


import com.senacor.testing.GUID;

public class PollOption {

    private final String id;
    private final String description;

    public PollOption(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public PollOption(String description) {
        this(GUID.random(), description);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
