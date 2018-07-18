package com.senacor.testing.d_most_recently_used_list_possible_solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.senacor.testing.d_most_recently_used_list.MostRecentlyUsedListAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * solution: Andreas Karoly
 */
public class MostRecentlyUsedListSpec {

    MostRecentlyUsedListExample sut;

    @BeforeEach
    void setUp() {
        sut = new MostRecentlyUsedListExample();
    }

    @Nested
    class WhenEmpty {

        @Test
        void itHasInitialSizeOf0() {
            assertThat(sut.size()).isEqualTo(0);
        }

        @Test
        void itAcceptsARecentlyUsedElement() {
            MostRecentlyUsedListExample sut = new MostRecentlyUsedListExample();

            sut.add(new Object());

            assertThat(sut.size()).isEqualTo(1);
        }
    }

    @Nested
    class WhenCapped {

        @BeforeEach
        public void createWithMaxCapacity() {
            sut = new MostRecentlyUsedListExample(1);
        }

        @Test
        void itAcceptsItemsUntilCapacityIsReached() {
            sut.add(new Object());
            sut.add(new Object());

            assertThat(sut.size()).isEqualTo(1);
        }

        @Test
        void itOverwritesLastItemToEnsureCapacity() {
            // given
            Object firstItem = new Object();
            Object secondItem = new Object();

            // when
            sut.add(firstItem);
            sut.add(secondItem);

            // then
            assertThat(sut.size()).isEqualTo(1);
            assertThat(sut).hasOnlyRecentlyUsed(secondItem);
        }
    }

    @Nested
    class WhenOneItemIsAlreadyAdded {

        private final Object initialItem = new Object();

        @BeforeEach
        void setUp() {
            sut.add(initialItem);
        }

        @Test
        void itAcceptsAnotherRecentlyUsedElement() {
            sut.add(new Object());

            assertThat(sut.size()).isEqualTo(2);
        }

        @Test
        void itRetainsItemsThatAreAddedTwiceOnlyOnce() {
            sut.add(initialItem);

            assertThat(sut.size()).isEqualTo(1);
            assertThat(sut).hasOnlyRecentlyUsed(initialItem);        }
    }

    @Nested
    class WhenMultipleItemsAreAlreadyAdded {

        private final Object firstItem = "first";
        private final Object secondItem = "second";

        @BeforeEach
        void setUp() {
            sut.add(firstItem);
            sut.add(secondItem);
        }

        @Test
        void itAddsMostRecentlyUsedItemToTheTop() {
            assertThat(sut.getRecentlyUsed()).containsExactly(secondItem, firstItem);
        }

        @Test
        void itRanksPreviouslyAddedItemsToTopIfReadded() {
            Object thirdItem = "third";
            sut.add(thirdItem);

            sut.add(secondItem);

            assertThat(sut.getRecentlyUsed()).containsExactly(secondItem, thirdItem, firstItem);
        }
    }

    @Test
    void itDoesNotAcceptNullValues() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> sut.add(null))
                .withMessage("null values are not supported");
    }
}