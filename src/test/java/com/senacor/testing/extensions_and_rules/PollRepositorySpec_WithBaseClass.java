package com.senacor.testing.extensions_and_rules;


import com.senacor.testing.Application;
import com.senacor.testing.Poll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static com.senacor.testing.Assertions.assertThat;
import static com.senacor.testing.Poll.newBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(Application.class)
class PollRepositorySpec_WithBaseClass extends OneBaseClassToRuleThemAllTest {

    @Nested
    class FindAllOfUser {

        @Test
        void itFindsTheUsersPolls() {
            Poll poll = newBuilder().ownerId("me").build();
            repository.insert(poll);

            List<Poll> polls = repository.findAllOfUser("me");

            assertThat(polls).hasSize(1)
                    .first()
                    .satisfies(singlePoll -> assertThat(singlePoll).hasOwnerId("me"));
        }

        @Test
        void itIncludesPollsWhereTheUserIsAParticipant() {
            Poll poll = newBuilder()
                    .ownerId("owner")
                    .participantUserId("me")
                    .build();
            repository.insert(poll);

            List<Poll> polls = repository.findAllOfUser("me");

            assertThat(polls).hasSize(1)
                    .first()
                    .satisfies(singlePoll -> assertThat(singlePoll).hasId(poll.getId()));
        }

        @Test
        void itExcludesOtherUsersPolls() {
            Poll poll = newBuilder()
                    .ownerId("2")
                    .participantUserId("3")
                    .participantUserId("4")
                    .build();
            repository.insert(poll);

            List<Poll> polls = repository.findAllOfUser("me");

            assertThat(polls).isEmpty();
        }
    }
}