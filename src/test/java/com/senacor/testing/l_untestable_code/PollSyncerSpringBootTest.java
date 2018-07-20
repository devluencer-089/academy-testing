package com.senacor.testing.l_untestable_code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PollSyncerSpringBootTest {

    @Autowired
    private PollSyncer sut;

    @MockBean(reset = MockReset.BEFORE)
    private Inbox inbox;

    @MockBean(reset = MockReset.BEFORE)
    private SMTPConnector smptConnector;

//    @Nested
//    class WhenThereAreMessages {

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

//    }

//    @Nested
//    class WhenThereAreNoMessages {

        @Test
        void syncingDoesNotUpdateTheInbox() {
            when(smptConnector.messageStream()).thenReturn(Stream.empty());

            sut.syncForThePast(Duration.ofDays(1));

            verify(inbox, never()).add(any());
        }

//    }

}