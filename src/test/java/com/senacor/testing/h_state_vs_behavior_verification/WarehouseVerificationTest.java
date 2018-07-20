package com.senacor.testing.h_state_vs_behavior_verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WarehouseVerificationTest {
    private static final String CAR = "car";
    private static final String BIKE = "bike";

    Warehouse sut;

    @BeforeEach
    void initWarehouse() {
        sut = new Warehouse();
    }

    @Test
    void itAddsGoodsToTheWarehouse() {
        sut.add(CAR, 10);

        assertThat(sut.getInventory(CAR)).isEqualTo(10);
    }

    @Test
    void itIncreaseTheStockSizeInTheWarehouse() {
        sut.add(CAR, 10);
        sut.add(CAR, 10);

        assertThat(sut.getInventory(CAR)).isEqualTo(20);
    }

    @Test
    void itRemovesGoodsFromTheWarehouse() {
        sut.add(CAR, 50);

        sut.removeFromInventory(CAR, 10);

        assertThat(sut.getInventory(CAR)).isEqualTo(40);
    }

    @Test
    void itDecreaseTheStockSizeMultipleTimesInTheWarehouse() {
        sut.add(CAR, 50);

        sut.removeFromInventory(CAR, 10);
        sut.removeFromInventory(CAR, 10);

        assertThat(sut.getInventory(CAR)).isEqualTo(30);
    }

    @Test
    void itCheckThatEnoughGoodsInTheWarehouse() {
        sut.add(CAR, 50);

        assertTrue(sut.hasInventory(CAR, 10));
    }

    @Test
    void itCheckThatNotEnoughGoodsInTheWarehouse() {
        sut.add(CAR, 50);

        assertFalse(sut.hasInventory(CAR, 60));
    }

    @Test
    void itReportsTheCorrectSizeOfTheGoodWhenTheStockContainsMultipleGoods() {
        sut.add(CAR, 50);
        sut.add(BIKE, 42);

        assertThat(sut.getInventory(CAR)).isEqualTo(50);
        assertThat(sut.getInventory(BIKE)).isEqualTo(42);
    }

    @Test
    void itReturnsZeroForUnknownGoods() {
        assertThat(sut.getInventory(CAR)).isEqualTo(0);
    }
}
