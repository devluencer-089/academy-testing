package com.senacor.testing.m_3rd_party_code;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class CashCowPaymentFacade {

    private final CashCowPaymentKit paymentKit;

    public CashCowPaymentFacade(CashCowPaymentKit paymentKit) {
        this.paymentKit = paymentKit;
    }

    CompletableFuture<String> initiatePayment(String receiverId, Amount amount) {
        Map<String, String> options = ImmutableMap.of("transferType", "directTransfer", "key2", "value2");


        CompletableFuture<String> transactionIdFuture = new CompletableFuture<>();
        paymentKit.initiatePayment(
                UUID.randomUUID().toString(),
                receiverId,
                amount.getAmount().doubleValue(),
                amount.getCurrency(),
                options,
                new CashCowPaymentKit.Callback() {
                    @Override
                    public void onError(Exception ex) {
                        transactionIdFuture.completeExceptionally(ex);
                    }

                    @Override
                    public void onSuccess(String transactionId) {
                        transactionIdFuture.complete(transactionId);
                    }
                });

        return transactionIdFuture;
    }
}
