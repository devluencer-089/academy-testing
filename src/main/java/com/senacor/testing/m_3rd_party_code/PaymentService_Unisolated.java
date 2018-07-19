package com.senacor.testing.m_3rd_party_code;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;

public class PaymentService_Unisolated {


    private final CashCowPaymentKit paymentKit;

    public PaymentService_Unisolated(CashCowPaymentKit paymentKit) {
        this.paymentKit = paymentKit;
    }

    void send(Amount amount, String receiverId, CashCowPaymentKit.Callback callback) {
        Map<String, String> options = ImmutableMap.of("transferType", "directTransfer", "key2", "value2");
        paymentKit.initiatePayment(
                UUID.randomUUID().toString(),
                receiverId,
                amount.getAmount().doubleValue(),
                amount.getCurrency(),
                options,
                callback);
    }
}
