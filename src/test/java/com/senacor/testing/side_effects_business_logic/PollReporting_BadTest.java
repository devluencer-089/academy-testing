package com.senacor.testing.side_effects_business_logic;


import com.senacor.testing.Email;
import com.senacor.testing.Poll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.List;

import static com.senacor.testing.Poll.newBuilder;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class PollReporting_BadTest {

    PollReporting_Bad sut;

    @Mock
    MyFileSystem fileSystem;

    @Captor
    ArgumentCaptor<List<Poll>> captor;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new PollReporting_Bad(fileSystem);
    }

    @Nested
    class FinishedPollsReporting {

        @Test
        void itIncludesOnlyClosedPolls() {
            Poll openPoll = newBuilder()
                    .id("1")
                    .participant("1", Email.random())
                    .closed(false)
                    .build();
            Poll closedPoll = newBuilder()
                    .id("2")
                    .participant("2", Email.random())
                    .closed(true)
                    .build();

            sut.reportFinishedPolls(asList(
                    openPoll,
                    closedPoll
            ));

            verify(fileSystem).write(captor.capture());
            assertThat(captor.getValue()).hasSize(1)
                    .first()
                    .isEqualTo(closedPoll);

        }

        @Test
        void itExcludesPollsWithoutParticipants() {
            Poll closedPoll = newBuilder()
                    .id("1")
                    .participant("1", Email.random())
                    .closed(true)
                    .build();

            Poll closedPollNoParticipants = newBuilder()
                    .id("2")
                    .closed(true)
                    .build();

            sut.reportFinishedPolls(asList(
                    closedPoll,
                    closedPollNoParticipants
            ));

            verify(fileSystem).write(captor.capture());
            assertThat(captor.getValue()).hasSize(1)
                    .first()
                    .isEqualTo(closedPoll);

        }

    }

}