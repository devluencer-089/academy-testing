package com.senacor.testing.o_async;

import com.google.common.util.concurrent.Uninterruptibles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Profile("!test")
public class EmailGateway {
    void enqueue(Invitation invitation) {
        //push to mail queue
        Uninterruptibles.sleepUninterruptibly(25, TimeUnit.MILLISECONDS);
    }
}
