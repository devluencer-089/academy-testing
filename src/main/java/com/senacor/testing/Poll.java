package com.senacor.testing;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;


public class Poll {

    @Id
    @NotNull
    private final String id;

    @Indexed
    @NotNull
    private final String ownerId;

    private final String topic;

    private final List<PollOption> pollOptions;

    @Indexed
    private final List<Participant> participants;

    private final boolean closed;


    @PersistenceConstructor
    Poll(String id, String ownerId, String topic, List<PollOption> pollOptions, List<Participant> participants, boolean closed) {
        this.id = id;
        this.ownerId = ownerId;
        this.topic = topic;
        this.pollOptions = pollOptions;
        this.participants = participants;
        this.closed = closed;
    }

    private Poll(Builder builder) {
        id = builder.id;
        ownerId = builder.ownerId;
        topic = builder.topic;
        pollOptions = builder.pollOptions;
        participants = builder.participants;
        closed = builder.closed;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Poll copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.ownerId = copy.getOwnerId();
        builder.topic = copy.getTopic();
        builder.pollOptions = copy.getPollOptions();
        builder.participants = copy.getParticipants();
        builder.closed = copy.isClosed();
        return builder;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    Poll addParticipant(String id, Email email) {
        return Poll.newBuilder(this).participant(id, email).build();
    }

    public String getTopic() {
        return topic;
    }

    public List<PollOption> getPollOptions() {
        return pollOptions;
    }

    public boolean isClosed() {
        return closed;
    }



    @Override
    public String toString() {
        return "Poll{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", topic='" + topic + '\'' +
                ", pollOptions=" + pollOptions +
                ", participants=" + participants +
                '}';
    }

    public int getParticipantCount() {
        return participants.size();
    }

    public Set<Email> getParticipantEmails() {
        return participants.stream().map(Participant::getEmail).collect(toSet());
    }

    public static final class Builder {
        private String id = GUID.random();
        private String ownerId;
        private String topic;
        private List<PollOption> pollOptions = new ArrayList<>();
        private List<Participant> participants = new ArrayList<>();
        private boolean closed;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder ownerId(String ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public Builder topic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder closed(boolean closed) {
            this.closed = closed;
            return this;
        }

        public Builder pollOption(PollOption pollOption) {
            this.pollOptions.add(pollOption);
            return this;
        }

        public Builder participant(String id, Email email) {
            participants.add(Participant.newBuilder()
                    .withId(GUID.random())
                    .withUserId(id)
                    .withEmail(email)
                    .build());
            return this;
        }

        public Builder participantUserId(String participantUserId) {
            participants.add(Participant.newBuilder()
                    .withId(GUID.random())
                    .withUserId(participantUserId)
                    .build());
            return this;
        }

        public Poll build() {
            return new Poll(this);
        }
    }
}
