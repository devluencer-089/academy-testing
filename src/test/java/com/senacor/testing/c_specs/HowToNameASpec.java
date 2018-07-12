package com.senacor.testing.c_specs;

import org.junit.jupiter.api.Test;

class HowToNameASpec {

    interface LongAdder {
        long addLongs(long first, long second);
    }

    @Test
    public void testLongAdder() {
        //really bad
    }

    @Test
    public void itShouldReturnAResult() {
        //not specific, too generic
    }

    @Test
    public void test_addLongs_positiveNumbers_shouldReturnResult() {
        //still not specific
        //What result? Just any result?
    }

    @Test
    public void thatSummationShouldReturnReturnTheCorrectResult() {
        //very vague, too verbose
        //Does it know how to calculate the sum or should it know? What is it?!?
    }

    @Test
    public void itCalculatesTheSumOfTwoLongValues() {
        //specific and decisive wording: a specification
    }
}