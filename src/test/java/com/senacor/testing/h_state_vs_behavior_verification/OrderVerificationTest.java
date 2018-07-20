package com.senacor.testing.h_state_vs_behavior_verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderVerificationTest {
    private static final String CAR = "car";

    @Mock
    private Warehouse warehouse;

    @Mock
    private MailService mailService;

    @BeforeEach
    void initWarehouse() {
        initMocks(this);
    }

    @Nested
    public class WarehouseWithEnoughGoodsInStock {

        @Test
        public void itFulfilledTheOrder() {
            Order sut = new Order(CAR, 20);
            when(warehouse.hasInventory(CAR, 20)).thenReturn(true);

            sut.fill(warehouse);

            assertTrue(sut.isFilled());
            verify(warehouse).hasInventory(CAR, 20);
            verify(warehouse).removeFromInventory(CAR, 20);
        }

        @Test
        public void itDoesNotOrderNewGoods() {
            Order sut = new Order(CAR, 20);
            sut.setMailService(mailService);
            when(warehouse.hasInventory(CAR, 20)).thenReturn(true);

            sut.fill(warehouse);

            verify(mailService, never()).send(any());
        }
    }

    @Nested
    public class WarehouseWithNotEnoughGoodsInStock {

        @Test
        public void itCanNotFulfillTheOrder() {
            Order sut = new Order(CAR, 25);
            sut.setMailService(mailService);
            when(warehouse.hasInventory(CAR, 25)).thenReturn(false);

            sut.fill(warehouse);

            assertFalse(sut.isFilled());
            verify(warehouse).hasInventory(CAR, 25);
            verify(warehouse, never()).removeFromInventory(anyString(), anyInt());
        }

        @Test
        public void itOrdersNewGoods() {
            Order sut = new Order(CAR, 25);
            sut.setMailService(mailService);
            when(warehouse.hasInventory(CAR, 25)).thenReturn(false);

            sut.fill(warehouse);

            verify(mailService).send(any());
            verify(warehouse).hasInventory(CAR, 25);
        }
    }
}
