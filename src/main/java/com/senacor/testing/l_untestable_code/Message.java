package com.senacor.testing.l_untestable_code;

import java.time.Instant;

public class Message {

    private final String text;
    private final Instant timestamp;

    public Message(String text, Instant timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
