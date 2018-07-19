package com.senacor.testing.m_3rd_party_code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class PaymentService_IsolatedTest {

    PaymentService_Isolated sut;

    @Mock
    private CashCowPaymentFacade paymentFacade;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sut = new PaymentService_Isolated(paymentFacade);
    }

    @Test
    void thisIsMorePleasant() {
        when(paymentFacade.initiatePayment("receiver", Amount.ZERO_EUR)).thenReturn(completedFuture("transactionId"));

        String result = sut.send(Amount.ZERO_EUR, "receiver");

        assertThat(result).isEqualTo("transactionId");
    }
}