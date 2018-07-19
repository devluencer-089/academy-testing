package com.senacor.testing.o_async;

import com.senacor.testing.Application;
import com.senacor.testing.Email;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringJUnitConfig({Application.class, MailerIntegrationTest_Polling.class})
@ActiveProfiles("test")
class MailerIntegrationTest_Polling {

    @Autowired
    private Mailer sut;

    @Autowired
    private EmailGatewaySpy emailGateway;


    @AfterEach
    void resetMock() {
        emailGateway.reset();
    }

    @Test
    void pollingIsAUsefullTechniqueForIntegrationTests() {
        sut.sendInvitation("pollId", Email.random());
        sut.sendInvitation("pollId", Email.random());
        sut.sendInvitation("pollId", Email.random());

        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            assertThat(emailGateway.getCount()).isEqualTo(3);
        });
    }

    @Test
    void thisIsJustBad() throws InterruptedException {
        sut.sendInvitation("pollId", Email.random());
        sut.sendInvitation("pollId", Email.random());
        sut.sendInvitation("pollId", Email.random());

        TimeUnit.SECONDS.sleep(2);
        assertThat(emailGateway.getCount()).isEqualTo(3);
    }

    @Test
    @Disabled
    void thisWillFailForObviousReasons() {
        sut.sendInvitation("pollId", Email.random());
        sut.sendInvitation("pollId", Email.random());
        sut.sendInvitation("pollId", Email.random());

        assertThat(emailGateway.getCount()).isEqualTo(3);
    }

}