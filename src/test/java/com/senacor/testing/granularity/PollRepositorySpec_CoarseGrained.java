package com.senacor.testing.granularity;


import com.senacor.testing.Application;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(Application.class)
@ActiveProfiles("test")
class PollRepositorySpec_CoarseGrained {

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

    @Test
    void itFindsAllPollsWhereTheUserIsTheOwnerOrAParticipant() {
        List<Poll> polls = asList(
                Poll.newBuilder()
                        .ownerId("1")
                        .participantUserId("2")
                        .build(),
                Poll.newBuilder()
                        .ownerId("2")
                        .participantUserId("1")
                        .build(),
                Poll.newBuilder()
                        .ownerId("3")
                        .participantUserId("2")
                        .build()
        );

        sut.insert(polls);

        List<Poll> results = sut.findAllOfUser("1");

        assertThat(results)
                .extracting(Poll::getOwnerId)
                .containsExactly("1", "2");
    }
}