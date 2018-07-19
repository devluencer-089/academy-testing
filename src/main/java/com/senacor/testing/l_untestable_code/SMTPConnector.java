package com.senacor.testing.l_untestable_code;

import java.time.Instant;
import java.util.stream.Stream;

public class SMTPConnector {
    private final String username;
    private final String password;

    public SMTPConnector(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Stream<Message> messageStream() {
        return Stream.of(
                new Message("hi", Instant.now()),
                new Message("hi", Instant.now()),
                new Message("hi", Instant.now())
        );
    }

    public void connect() {
        //login with credentials
    }

    public void disconnect() {
        //logout
    }
}
