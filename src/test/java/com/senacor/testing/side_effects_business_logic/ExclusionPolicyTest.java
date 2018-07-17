package com.senacor.testing.side_effects_business_logic;

import com.senacor.testing.Email;
import com.senacor.testing.Poll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.senacor.testing.Poll.newBuilder;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ExclusionPolicyTest {

    ExclusionPolicy sut;

    @Nested
    class ExcludeOpenPolicy {

        @BeforeEach
        void setUp() {
            sut = ExclusionPolicy.excludeOpen();
        }

        @Test
        void itExcludesPollsThatAreNotClosed() {
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

            List<Poll> filteredPolls = sut.apply(asList(
                    openPoll,
                    closedPoll
            ));

            assertThat(filteredPolls).containsExactly(closedPoll);
        }
    }

    @Nested
    class ExcludeUnparticipatedPolicy {

        @BeforeEach
        void setUp() {
            sut = ExclusionPolicy.excludeUnparticipated();
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

            List<Poll> filteredPolls = sut.apply(asList(
                    closedPoll,
                    closedPollNoParticipants
            ));

            assertThat(filteredPolls).containsExactly(closedPoll);

        }
    }
}