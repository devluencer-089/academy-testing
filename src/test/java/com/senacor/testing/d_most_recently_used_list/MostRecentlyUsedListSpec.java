package com.senacor.testing.d_most_recently_used_list;

import com.senacor.testing.d_most_recently_used_list_impl.MyMostRecentlyUsedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

public class MostRecentlyUsedListSpec {

    private MyMostRecentlyUsedList sut;

    @Nested
    class WhenANewItemIsAdded {

        @BeforeEach
        public void setup() {
            sut = new MyMostRecentlyUsedList(2);
        }

        @Test
        public void theListSizeIsOne() {
            sut.add(new Object());

            assertThat(sut.size()).isEqualTo(1);
            assertThat(sut.getRecentlyUsed()).hasSize(1);
        }

        @Test
        public void theListReturnsTheHeldObject() {
            Object o = "one";
            sut.add(o);

            assertThat(sut.getRecentlyUsed()).isNotNull();
            assertThat(sut.getRecentlyUsed()).containsExactly(o);
        }

        @Test
        public void theRecentlyUsedListReturnsACopy() {
            String first = "first";
            sut.add(first);

            List<Object> returnedRecentlyUsed = sut.getRecentlyUsed();
            returnedRecentlyUsed.add("two");

            assertThat(sut.getRecentlyUsed()).containsExactly("first");
            assertThat(sut.getRecentlyUsed()).isNotSameAs(returnedRecentlyUsed);
        }
    }

    @Nested
    class WhenEmpty {

    }

    @Nested
    class WhenTheCapacityIsExceeded {

        @BeforeEach
        public void setup() {
            sut = new MyMostRecentlyUsedList(2);
        }

        @Test
        public void theListCapacityCannotBeExceeded() {
            sut.add(new Object());
            sut.add(new Object());

            Throwable thrown = catchThrowable(() -> sut.add(new Object()));

            assertThat(thrown)
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("List size exceeded!");
        }

    }

    @Nested
    class WhenFull {

    }
}