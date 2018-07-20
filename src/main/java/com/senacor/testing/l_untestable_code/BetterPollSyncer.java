package com.senacor.testing.l_untestable_code;


import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

public class BetterPollSyncer {

    private final Inbox inbox;
    private final SMTPConnector smtpConnector;
    private final Duration defaultInboxSyncDuration;
    private final Clock clock;

    public BetterPollSyncer(Inbox inbox, SMTPConnector smtpConnector, Duration defaultInboxSyncDuration, Clock clock) {
        this.inbox = inbox;
        this.smtpConnector = smtpConnector;
        this.defaultInboxSyncDuration = defaultInboxSyncDuration;
        this.clock = clock;
    }

    public void sync() {
        smtpConnector.connect();
        try {
            Stream<Message> polls = smtpConnector.messageStream();

            Instant syncFrom = Instant.now(clock).minus(defaultInboxSyncDuration);
            polls
                    .filter(message -> message.getTimestamp().isAfter(syncFrom))
                    .forEach(message -> inbox.add(message));

        } finally {
            smtpConnector.disconnect();
        }
    }
}