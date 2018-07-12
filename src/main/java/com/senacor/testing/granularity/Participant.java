package com.senacor.testing.granularity;

public class Participant {

    private final String id;

    private final String userId;

    private final Email email;

    public Participant(String id, String userId, Email email) {
        this.id = id;
        this.userId = userId;
        this.email = email;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", email=" + email +
                '}';
    }


    public static final class Builder {
        private String id;
        private String userId;
        private Email email;

        private Builder() {
        }

        public Builder withId(String val) {
            id = val;
            return this;
        }

        public Builder withUserId(String val) {
            userId = val;
            return this;
        }

        public Builder withEmail(Email val) {
            email = val;
            return this;
        }

        public Participant build() {
            return new Participant(id, userId, email);
        }
    }
}
