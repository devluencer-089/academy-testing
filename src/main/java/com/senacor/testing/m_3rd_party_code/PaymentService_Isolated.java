package com.senacor.testing.m_3rd_party_code;

import com.google.common.base.Throwables;

public class PaymentService_Isolated {


    private final CashCowPaymentFacade paymentFacade;

    public PaymentService_Isolated(CashCowPaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    String send(Amount amount, String receiverId) {
        try {
            return paymentFacade.initiatePayment(receiverId, amount).get();
        } catch (Exception e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }
}
