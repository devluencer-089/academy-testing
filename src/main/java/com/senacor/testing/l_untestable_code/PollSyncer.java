package com.senacor.testing.l_untestable_code;


import java.time.Instant;
import java.util.stream.Stream;

public class PollSyncer {
    private static final PollSyncer instance = new PollSyncer(SyncerConfig.getInstance());

    public static PollSyncer getInstance() {
        return instance;
    }

    private final String username;
    private final String password;


    private PollSyncer(SyncerConfig config) {
        this.username = config.getUsername();
        this.password = config.getPassword();
    }

    public void sync() {
        Inbox inbox = Inbox.get(username, password);
        SMTPConnector connector = new SMTPConnector(username, password);
        connector.connect();
        try {

            Stream<Message> polls = connector.messageStream();

            Instant syncFrom = Instant.now().minus(Defaults.INBOX_SYNC_DURATION);
            polls
                    .filter(message -> message.getTimestamp().isAfter(syncFrom))
                    .forEach(message -> inbox.add(message));

        } finally {
            connector.disconnect();
        }
    }
}