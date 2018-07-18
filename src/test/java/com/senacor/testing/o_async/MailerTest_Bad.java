package com.senacor.testing.o_async;

import com.senacor.testing.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


class MailerTest_Bad {

    Mailer sut;

    @Mock
    private EmailGateway emailGateway;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new Mailer(emailGateway);
    }

    @Test
    @Disabled("does not work this way")
    void itDoesNotWorkLikeNormalSynchronousOrBlockingCode() {
        Email email = Email.random();
        String pollId = "pollId";

        sut.sendInvitation(pollId, email);

        verify(emailGateway).enqueue(new Invitation(pollId, email));
    }

    @Test
    void ThreadDotSleep_isNeverAGoodIdea() throws InterruptedException {
        Email email = Email.random();
        String pollId = "pollId";

        sut.sendInvitation(pollId, email);

        TimeUnit.SECONDS.sleep(1);

        verify(emailGateway).enqueue(new Invitation(pollId, email));
    }
}