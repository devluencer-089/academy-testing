package com.senacor.testing.h_state_vs_behavior_verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example as in:
 * https://martinfowler.com/articles/mocksArentStubs.html#TheDifferenceBetweenMocksAndStubs
 */
public class OrderBehaviorVerificationTest {
    private static final String TALISKER = "Talisker";

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = mock(Warehouse.class);
    }

    @Test
    void testFillingRemovesInventoryIfInStock() {
        Order order = new Order(TALISKER, 40);
        when(warehouse.hasInventory(TALISKER, 40)).thenReturn(true);

        order.fill(warehouse);

        assertTrue(order.isFilled());
        verify(warehouse).hasInventory(TALISKER, 40);
        verify(warehouse).removeFromInventory(TALISKER, 40);
    }

    @Test
    void testOrderSendsMailIfUnfilled() {
        Order order = new Order(TALISKER, 51);
        MailService mailService = mock(MailService.class);
        order.setMailService(mailService);
        when(warehouse.hasInventory(TALISKER, 51)).thenReturn(false);

        order.fill(warehouse);

        verify(mailService).send(any());
        verify(warehouse).hasInventory(TALISKER, 51);
    }
}
