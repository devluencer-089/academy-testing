package com.senacor.testing.o_async;

import com.senacor.testing.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturizedMailer {

    private static final Logger logger = LoggerFactory.getLogger(FuturizedMailer.class);

    private final EmailGateway emailGateway;
    private final ExecutorService executor;

    public FuturizedMailer(EmailGateway emailGateway) {
        this(emailGateway, Executors.newCachedThreadPool());
    }

    public FuturizedMailer(EmailGateway emailGateway, ExecutorService executor) {
        this.emailGateway = emailGateway;
        this.executor = executor;
    }

    Future<Invitation> sendInvitation(String pollId, Email participantEmail) {
        return executor.submit(() -> {
            emailGateway.connect();
            logger.info("email is send on thread {}", Thread.currentThread().getName());
            Invitation invitation = new Invitation(pollId, participantEmail);
            emailGateway.enqueue(invitation);
            return invitation;
        });
    }


    CompletableFuture<Invitation> sendInvitationAlternative(String pollId, Email participantEmail) {
        return CompletableFuture.supplyAsync(() -> {
            //...
            Invitation invitation = new Invitation(pollId, participantEmail);
            //..
            return invitation;
        }, executor);
    }
}
