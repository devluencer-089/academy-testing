package com.senacor.testing.m_3rd_party_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;


class PaymentService_UnisolatedTest {


    PaymentService_Unisolated sut;

    @Mock
    private CashCowPaymentKit paymentKit;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new PaymentService_Unisolated(paymentKit);
    }

    @Test
    void pleaseKillMeNow() {

        //🤮🤮🤮🤮🤮🤮
        CashCowPaymentKit.Callback callback = mock(CashCowPaymentKit.Callback.class);
        doAnswer(invocation -> {
            callback.onSuccess("transactionId");
            return null;
        }).when(paymentKit).initiatePayment(anyString(), anyString(), anyDouble(), anyString(), anyMap(), eq(callback));

        sut.send(Amount.ZERO_EUR, "receiver", callback);

        //verify(paymentKit)...
    }
}