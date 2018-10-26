package com.senacor.testing.o_async_2.o_async;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class SMSSenderSpec {


    @Test
    void smsIsSentViaGateway() {

        SMSGateway gateway = mock(SMSGateway.class);
        SMSSender sut = new SMSSender(gateway);
        //TODO implement
    }
}