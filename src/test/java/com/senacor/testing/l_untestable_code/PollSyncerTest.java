package com.senacor.testing.l_untestable_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PollSyncerTest {

    private PollSyncer sut;

    @Mock
    private Inbox inbox;

    @Mock
    private SMTPConnector smptConnector;

    @BeforeEach
    void provideInboxAndConnector() {
        sut = new PollSyncer(inbox, smptConnector);
    }

    @Nested
    class WhenThereAreMessages {

        @Test
        void syncingWithinTheTimePeriodUpdatesTheInbox() {
            when(smptConnector.messageStream()).thenReturn(
                    Stream.of(
                            new Message("testMessage", Instant.now().minus(Duration.ofDays(1))),
                            new Message("testMessage", Instant.now().minus(Duration.ofDays(2))),
                            new Message("testMessage", Instant.now().minus(Duration.ofDays(3)))
                    )
            );

            sut.syncForThePast(Duration.ofDays(5));

            verify(inbox, times(3)).add(any());
        }

        @Test
        void syncingOutsideTheTimePeriodDoesNotUpdateTheInbox() {
            when(smptConnector.messageStream()).thenReturn(
                    Stream.of(
                            new Message("testMessage", Instant.now().minus(Duration.ofDays(2))),
                            new Message("testMessage", Instant.now().minus(Duration.ofDays(3)))
                    )
            );

            sut.syncForThePast(Duration.ofDays(1));

            verify(inbox, never()).add(any());
        }

        @Test
        void syncPeriodIsNotInclusive() {
            when(smptConnector.messageStream()).thenReturn(
                    Stream.of(
                            new Message("testMessage", Instant.now().minus(Duration.ofDays(1)))
                    )
            );

            sut.syncForThePast(Duration.ofDays(1));

            verify(inbox, never()).add(any());
        }

    }

    @Nested
    class WhenThereAreNoMessages {

        @Test
        void syncingDoesNotUpdateTheInbox() {
            when(smptConnector.messageStream()).thenReturn(Stream.empty());

            sut.syncForThePast(Duration.ofDays(1));

            verify(inbox, never()).add(any());
        }

    }

}