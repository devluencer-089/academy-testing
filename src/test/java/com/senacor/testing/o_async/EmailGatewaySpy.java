package com.senacor.testing.o_async;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
class EmailGatewaySpy extends EmailGateway {

    private int count;

    @Override
    void enqueue(Invitation invitation) {
        count++;
    }

    public int getCount() {
        return count;
    }

    void reset() {
        count = 0;
    }
}
