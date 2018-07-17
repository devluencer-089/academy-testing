package com.senacor.testing.async;

import com.senacor.testing.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Mailer {

    private static final Logger logger = LoggerFactory.getLogger(Mailer.class);

    private final EmailGateway emailGateway;
    private final Executor executor;

    public Mailer(EmailGateway emailGateway) {
        this(emailGateway, Executors.newCachedThreadPool());
    }

    public Mailer(EmailGateway emailGateway, Executor executor) {
        this.emailGateway = emailGateway;
        this.executor = executor;
    }

    void sendInvitation(String pollId, Email participantEmail) {
        logger.info("method is entered on thread {}", Thread.currentThread().getName());
        executor.execute(() -> {
            emailGateway.connect();
            logger.info("email is send on thread {}", Thread.currentThread().getName());
            emailGateway.enqueue(new Invitation(pollId, participantEmail));
        });
        logger.info("method is exited on thread {}", Thread.currentThread().getName());
    }
}
