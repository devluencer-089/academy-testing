package com.senacor.testing.m_3rd_party_code;

import java.util.Map;

public class CashCowPaymentKit {


    void initiatePayment(String id, String receiver, double amount, String ccy, Map<String, String> options, Callback callback) {
        callback.onSuccess("transactionId");
    }


    static interface Callback {
        void onError(Exception ex);

        void onSuccess(String transactionId);
    }
}
