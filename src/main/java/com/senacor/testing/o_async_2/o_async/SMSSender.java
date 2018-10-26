package com.senacor.testing.o_async_2.o_async;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SMSSender {

    private final SMSGateway smsGateway;
    private final Executor executor = Executors.newCachedThreadPool();

    public SMSSender(SMSGateway smsGateway) {
        this.smsGateway = smsGateway;
    }

    void send(String sms, Callback callback) {
        executor.execute(() -> {
            try {
                smsGateway.enqueue(sms);
                callback.onSuccess(UUID.randomUUID().toString());

            } catch (Exception ex) {
                callback.onError(ex);
            }

        });
    }

    Future<String> send(String sms) {
        //TODO implement
        CompletableFuture<String> future = new CompletableFuture<>();
        return future;
    }


    static interface Callback {
        void onSuccess(String smsId);

        void onError(Exception ex);
    }
}
