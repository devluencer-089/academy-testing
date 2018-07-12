package com.senacor.testing.granularity;


import com.senacor.testing.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static com.senacor.testing.granularity.Poll.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(Application.class)
@ActiveProfiles("test")
class PollRepositorySpec_FineGrained {

    @Autowired
    PollRepository sut;

    @BeforeEach
    void setUp() {
        sut.deleteAll();
    }

    @AfterEach
    void tearDown() {
        sut.deleteAll();
    }

    @Nested
    class FindAllOfUser {

        @Test
        void itFindsTheUsersPolls() {
            Poll poll = newBuilder().ownerId("me").build();
            sut.insert(poll);

            List<Poll> polls = sut.findAllOfUser("me");

            assertThat(polls).hasSize(1)
                    .first()
                    .satisfies(singlePoll -> PollAssert.assertThat(singlePoll).hasOwnerId("me"));
        }

        @Test
        void itIncludesPollsWhereTheUserIsAParticipant() {
            Poll poll = newBuilder()
                    .ownerId("owner")
                    .participantUserId("me")
                    .build();
            sut.insert(poll);

            List<Poll> polls = sut.findAllOfUser("me");

            assertThat(polls).hasSize(1)
                    .first()
                    .satisfies(singlePoll -> PollAssert.assertThat(singlePoll).hasId(poll.getId()));
        }

        @Test
        void itExcludesOtherUsersPolls() {
            Poll poll = newBuilder()
                    .ownerId("2")
                    .participantUserId("3")
                    .participantUserId("4")
                    .build();
            sut.insert(poll);

            List<Poll> polls = sut.findAllOfUser("me");

            assertThat(polls).isEmpty();
        }
    }
}