package com.senacor.testing.state_vs_behavior_verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Example as in:
 * https://martinfowler.com/articles/mocksArentStubs.html#TheDifferenceBetweenMocksAndStubs
 */
public class OrderStateVerificationTest {
    private static final String TALISKER = "Talisker";

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
        warehouse.add(TALISKER, 50);
    }

    @Test
    void testFillingRemovesInventoryIfInStock() {
        Order order = new Order(TALISKER, 40);

        order.fill(warehouse);

        assertTrue(order.isFilled());
        assertEquals(10, warehouse.getInventory(TALISKER));
    }

    @Test
    void testOrderSendsMailIfUnfilled() {
        Order order = new Order(TALISKER, 51);
        MailServiceStub mailService = new MailServiceStub();
        order.setMailService(mailService);

        order.fill(warehouse);

        assertEquals(1, mailService.numberSent());
    }

    private static class MailServiceStub implements MailService {
        private List<Message> messages = new ArrayList<>();

        public void send(Message msg) {
            messages.add(msg);
        }

        public int numberSent() {
            return messages.size();
        }
    }
}
