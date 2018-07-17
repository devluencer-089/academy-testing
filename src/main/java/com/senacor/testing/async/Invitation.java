package com.senacor.testing.async;

import com.senacor.testing.Email;

import java.util.Objects;

public class Invitation {
    private final String pollId;
    private final Email participantEmail;

    public Invitation(String pollId, Email participantEmail) {
        this.pollId = pollId;
        this.participantEmail = participantEmail;
    }

    public String getPollId() {
        return pollId;
    }

    public Email getParticipantEmail() {
        return participantEmail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pollId, participantEmail);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Invitation other = (Invitation) obj;
        return Objects.equals(this.pollId, other.pollId)
                && Objects.equals(this.participantEmail, other.participantEmail);
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "pollId='" + pollId + '\'' +
                ", participantEmail=" + participantEmail +
                '}';
    }
}
