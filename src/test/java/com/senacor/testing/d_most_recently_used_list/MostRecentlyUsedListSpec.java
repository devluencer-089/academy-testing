package com.senacor.testing.d_most_recently_used_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MostRecentlyUsedListSpec {

    private MostRecentlyUsedList list;

    @Nested
    class IfEmpty {

        @BeforeEach
        public void initializeEmpty() {
            list = new ListBackedMostRecentlyUsedList();
        }

        @Test
        public void itIsEmptyWhenCreated() {
            assertThat(list.size())
                    .isEqualTo(0);
        }

        @Test
        public void itIncreasesInSizeIfAnElementIsAdded() {
            list.add("an element");

            assertThat(list.size())
                    .isEqualTo(1);
        }

        @Test
        public void itReturnsElementsInTheOrderTheyWereMostRecentlyUsed() {
            list.add("first");
            list.add("second");

            assertThat(list.getRecentlyUsed())
                    .containsExactly("second", "first");
        }

        @Test
        public void itSkipsInsertingEqualElementsThatAreAddedInSuccession() {
            Object o = "Yoda";
            list.add(o);
            list.add(o);

            assertThat(list.size()).isEqualTo(1);
        }

        @Test
        public void itInsertsEqualElementsIfTheyAreNotAddedInSuccession() {
            Object o = new Object();
            list.add(o);
            list.add("element in the middle");
            list.add(o);

            assertThat(list.size()).isEqualTo(3);
        }
    }

    @Nested
    class IfNotEmpty {

        @BeforeEach
        public void initializeNotEmpty() {
            list = new ListBackedMostRecentlyUsedList("never", "gonna", "give", "you", "up");
        }

        @Test
        public void itReturnsElementsInTheOrderTheyWereMostRecentlyUsed() {
            assertThat(list.getRecentlyUsed().get(0)).isEqualTo("up");
        }
    }

    @BeforeEach
    public void beforeEach() {
        list = new ListBackedMostRecentlyUsedList();
    }

    @Test
    public void itThrowsAnExceptionWhenAddingNulls() {
        assertThatThrownBy(() -> list.add(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void itCannotBeConstructedWithNulls() {
        assertThatThrownBy(() -> new ListBackedMostRecentlyUsedList(null, null)).isInstanceOf(IllegalArgumentException.class);
    }


}