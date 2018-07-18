package com.senacor.testing.o_async;

import com.google.common.util.concurrent.Uninterruptibles;

import java.util.concurrent.TimeUnit;

public interface EmailGateway {
    void enqueue(Invitation invitation);

    default void connect() {
        Uninterruptibles.sleepUninterruptibly(25, TimeUnit.MILLISECONDS);
    }
}
