package com.senacor.testing.l_untestable_code;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PollSyncer {

    private final Inbox inbox;
    private final SMTPConnector connector;

    public void syncForThePast(Duration duration) {
            List<Message> messages = getMessages();

            Instant syncFrom = Instant.now().minus(duration);

            // yo heard you like side effects
            messages.stream()
                .filter(message -> message.getTimestamp().isAfter(syncFrom))
                .forEach(inbox::add);
    }

    private List<Message> getMessages() {
        try {
            connector.connect();
            return connector
                    .messageStream()
                    .collect(Collectors.toList());
        } finally {
            connector.disconnect();
        }
    }
}