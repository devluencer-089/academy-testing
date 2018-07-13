package com.senacor.testing.b_better_assertions;

import com.senacor.testing.granularity.Email;
import com.senacor.testing.granularity.Poll;
import com.senacor.testing.granularity.PollAssert;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BetterAssertions {


    @Test
    public void assertJAssertions() {
        Poll sut = Poll.newBuilder()
                .participant("id1", Email.of("1@1.com"))
                .participant("id2", Email.of("2@2.com"))
                .build();

        int count = sut.getParticipantCount();

        assertThat(count).isEqualTo(2);
    }

    @Test
    public void generatedAssertJAssertionsWithTheMavenPlugin() {
        Poll sut = Poll.newBuilder()
                .ownerId("me")
                .participant("id1", Email.of("shared-email.com"))
                .participant("id2", Email.of("shared-email.com"))
                .build();

        PollAssert.assertThat(sut)
                .hasOnlyParticipantEmails(Email.of("shared-email.com"))
                .hasOwnerId("me")
                .hasNoPollOptions();
    }

    @Test
    public void hamcrestMatchers() {
        Poll sut = Poll.newBuilder()
                .participant("id1", Email.of("1@1.com"))
                .participant("id2", Email.of("2@2.com"))
                .build();

        Set<Email> emails = sut.getParticipantEmails();

        //removed compile dependency to hamcrest
//        assertThat(emails, containsInAnyOrder(Email.of("1@1.com"), Email.of("2@2.com")));
    }

}
