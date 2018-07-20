package com.senacor.testing.d_most_recently_used_list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MostRecentlyUsedListSpec {

    private MostRecentlyUsedListImpl sut;

    @BeforeEach
    public void setup() {
        sut = new MostRecentlyUsedListImpl(5);
    }

    @Nested
    public class EmptyList {

        @Test
        public void itAddsAObjectToList() {
            sut.add("1");

            assertThat(sut.size()).isEqualTo(1);
        }

        @Test
        public void itContainsTheNewObjectAfterAObjectWasAdded() {
            sut.add("1");

            assertThat(sut.getRecentlyUsed())
                    .hasSize(1)
                    .containsExactly("1");
        }
    }

    @Nested
    public class PartiallyFilledList {

        @BeforeEach
        public void setup() {
            sut.add("1");
        }

        @Test
        public void itAddsAObjectToList() {
            sut.add("2");

            assertThat(sut.size()).isEqualTo(2);
        }

        @Test
        public void itContainsTheOldAndTheNewObjectAfterAObjectWasAddedInTheCorrectOrder() {
            sut.add("2");

            assertThat(sut.getRecentlyUsed())
                    .hasSize(2)
                    .containsExactly("1", "2");
        }
    }

    @Nested
    public class FullList {

        @BeforeEach
        public void setup() {
            sut.add("1");
            sut.add("2");
            sut.add("3");
            sut.add("4");
            sut.add("5");
        }

        @Test
        public void theSizeIsNotBiggerThenTheCapacity() {
            sut.add("6");

            assertThat(sut.getRecentlyUsed()).hasSize(5);
        }

        @Test
        public void itContainsTheNewObjectAfterAObjectWasAdded() {
            sut.add("6");

            assertThat(sut.getRecentlyUsed())
                    .hasSize(5)
                    .contains("6");
        }

        @Test
        public void itDoesNotContainsTheOldestObjectAfterAObjectWasAdded() {
            sut.add("6");

            assertThat(sut.getRecentlyUsed())
                    .hasSize(5)
                    .doesNotContain("1");
        }

        @Test
        public void itContainsTheObjectsInTheCorrectOrderAfterAObjectWasAdded() {
            sut.add("6");

            assertThat(sut.getRecentlyUsed())
                    .hasSize(5)
                    .containsExactly("2", "3", "4", "5", "6");
        }
    }

    @Test
    public void itThrowsAnIllegalArgumentExceptionWhenNullIsAdded() {
        assertThatThrownBy(() -> { sut.add(null); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Null objects can not be added.");
    }
}