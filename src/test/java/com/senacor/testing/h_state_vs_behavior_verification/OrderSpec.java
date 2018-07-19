package com.senacor.testing.h_state_vs_behavior_verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Pavle Bektasevic on 19.07.2018.
 */
@ExtendWith(MockitoExtension.class)
public class OrderSpec {

    private static final String TALISKER = "Talisker";

    private Order sut;

    @Mock
    private Warehouse warehouse;

    // just a dirty spy
    @Mock
    private MailService mailService;

    @Nested
    class WhenOrderingLessThanAvailableInWarehouse {

        @BeforeEach
        void warehouseHasEnoughProducts() {
            when(warehouse.hasInventory(TALISKER, 1)).thenReturn(true);
        }

        @Test
        void anEmailIsNotSent() {
            sut = new Order(TALISKER, 1);
            sut.setMailService(mailService);
            sut.fill(warehouse);

            verify(mailService, never()).send(any(Message.class));
        }

        @Test
        void anOrderIsFilled() {
            sut = new Order(TALISKER, 1);
            sut.setMailService(mailService);
            sut.fill(warehouse);

            assertThat(sut.isFilled()).isEqualTo(true);
        }

    }

    @Nested
    class WhenOrderingMoreThanAvailableInWarehouse {

        @BeforeEach
        void warehouseDoesNotHaveEnoughProducts() {
            when(warehouse.hasInventory(TALISKER, 1)).thenReturn(false);
        }

        @Test
        void anEmailIsSent() {
            sut = new Order(TALISKER, 1);
            sut.setMailService(mailService);
            sut.fill(warehouse);

            verify(mailService).send(any(Message.class));
        }

        @Test
        void anOrderIsNotFilled() {
            sut = new Order(TALISKER, 1);
            sut.setMailService(mailService);
            sut.fill(warehouse);

            assertThat(sut.isFilled()).isEqualTo(false);
        }
    }
}
