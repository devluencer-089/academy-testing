package com.senacor.testing.o_async_2.o_async;

import com.google.common.util.concurrent.Uninterruptibles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Profile("!test")
public class SMSGateway {
    String enqueue(String message) {
        //push to mail queue
        Uninterruptibles.sleepUninterruptibly(25, TimeUnit.MILLISECONDS);

        return UUID.randomUUID().toString();
    }
}
