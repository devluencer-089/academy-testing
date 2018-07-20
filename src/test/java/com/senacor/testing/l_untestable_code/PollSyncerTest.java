package com.senacor.testing.l_untestable_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PollSyncerTest {

    @Mock
    private Inbox inbox;

    @Mock
    private SMTPConnector smtpConnector;

    @Mock
    private Clock clock;

    Instant firstMessageTimestamp = Instant.now();

    @BeforeEach
    void initBetterPollSyncer() {
        initMocks(this);
        firstMessageTimestamp = Instant.now();
    }

    @Test
    public void itSyncsTheInboxWithNewMessages() {
        BetterPollSyncer sut = new BetterPollSyncer(inbox, smtpConnector, Duration.of(30, SECONDS), clock);
        when(clock.instant()).thenReturn(firstMessageTimestamp.plus(25, SECONDS));
        when(smtpConnector.messageStream()).thenReturn(firstChunkMessageStream(firstMessageTimestamp));

        sut.sync();

        verify(inbox, times(2)).add(any());
    }

    @Test
    public void itDoesNotSyncMessagesWhichWereSyncedBefore() {
        BetterPollSyncer sut = new BetterPollSyncer(inbox, smtpConnector, Duration.of(30, SECONDS), clock);
        when(clock.instant()).thenReturn(firstMessageTimestamp.plus(60, SECONDS));
        when(smtpConnector.messageStream()).thenReturn(firstChunkMessageStream(firstMessageTimestamp));

        sut.sync();

        verify(inbox, never()).add(any());
    }

    @Test
    public void itSyncsTheNewMessagesWhenItSyncsInALoop() {
        BetterPollSyncer sut = new BetterPollSyncer(inbox, smtpConnector, Duration.of(30, SECONDS), clock);
        when(clock.instant()).thenReturn(firstMessageTimestamp.plus(25, SECONDS), firstMessageTimestamp.plus(55, SECONDS));
        when(smtpConnector.messageStream()).thenReturn(firstChunkMessageStream(firstMessageTimestamp), aMessageStream(firstMessageTimestamp));

        sut.sync();
        sut.sync();

        verify(inbox, times(4)).add(any());
    }

    private Stream<Message> firstChunkMessageStream(Instant startTimestamp) {
        return Stream.of(
                new Message("1", startTimestamp),
                new Message("2", startTimestamp.plus(20, SECONDS))
                );
    }

    private Stream<Message> secondMessageStream(Instant startTimestamp) {
        return Stream.of(
                new Message("3", startTimestamp.plus(40, SECONDS)),
                new Message("4", startTimestamp.plus(50, SECONDS))
        );
    }

    private Stream<Message> aMessageStream(Instant startTimestamp) {
        return Stream.concat(firstChunkMessageStream(startTimestamp), secondMessageStream(startTimestamp));
    }
}