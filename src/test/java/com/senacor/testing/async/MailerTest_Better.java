package com.senacor.testing.async;

import com.senacor.testing.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


class MailerTest_Better {

    @Mock
    private EmailGateway emailGateway;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void aCallingThreadTestDoubleDoesTheTrick() {
        Mailer sut = new Mailer(emailGateway, CallingThreadExecutor.INSTANCE);
        Email email = Email.random();
        String pollId = "pollId";

        sut.sendInvitation(pollId, email);

        verify(emailGateway).enqueue(new Invitation(pollId, email));
    }

    @Test
    void refactoringToUsePromisedIsAlsoAGoodTechnique() throws Exception {
        FuturizedMailer sut = new FuturizedMailer(emailGateway);
        Email email = Email.random();
        String pollId = "pollId";

        Future<Invitation> invitation = sut.sendInvitation(pollId, email);

        invitation.get(1, TimeUnit.SECONDS);
        verify(emailGateway).enqueue(new Invitation(pollId, email));
    }


    private static class CallingThreadExecutor implements Executor {

        private static final Executor INSTANCE = new CallingThreadExecutor();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}